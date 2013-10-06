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
long artistId = ParamUtil.getLong(renderRequest, "artistId");
boolean showToolbar = ParamUtil.getBoolean(request, "showToolbar", true);
%>

<liferay-ui:success key="albumAdded" message="the-album-was-added-successfully" />
<liferay-ui:success key="albumUpdated" message="the-album-was-updated-successfully" />
<liferay-ui:success key="albumDeleted" message="the-album-was-deleted-successfully" />

<c:if test="<%= (artistId <= 0) && showToolbar %>">
	<portlet:renderURL var="searchURL">
		<portlet:param name="jspPage" value="/html/albums/view_search.jsp" />
		<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:renderURL>

	<aui:form action="<%= searchURL %>" method="post" name="fm">
		<jsp:include page="/html/albums/toolbar.jsp" />
	</aui:form>
</c:if>

<div id="<portlet:namespace />albumPanel">
	<jsp:include page="/html/albums/view_resources.jsp" />
</div>