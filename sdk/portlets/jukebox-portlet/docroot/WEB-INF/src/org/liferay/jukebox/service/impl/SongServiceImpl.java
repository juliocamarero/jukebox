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

package org.liferay.jukebox.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.service.base.SongServiceBaseImpl;
import org.liferay.jukebox.service.permission.JukeBoxPermission;
import org.liferay.jukebox.service.permission.SongPermission;

import java.util.List;

/**
 * The implementation of the song remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.liferay.jukebox.service.SongService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Julio Camarero
 * @see org.liferay.jukebox.service.base.SongServiceBaseImpl
 * @see org.liferay.jukebox.service.SongServiceUtil
 */
public class SongServiceImpl extends SongServiceBaseImpl {
	public Song addSong(
			long albumId, String name, ServiceContext serviceContext)
		throws PortalException, SystemException {

		JukeBoxPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			"ADD_SONG");

		return songLocalService.addSong(
			getUserId(), albumId, name, serviceContext);
	}

	public List<Song> getSongs(long groupId) throws SystemException {
		return songPersistence.filterFindByGroupId(groupId);
	}

	public List<Song> getSongs(long groupId, int start, int end)
		throws SystemException {

		return songPersistence.filterFindByGroupId(groupId, start, end);
	}

	public int getSongsCount(long groupId) throws SystemException {
		return songPersistence.filterCountByGroupId(groupId);
	}

	public Song updateSong(
			long songId, long albumId, String name,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		SongPermission.check(getPermissionChecker(), songId, "UPDATE");

		return songLocalService.updateSong(
			getUserId(), songId, albumId, name, serviceContext);
	}

	public Song deleteSong(
			long songId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		SongPermission.check(getPermissionChecker(), songId, "DELETE");

		return songLocalService.deleteSong(songId);
	}
}