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
package org.liferay.jukebox.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.liferay.jukebox.model.Artist;
import org.liferay.jukebox.portlet.ArtistsPortlet;
import org.liferay.jukebox.service.permission.ArtistPermission;

/**
 * @author Julio Camarero
 */

public class ArtistAssetRenderer extends BaseAssetRenderer {

	public ArtistAssetRenderer(Artist artist) {
		_artist = artist;
	}

	@Override
	public String getClassName() {
		return Artist.class.getName();
	}

	@Override
	public long getClassPK() {
		return _artist.getArtistId();
	}

	@Override
	public long getGroupId() {
		return _artist.getGroupId();
	}

	@Override
	public String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/blogs/blogs.png";
	}

	public String getPortletId() {
		AssetRendererFactory assetRendererFactory = getAssetRendererFactory();

		return assetRendererFactory.getPortletId();
	}

	@Override
	public String getSummary(Locale locale) {
		String summary = _artist.getName();

		return summary;
	}

	@Override
	public String getThumbnailPath(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return themeDisplay.getPathThemeImages() +
			"/file_system/large/blog.png";
	}

	@Override
	public String getTitle(Locale locale) {
		return _artist.getName();
	}

	public String getType() {
		return ArtistAssetRendererFactory.TYPE;
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest),
			ArtistsPortlet.PORTLET_ID, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("jspPage", "/html/artists/edit_artist.jsp");
		portletURL.setParameter(
			"artistId", String.valueOf(_artist.getArtistId()));

		return portletURL;
	}

	@Override
	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			ArtistsPortlet.PORTLET_ID, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("jspPage", "/html/artists/view_artist.jsp");
		portletURL.setParameter(
			"artistId", String.valueOf(_artist.getArtistId()));
		portletURL.setWindowState(windowState);

		return portletURL;
	}

	@Override
	public long getUserId() {
		return _artist.getUserId();
	}

	@Override
	public String getUserName() {
		return _artist.getUserName();
	}

	@Override
	public String getUuid() {
		return _artist.getUuid();
	}

	public boolean hasDeletePermission(PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		return ArtistPermission.contains(
			permissionChecker, _artist.getArtistId(), ActionKeys.DELETE);
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		return ArtistPermission.contains(
			permissionChecker, _artist.getArtistId(), ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		return ArtistPermission.contains(
			permissionChecker, _artist.getArtistId(), ActionKeys.VIEW);
	}

	@Override
	public boolean isPrintable() {
		return true;
	}

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String template)
		throws Exception {
		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute("jukebox_artist", _artist);

			return "/html/artists/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	private Artist _artist;
}