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

<liferay-ui:success key="artistAdded" message="the-artist-was-added-successfully" />
<liferay-ui:success key="artistUpdated" message="the-artist-was-updated-successfully" />
<liferay-ui:success key="artistDeleted" message="the-artist-was-deleted-successfully" />

<jsp:include page="/html/artists/toolbar.jsp" />

<%
List<Artist> artists = ArtistServiceUtil.getArtists(scopeGroupId);
%>

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