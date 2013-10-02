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

Indexer indexer = IndexerRegistryUtil.getIndexer(Artist.class);

SearchContext searchContext = SearchContextFactory.getInstance(request);

searchContext.setIncludeDiscussions(true);
searchContext.setKeywords(keywords);

Hits hits = indexer.search(searchContext);

List<Artist> artists = new ArrayList<Artist>();

for (int i = 0; i < hits.getDocs().length; i++) {
	Document doc = hits.doc(i);

	long artistId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

	Artist artist = null;

	try {
		artist = ArtistLocalServiceUtil.getArtist(artistId);

		artist = artist.toEscapedModel();
	}
	catch (Exception e) {
		continue;
	}

	artists.add(artist);
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="search"
/>

<portlet:renderURL var="searchURL">
	<portlet:param name="jspPage" value="/html/artists/view_search.jsp" />
	<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
</portlet:renderURL>

<aui:form action="<%= searchURL %>" method="post" name="fm">
	<jsp:include page="/html/artists/toolbar.jsp" />
</aui:form>

<c:choose>
	<c:when test="<%= artists.isEmpty() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-artists" />
		</div>
	</c:when>
	<c:otherwise>
		<ul>

			<%
			for (Artist artist : artists) {
			%>

			<li>
				<portlet:renderURL var="viewArtistURL">
					<portlet:param name="jspPage" value="/html/artists/view_artist.jsp" />
					<portlet:param name="artistId" value="<%= String.valueOf(artist.getArtistId()) %>" />
					<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
				</portlet:renderURL>

				<aui:a href="<%= viewArtistURL %>" label="<%= artist.getName() %>" />

				<%= artist.getName() %>

				<c:if test="<%= ArtistPermission.contains(permissionChecker, artist.getArtistId(), ActionKeys.UPDATE) %>">
					<portlet:renderURL var="editArtistURL">
						<portlet:param name="jspPage" value="/html/artists/edit_artist.jsp" />
						<portlet:param name="artistId" value="<%= String.valueOf(artist.getArtistId()) %>" />
						<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>

					<liferay-ui:icon image="edit" label="<%= true %>" message="edit" url="<%= editArtistURL %>" />
				</c:if>
			</li>

			<%
			}
			%>

		</ul>
	</c:otherwise>
</c:choose>