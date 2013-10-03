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

long songId = ParamUtil.getLong(request, "songId");

Song song = null;

if (songId > 0) {
	 song = SongLocalServiceUtil.getSong(songId);
}
else {
	song = (Song)request.getAttribute("jukebox_song");
}

Album album = AlbumLocalServiceUtil.getAlbum(song.getAlbumId());

Artist artist = ArtistLocalServiceUtil.getArtist(song.getArtistId());

boolean showHeader = ParamUtil.getBoolean(request, "showHeader", true);
%>

<c:if test="<%= showHeader %>">
	<liferay-ui:header
		backURL="<%= redirect %>"
		title="<%= song.getName() %>"
	/>
</c:if>

<div class="song-artist">
	<%= artist.getName() %>

	<div class="song-album">(<%= album.getName() %>)</div>
	<div class="song-year">(<%= album.getYear() %>)</div>
</div>

<c:if test="<%= showHeader %>">
	<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

	<liferay-ui:discussion
		className="<%= Song.class.getName() %>"
		classPK="<%= song.getSongId() %>"
		formAction="<%= discussionURL %>"
		formName="fm2"
		userId="<%= song.getUserId() %>"
	/>
</c:if>