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
package org.liferay.jukebox.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.liferay.jukebox.AlbumNameException;
import org.liferay.jukebox.model.Album;
import org.liferay.jukebox.service.AlbumServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Julio Camarero
 */
public class AlbumsPortlet extends MVCPortlet {

	public void addAlbum(ActionRequest request, ActionResponse response)
		throws Exception {

		long artistId = ParamUtil.getLong(request, "artistId");
		String name = ParamUtil.getString(request, "name");
		int year = ParamUtil.getInteger(request, "year");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Album.class.getName(), request);

		try {
			AlbumServiceUtil.addAlbum(artistId, name, year, serviceContext);

			SessionMessages.add(request, "albumAdded");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			if (e instanceof AlbumNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(request, e.getClass().getName());

				response.setRenderParameter(
					"jspPage", "/html/albums/edit_album.jsp");
			}
			else {
				response.setRenderParameter("jspPage", "/html/error.jsp");
			}
		}
	}

	public void deleteAlbum(ActionRequest request, ActionResponse response)
		throws Exception {

		long albumId = ParamUtil.getLong(request, "albumId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Album.class.getName(), request);

		try {
			AlbumServiceUtil.deleteAlbum(albumId, serviceContext);

			SessionMessages.add(request, "albumDeleted");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			response.setRenderParameter("jspPage", "/html/error.jsp");
		}
	}

	public void updateAlbum(ActionRequest request, ActionResponse response)
		throws Exception {

		long albumId = ParamUtil.getLong(request, "albumId");
		long artistId = ParamUtil.getLong(request, "artistId");
		String name = ParamUtil.getString(request, "name");
		int year = ParamUtil.getInteger(request, "year");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Album.class.getName(), request);

		try {
			AlbumServiceUtil.updateAlbum(
				albumId, artistId, name, year, serviceContext);

			SessionMessages.add(request, "albumUpdated");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			if (e instanceof AlbumNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(request, e.getClass().getName());

				response.setRenderParameter(
					"jspPage", "/html/albums/edit_album.jsp");
			}
			else {
				response.setRenderParameter("jspPage", "/html/error.jsp");
			}
		}
	}
}