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

long artistId = ParamUtil.getLong(request, "artistId");

Artist artist = ArtistLocalServiceUtil.getArtist(artistId);

List<Album> albums = AlbumServiceUtil.getAlbums(artistId);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="<%= artist.getName() %>"
/>

<div class="album-artist">
	<div class="album-songs-number"><liferay-ui:message arguments="<%= albums.size() %>" key="x-albums" /></div>
</div>

<c:if test="<%= albums.isEmpty() %>">
	<jsp:include page="/html/albums/view.jsp">
		<jsp:param name="artistId" value="<%= String.valueOf(artistId) %>" />
	</jsp:include>
</c:if>