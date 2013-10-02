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

long albumId = ParamUtil.getLong(request, "albumId");

Album album = AlbumLocalServiceUtil.getAlbum(albumId);

Artist artist = ArtistLocalServiceUtil.getArtist(album.getArtistId());

List<Song> songs = SongServiceUtil.getSongs(albumId);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="<%= album.getName() %>"
/>

<div class="album-artist">
	<%= artist.getName() %>

	<div class="album-year">(<%= album.getYear() %>)</div>

	<div class="album-songs-number"><liferay-ui:message arguments="<%= songs.size() %>" key="x-songs" /></div>
</div>

<c:if test="<%= songs.isEmpty() %>">
	<jsp:include page="/html/songs/view.jsp">
		<jsp:param name="albumId" value="<%= String.valueOf(albumId) %>" />
	</jsp:include>
</c:if>