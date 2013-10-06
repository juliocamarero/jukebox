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

<%@ include file="/html/portlet/search/facets/init.jsp" %>

<%@ page import="org.liferay.jukebox.model.Artist" %>
<%@ page import="org.liferay.jukebox.service.ArtistLocalServiceUtil" %>

<%
if (termCollectors.isEmpty()) {
	return;
}

int frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
int maxTerms = dataJSONObject.getInt("maxTerms", 10);
boolean showAssetCount = dataJSONObject.getBoolean("showAssetCount", true);
%>

<div class="<%= cssClass %>" data-facetFieldName="<%= facet.getFieldId() %>" id="<%= randomNamespace %>facet">
	<aui:input name="<%= facet.getFieldId() %>" type="hidden" value="<%= fieldParam %>" />

	<ul class="artist unstyled">
		<li class="facet-value default <%= Validator.isNull(fieldParam) ? "current-term" : StringPool.BLANK %>">
			<a data-value="" href="javascript:;"><img alt="" class="any-artist-result" src='<%= themeDisplay.getPortalURL() + "/jukebox-portlet/icons/artists.png" %>' /><liferay-ui:message key="any" /> <liferay-ui:message key="<%= facetConfiguration.getLabel() %>" /></a>
		</li>

		<%
		long artistId = GetterUtil.getLong(fieldParam);

		for (int i = 0; i < termCollectors.size(); i++) {
			TermCollector termCollector = termCollectors.get(i);

			long curArtistId = GetterUtil.getLong(termCollector.getTerm());

			Artist curArtist = ArtistLocalServiceUtil.getArtist(curArtistId);
		%>

			<c:if test="<%= artistId == curArtistId %>">
				<aui:script use="liferay-token-list">
					Liferay.Search.tokenList.add(
						{
							clearFields: '<%= renderResponse.getNamespace() + facet.getFieldId() %>',
							text: '<%= HtmlUtil.escapeJS(curArtist.getName()) %>'
						}
					);
				</aui:script>
			</c:if>

			<%
			if (((maxTerms > 0) && (i >= maxTerms)) || ((frequencyThreshold > 0) && (frequencyThreshold > termCollector.getFrequency()))) {
				break;
			}
			%>

			<li class="facet-value <%= (artistId == curArtistId) ? "current-term" : StringPool.BLANK %>">
				<a data-value="<%= curArtistId %>" href="javascript:;"><img alt="" class="artist-search-result img-circle" src='<%= curArtist.getImageURL(themeDisplay) %>' /><%= HtmlUtil.escape(curArtist.getName()) %></a><c:if test="<%= showAssetCount %>"> <span class="frequency">(<%= termCollector.getFrequency() %>)</span></c:if>
			</li>

		<%
		}
		%>

	</ul>
</div>

<style type="text/css">
	.artist-search-result {
		width: 30px;
		height: 30px;

	}

	.any-artist-result {
		width: 25px;
	}
</style>