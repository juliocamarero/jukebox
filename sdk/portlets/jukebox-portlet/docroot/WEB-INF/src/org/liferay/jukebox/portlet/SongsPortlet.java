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
import org.liferay.jukebox.SongNameException;
import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.service.SongServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Julio Camarero
 */
public class SongsPortlet extends MVCPortlet {
	
	public void addSong(ActionRequest request, ActionResponse response)
		throws Exception {

		long albumId = ParamUtil.getLong(request, "albumId");
		String name = ParamUtil.getString(request, "name");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Song.class.getName(), request);

		try {
			SongServiceUtil.addSong(albumId, name, serviceContext);

			SessionMessages.add(request, "songAdded");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			if (e instanceof SongNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(request, e.getClass().getName());

				response.setRenderParameter(
					"jspPage", "/html/songs/edit_song.jsp");
			}
			else {
				response.setRenderParameter("jspPage", "/html/error.jsp");
			}
		}
	}

	public void deleteSong(ActionRequest request, ActionResponse response)
		throws Exception {

		long songId = ParamUtil.getLong(request, "songId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Song.class.getName(), request);

		try {
			SongServiceUtil.deleteSong(songId, serviceContext);

			SessionMessages.add(request, "songDeleted");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			response.setRenderParameter("jspPage", "/html/error.jsp");
		}
	}

	public void updateSong(ActionRequest request, ActionResponse response)
		throws Exception {

		long albumId = ParamUtil.getLong(request, "albumId");
		long songId = ParamUtil.getLong(request, "songId");
		String name = ParamUtil.getString(request, "name");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Song.class.getName(), request);

		try {
			SongServiceUtil.updateSong(
				songId, albumId, name, serviceContext);

			SessionMessages.add(request, "songUpdated");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			if (e instanceof SongNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(request, e.getClass().getName());

				response.setRenderParameter(
					"jspPage", "/html/songs/edit_song.jsp");
			}
			else {
				response.setRenderParameter("jspPage", "/html/error.jsp");
			}
		}
	}
}