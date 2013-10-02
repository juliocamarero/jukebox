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
String toolbarItem = ParamUtil.getString(request, "toolbarItem");
%>

<aui:nav-bar>
	<aui:nav>
		<c:if test='<%= JukeBoxPermission.contains(permissionChecker, scopeGroupId, "ADD_ARTIST") %>'>
			<portlet:renderURL var="editArtistURL">
				<portlet:param name="jspPage" value="/html/artists/edit_artist.jsp" />
				<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= editArtistURL %>" iconClass="icon-plus" label="add-artist" selected='<%= toolbarItem.equals("add") %>' />
		</c:if>
	</aui:nav>

	<aui:nav-bar-search cssClass="pull-right">
		<div class="form-search">
			<liferay-ui:input-search autoFocus="<%= liferayPortletRequest.getWindowState().equals(WindowState.MAXIMIZED) %>" id="keywords1" name="keywords" placeholder='<%= LanguageUtil.get(locale, "keywords") %>' />
		</div>
	</aui:nav-bar-search>
</aui:nav-bar>