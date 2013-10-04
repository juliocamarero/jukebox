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
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.liferay.jukebox.SongNameException;
import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.service.SongServiceUtil;

/**
 * @author Julio Camarero
 * @author Sergio González
 * @author Eudaldo Alonso
 */
public class SongsPortlet extends MVCPortlet {

	public void addSong(ActionRequest request, ActionResponse response)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(request);

		long albumId = ParamUtil.getLong(uploadPortletRequest, "albumId");
		String name = ParamUtil.getString(uploadPortletRequest, "name");

		InputStream songInputStream = uploadPortletRequest.getFileAsStream(
			"songFile");
		String songFileName = uploadPortletRequest.getFileName("songFile");

		InputStream lyricsInputStream = uploadPortletRequest.getFileAsStream(
			"lyricsFile");
		String lyricsFileName = uploadPortletRequest.getFileName("lyricsFile");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Song.class.getName(), uploadPortletRequest);

		try {
			SongServiceUtil.addSong(
				albumId, name, songFileName, songInputStream, lyricsFileName,
				lyricsInputStream, serviceContext);

			SessionMessages.add(uploadPortletRequest, "songAdded");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			if (e instanceof SongNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(uploadPortletRequest, e.getClass().getName());

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

		boolean moveToTrash = ParamUtil.getBoolean(request, "moveToTrash");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Song.class.getName(), request);

		try {
			if (moveToTrash) {
				SongServiceUtil.moveSongToTrash(songId);

				SessionMessages.add(request, "songMovedToTrash");
			}
			else {
				SongServiceUtil.deleteSong(songId, serviceContext);

				SessionMessages.add(request, "songDeleted");
			}

			sendRedirect(request, response);
		}
		catch (Exception e) {
			response.setRenderParameter("jspPage", "/html/error.jsp");
		}
	}

	public void updateSong(ActionRequest request, ActionResponse response)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(request);

		long albumId = ParamUtil.getLong(uploadPortletRequest, "albumId");
		long songId = ParamUtil.getLong(uploadPortletRequest, "songId");
		String name = ParamUtil.getString(uploadPortletRequest, "name");

		InputStream songInputStream = uploadPortletRequest.getFileAsStream(
			"songFile");
		String songFileName = uploadPortletRequest.getFileName("songFile");

		InputStream lyricsInputStream = uploadPortletRequest.getFileAsStream(
			"lyricsFile");
		String lyricsFileName = uploadPortletRequest.getFileName("lyricsFile");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Song.class.getName(), uploadPortletRequest);

		try {
			SongServiceUtil.updateSong(
				songId, albumId, name, songFileName, songInputStream,
				lyricsFileName, lyricsInputStream, serviceContext);

			SessionMessages.add(uploadPortletRequest, "songUpdated");

			sendRedirect(request, response);
		}
		catch (Exception e) {
			if (e instanceof SongNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(uploadPortletRequest, e.getClass().getName());

				response.setRenderParameter(
					"jspPage", "/html/songs/edit_song.jsp");
			}
			else {
				response.setRenderParameter("jspPage", "/html/error.jsp");
			}
		}
	}

	public final static String PORTLET_ID = "songs_WAR_jukeboxportlet";

}