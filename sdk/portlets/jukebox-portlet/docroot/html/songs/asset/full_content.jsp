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

<%@ include file="../../init.jsp" %>

<jsp:include page="/html/songs/view_song.jsp">
	<jsp:param name="showHeader" value="<%= String.valueOf(false) %>" />
</jsp:include>

<liferay-util:body-bottom outputKey="jukebox-css">

	<%
	String headerPortalCss = PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext() + "/jukebox-portlet/css/jukebox.css");
	%>

	<script type="text/javascript">
		// Load the CSS Asynchronously

		(function(d){
			  var css, id = 'jukebox-css'; if (d.getElementById(id)) {return;}
			  css = d.createElement('link'); css.id = id; css.async = true; css.rel = 'stylesheet'; css.type = 'text/css'
			  css.href = '<%= HtmlUtil.escapeJS(headerPortalCss) %>';
			  d.getElementsByTagName('head')[0].appendChild(css);
		}(document));
	</script>
</liferay-util:body-bottom>