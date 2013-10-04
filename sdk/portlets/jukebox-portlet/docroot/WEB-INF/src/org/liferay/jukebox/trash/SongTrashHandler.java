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

package org.liferay.jukebox.trash;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.trash.BaseTrashHandler;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;

import javax.portlet.PortletRequest;

import com.liferay.portal.theme.ThemeDisplay;
import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.service.SongLocalServiceUtil;
import org.liferay.jukebox.service.permission.SongPermission;

/**
 * Implements trash handling for the songs.
 *
 * @author Sergio González
 */
public class SongTrashHandler extends BaseTrashHandler {

	@Override
	public void deleteTrashEntry(long classPK)
		throws PortalException, SystemException {

		SongLocalServiceUtil.deleteSong(classPK);
	}

	@Override
	public String getClassName() {
		return Song.class.getName();
	}

	@Override
	public String getRestoreMessage(
		PortletRequest portletRequest, long classPK) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return themeDisplay.translate("songs");
	}

	@Override
	public boolean isInTrash(long classPK)
		throws PortalException, SystemException {

		Song song = SongLocalServiceUtil.getSong(classPK);

		return song.isInTrash();
	}

	@Override
	public void restoreTrashEntry(long userId, long classPK)
		throws PortalException, SystemException {

		SongLocalServiceUtil.restoreSongFromTrash(userId, classPK);
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException, SystemException {

		return SongPermission.contains(permissionChecker, classPK, actionId);
	}

}