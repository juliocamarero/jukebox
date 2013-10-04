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
%>

<liferay-ui:success key="songAdded" message="the-song-was-added-successfully" />
<liferay-ui:success key="songUpdated" message="the-song-was-updated-successfully" />
<liferay-ui:success key="songDeleted" message="the-song-was-deleted-successfully" />
<liferay-ui:success key="songMovedToTrash" message="the-song-was-moved-to-trash-successfully" />

<portlet:actionURL name="restoreSong" var="undoTrashURL" />

<liferay-ui:trash-undo portletURL="<%= undoTrashURL %>" />

<c:if test="<%= (albumId <= 0) && showToolbar %>">
	<portlet:renderURL var="searchURL">
		<portlet:param name="jspPage" value="/html/songs/view_search.jsp" />
		<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
		<portlet:param name="searchView" value="<%= Boolean.TRUE.toString() %>" />
	</portlet:renderURL>

	<aui:form action="<%= searchURL %>" method="post" name="fm">
		<jsp:include page="/html/songs/toolbar.jsp" />
	</aui:form>
</c:if>

<div id="<portlet:namespace />songPanel">
	<jsp:include page="/html/songs/view_resources.jsp" />
</div>