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
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (song != null) ? song.getName() : "new-song" %>'
/>

<portlet:actionURL name='<%= (song != null) ? "updateSong" : "addSong" %>' var="addSongURL" />

<aui:form action="<%= addSongURL %>" method="post" name="fm">
	<aui:model-context bean="<%= song %>" model="<%= Song.class %>" />

	<aui:input name="songId" type="hidden" value="<%= songId %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:select label="album" name="albumId">

		<%
		List<Album> albums = AlbumLocalServiceUtil.getAlbums(scopeGroupId);

		for (Album album : albums) {
		%>

			<aui:option label="<%= album.getName() %>" value="<%= album.getAlbumId() %>" />

		<%
		}
		%>

	</aui:select>

	<aui:input name="name" />

	<aui:button-row>
		<aui:button type="submit" />

		<c:if test="<%= song != null %>">
			<portlet:actionURL name="deleteSong" var="deleteSongURL">
				<portlet:param name="songId" value="<%= String.valueOf(song.getSongId()) %>" />
			</portlet:actionURL>

			<aui:button cssClass="btn-danger" href="<%= deleteSongURL %>" value="delete" />
		</c:if>
	</aui:button-row>
</aui:form>