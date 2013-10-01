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
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import org.liferay.jukebox.SongNameException;
import org.liferay.jukebox.model.Album;
import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.service.base.SongLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the song local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.liferay.jukebox.service.SongLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Julio Camarero
 * @see org.liferay.jukebox.service.base.SongLocalServiceBaseImpl
 * @see org.liferay.jukebox.service.SongLocalServiceUtil
 */
public class SongLocalServiceImpl extends SongLocalServiceBaseImpl {
	public Song addSong(
			long userId, long albumId, String name,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(name);

		long songId = counterLocalService.increment(Song.class.getName());

		Song song = songPersistence.create(songId);

		song.setUuid(serviceContext.getUuid());
		song.setGroupId(serviceContext.getScopeGroupId());
		song.setCompanyId(user.getCompanyId());
		song.setUserId(user.getUserId());
		song.setUserName(user.getFullName());
		song.setCreateDate(serviceContext.getCreateDate(now));
		song.setModifiedDate(serviceContext.getModifiedDate(now));

		Album album = albumPersistence.findByPrimaryKey(albumId);

		song.setArtistId(album.getArtistId());
		song.setAlbumId(albumId);
		song.setName(name);
		song.setExpandoBridgeAttributes(serviceContext);

		songPersistence.update(song);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addEntryResources(
				song, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addEntryResources(
				song, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Asset

		updateAsset(
			userId, song, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return song;
	}

	@Override
	public void addEntryResources(
			Song song, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			song.getCompanyId(), song.getGroupId(), song.getUserId(),
			Song.class.getName(), song.getSongId(), false,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addEntryResources(
			Song song, String[] groupPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			song.getCompanyId(), song.getGroupId(), song.getUserId(),
			Song.class.getName(), song.getSongId(), groupPermissions,
			guestPermissions);
	}

	public List<Song> getSongs(long groupId, int start, int end)
		throws SystemException {

		return songPersistence.findByGroupId(groupId, start, end);
	}

	public List<Song> getSongs(long groupId) throws SystemException {
		return songPersistence.findByGroupId(groupId);
	}

	public int getSongsCount(long groupId) throws SystemException {
		return songPersistence.countByGroupId(groupId);
	}

	public Song updateSong(
			long userId, long songId, long albumId, String name,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Event

		User user = userPersistence.findByPrimaryKey(userId);

		validate(name);

		Song song = songPersistence.findByPrimaryKey(songId);

		song.setModifiedDate(serviceContext.getModifiedDate(null));

		Album album = albumPersistence.findByPrimaryKey(albumId);

		song.setArtistId(album.getArtistId());
		song.setAlbumId(albumId);

		song.setName(name);
		song.setExpandoBridgeAttributes(serviceContext);

		songPersistence.update(song);

		// Asset

		updateAsset(
			userId, song, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return song;
	}

	public void updateAsset(
			long userId, Song song, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException, SystemException {

		assetEntryLocalService.updateEntry(
			userId, song.getGroupId(), song.getCreateDate(),
			song.getModifiedDate(), Song.class.getName(),
			song.getSongId(), song.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, null, null, null, ContentTypes.TEXT_HTML,
			song.getName(), null, null, null, null, 0, 0, null, false);
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new SongNameException();
		}
	}
}