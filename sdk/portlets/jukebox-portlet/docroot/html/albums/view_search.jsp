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
String redirect = ParamUtil.getString(request, "redirect");

String keywords = ParamUtil.getString(request, "keywords");

Indexer indexer = IndexerRegistryUtil.getIndexer(Album.class);

SearchContext searchContext = SearchContextFactory.getInstance(request);

searchContext.setIncludeDiscussions(true);
searchContext.setKeywords(keywords);

Hits hits = indexer.search(searchContext);

List<Album> albums = new ArrayList<Album>();

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
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="search"
/>

<portlet:renderURL var="searchURL">
	<portlet:param name="jspPage" value="/html/albums/view_search.jsp" />
	<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
</portlet:renderURL>

<aui:form action="<%= searchURL %>" method="post" name="fm">
	<jsp:include page="/html/albums/toolbar.jsp" />
</aui:form>

<c:choose>
	<c:when test="<%= albums.isEmpty() %>">
		<div class="alert alert-info">
			<c:choose>
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