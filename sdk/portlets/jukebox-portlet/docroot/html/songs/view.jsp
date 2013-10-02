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

<liferay-ui:success key="songAdded" message="the-song-was-added-successfully" />
<liferay-ui:success key="songUpdated" message="the-song-was-updated-successfully" />
<liferay-ui:success key="songDeleted" message="the-song-was-deleted-successfully" />

<c:if test='<%= JukeBoxPermission.contains(permissionChecker, scopeGroupId, "ADD_SONG") %>'>
	<portlet:renderURL var="editSongURL">
		<portlet:param name="jspPage" value="/html/songs/edit_song.jsp" />
		<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:renderURL>

	<liferay-ui:icon image="add" label="<%= true %>" message="add-song" url="<%= editSongURL %>" />
</c:if>

<%
List<Song> songs = SongServiceUtil.getSongs(scopeGroupId);
%>

<c:choose>
	<c:when test="<%= songs.isEmpty() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-songs" />
		</div>
	</c:when>
	<c:otherwise>
		<ul>

			<%
			for (Song song : songs) {
			%>

				<li>

					<%
					Artist artist = ArtistLocalServiceUtil.getArtist(song.getArtistId());

					Album album = AlbumLocalServiceUtil.getAlbum(song.getAlbumId());
					%>

					<portlet:renderURL var="viewSongURL">
						<portlet:param name="jspPage" value="/html/songs/view_song.jsp" />
						<portlet:param name="songId" value="<%= String.valueOf(song.getSongId())%>" />
						<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>

					<aui:a href="<%= viewSongURL %>" label="<%= song.getName() %>" />, <%= artist.getName() %>, <%= album.getName() %>

					<c:if test='<%= SongPermission.contains(permissionChecker, song.getSongId(), ActionKeys.UPDATE) %>'>
						<portlet:renderURL var="editSongURL">
							<portlet:param name="jspPage" value="/html/songs/edit_song.jsp" />
							<portlet:param name="songId" value="<%= String.valueOf(song.getSongId())%>" />
							<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
						</portlet:renderURL>

						<liferay-ui:icon image="edit" label="<%= true %>" message="edit" url="<%= editSongURL %>" />
					</c:if>
				</li>

			<%
			}
			%>
		</ul>
	</c:otherwise>
</c:choose>