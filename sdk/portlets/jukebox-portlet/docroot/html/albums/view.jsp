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

<liferay-ui:success key="albumAdded" message="the-album-was-added-successfully" />
<liferay-ui:success key="albumUpdated" message="the-album-was-updated-successfully" />
<liferay-ui:success key="albumDeleted" message="the-album-was-deleted-successfully" />

<c:if test='<%= JukeBoxPermission.contains(permissionChecker, scopeGroupId, "ADD_ALBUM") %>'>
	<portlet:renderURL var="editAlbumURL">
		<portlet:param name="jspPage" value="/html/albums/edit_album.jsp" />
		<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:renderURL>

	<liferay-ui:icon image="add" label="<%= true %>" message="add-album" url="<%= editAlbumURL %>" />
</c:if>

<%
List<Album> albums = AlbumServiceUtil.getAlbums(scopeGroupId);
%>

<c:choose>
	<c:when test="<%= albums.isEmpty() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-albums" />
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

				<%= album.getName() %>, <%= artist.getName() %>, <%= album.getYear() %>

				<c:if test='<%= AlbumPermission.contains(permissionChecker, album.getAlbumId(), ActionKeys.UPDATE) %>'>
					<portlet:renderURL var="editAlbumURL">
						<portlet:param name="jspPage" value="/html/albums/edit_album.jsp" />
						<portlet:param name="albumId" value="<%= String.valueOf(album.getAlbumId())%>" />
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