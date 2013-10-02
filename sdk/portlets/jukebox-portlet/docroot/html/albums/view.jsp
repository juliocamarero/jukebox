<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="../init.jsp" %>

<%
long artistId = ParamUtil.getLong(renderRequest, "artistId");
%>

<liferay-ui:success key="albumAdded" message="the-album-was-added-successfully" />
<liferay-ui:success key="albumUpdated" message="the-album-was-updated-successfully" />
<liferay-ui:success key="albumDeleted" message="the-album-was-deleted-successfully" />

<%
List<Album> albums = null;

if (artistId > 0) {
	albums = AlbumLocalServiceUtil.getAlbumsByArtistId(artistId);
}
else {
	String keywords = ParamUtil.getString(request, "keywords");

	if (Validator.isNotNull(keywords)) {
		Indexer indexer = IndexerRegistryUtil.getIndexer(Album.class);

		SearchContext searchContext = SearchContextFactory.getInstance(request);

		searchContext.setIncludeDiscussions(true);
		searchContext.setKeywords(keywords);

		Hits hits = indexer.search(searchContext);

		for (int i = 0; i < hits.getDocs().length; i++) {
			Document doc = hits.doc(i);

			long albumId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

			Album album = null;

			try {
				album = AlbumLocalServiceUtil.getAlbum(albumId);

				album = album.toEscapedModel();
			}
			catch (Exception e) {
				continue;
			}

			albums.add(album);
		}
	}
	else {
		albums = AlbumServiceUtil.getAlbums(scopeGroupId);
	}
}
%>

<c:if test="<%= artistId <= 0 %>">
	<liferay-portlet:renderURL varImpl="searchURL" />
	
	<aui:form action="<%= searchURL.toString() %>" method="get" name="fm">
		<jsp:include page="/html/albums/toolbar.jsp" />
	</aui:form>
</c:if>

<c:choose>
	<c:when test="<%= albums.isEmpty() %>">
		<div class="alert alert-info">
			<c:choose>
				<c:when test="<%= artistId > 0 %>">
					<liferay-ui:message key="this-artist-does-not-have-any-album" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="there-are-no-albums" />
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>
	<c:otherwise>
		<ul>

			<%
			for (Album album : albums) {
			%>

			<li>

				<%
				Artist artist = ArtistLocalServiceUtil.getArtist(album.getArtistId());
				%>

				<portlet:renderURL var="viewAlbumURL">
					<portlet:param name="jspPage" value="/html/albums/view_album.jsp" />
					<portlet:param name="albumId" value="<%= String.valueOf(album.getAlbumId()) %>" />
					<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
				</portlet:renderURL>

				<aui:a href="<%= viewAlbumURL %>" label="<%= album.getName() %>" />, <%= artist.getName() %>, <%= album.getYear() %>

				<c:if test="<%= AlbumPermission.contains(permissionChecker, album.getAlbumId(), ActionKeys.UPDATE) %>">
					<portlet:renderURL var="editAlbumURL">
						<portlet:param name="jspPage" value="/html/albums/edit_album.jsp" />
						<portlet:param name="albumId" value="<%= String.valueOf(album.getAlbumId()) %>" />
						<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>

					<liferay-ui:icon image="edit" label="<%= true %>" message="edit" url="<%= editAlbumURL %>" />
				</c:if>
			</li>

			<%
			}
			%>

		</ul>
	</c:otherwise>
</c:choose>