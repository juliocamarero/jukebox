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
long albumId = ParamUtil.getLong(renderRequest, "albumId");
boolean showToolbar = ParamUtil.getBoolean(request, "showToolbar", true);

String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);

long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);
%>

<liferay-ui:success key="songAdded" message="the-song-was-added-successfully" />
<liferay-ui:success key="songUpdated" message="the-song-was-updated-successfully" />
<liferay-ui:success key="songDeleted" message="the-song-was-deleted-successfully" />

<%
List<Song> songs = null;

if (albumId > 0) {
	songs = SongLocalServiceUtil.getSongsByAlbumId(albumId);
}
else {
	songs = SongServiceUtil.getSongs(scopeGroupId);
}
%>

<c:if test="<%= (albumId <= 0) && showToolbar %>">
	<portlet:renderURL var="searchURL">
		<portlet:param name="jspPage" value="/html/songs/view_search.jsp" />
		<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:renderURL>

	<aui:form action="<%= searchURL %>" method="post" name="fm">
		<jsp:include page="/html/songs/toolbar.jsp" />
	</aui:form>
</c:if>

<c:choose>
	<c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
		<%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, songs) %>
	</c:when>
	<c:when test="<%= songs.isEmpty() %>">
		<div class="alert alert-info">
			<c:choose>
				<c:when test="<%= albumId > 0 %>">
					<liferay-ui:message key="this-album-does-not-have-any-song" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="there-are-no-songs" />
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>
	<c:otherwise>

		<div id="sm2-container">
		  <!-- SM2 flash goes here -->
		 </div>

		 <ul class="graphic">

			 <%
			for (Song song : songs) {
			%>

				<li>

					<%
					Artist artist = ArtistLocalServiceUtil.getArtist(song.getArtistId());

					Album album = AlbumLocalServiceUtil.getAlbum(song.getAlbumId());
					%>

					<%--
					<portlet:renderURL var="viewSongURL">
						<portlet:param name="jspPage" value="/html/songs/view_song.jsp" />
						<portlet:param name="songId" value="<%= String.valueOf(song.getSongId()) %>" />
						<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>

					<aui:a href="<%= viewSongURL %>" label="<%= song.getName() %>" />, <%= artist.getName() %>, <%= album.getName() %>
					--%>

			 		<a class="song-link" href="<%= song.getSongURL(themeDisplay, "mp3") %>"><%= song.getName() %></a>

					<c:if test="<%= SongPermission.contains(permissionChecker, song.getSongId(), ActionKeys.UPDATE) %>">
						<portlet:renderURL var="editSongURL">
							<portlet:param name="jspPage" value="/html/songs/edit_song.jsp" />
							<portlet:param name="songId" value="<%= String.valueOf(song.getSongId()) %>" />
							<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
						</portlet:renderURL>

						<liferay-ui:icon image="edit" message="edit" url="<%= editSongURL %>" />
					</c:if>
				</li>

			<%
			}
			 %>

	</c:otherwise>
</c:choose>