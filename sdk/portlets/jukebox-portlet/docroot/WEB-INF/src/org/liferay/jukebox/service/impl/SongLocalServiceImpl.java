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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Repository;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.util.DLProcessorRegistryUtil;

import java.io.InputStream;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.liferay.jukebox.SongNameException;
import org.liferay.jukebox.model.Album;
import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.service.base.SongLocalServiceBaseImpl;
import org.liferay.jukebox.util.Constants;

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
 * @author Sergio González
 * @author Eudaldo Alonso
 * @see org.liferay.jukebox.service.base.SongLocalServiceBaseImpl
 * @see org.liferay.jukebox.service.SongLocalServiceUtil
 */
public class SongLocalServiceImpl extends SongLocalServiceBaseImpl {

	@Override
	public void addEntryResources(
			Song song, boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			song.getCompanyId(), song.getGroupId(), song.getUserId(),
			Song.class.getName(), song.getSongId(), false, addGroupPermissions,
			addGuestPermissions);
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

	@Indexable(type = IndexableType.REINDEX)
	public Song addSong(
			long userId, long albumId, String name, String songFileName,
			InputStream songInputStream, String lyricsFileName,
			InputStream lyricsInputStream, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long groupId = serviceContext.getScopeGroupId();

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(name);

		long songId = counterLocalService.increment();

		Song song = songPersistence.create(songId);

		song.setUuid(serviceContext.getUuid());
		song.setGroupId(groupId);
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

		if ((songInputStream != null) || (lyricsInputStream != null)) {
			Repository repository =
				PortletFileRepositoryUtil.addPortletRepository(
					groupId, Constants.JUKEBOX_PORTLET_REPOSITORY,
					serviceContext);

			Folder folder = PortletFileRepositoryUtil.addPortletFolder(
				userId, repository.getRepositoryId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				String.valueOf(song.getSongId()), serviceContext);

			if (songInputStream != null) {
				Folder songFolder = PortletFileRepositoryUtil.addPortletFolder(
					userId, repository.getRepositoryId(), folder.getFolderId(),
					Constants.SONGS_FOLDER_NAME, serviceContext);

				FileEntry fileEntry =
					PortletFileRepositoryUtil.addPortletFileEntry(
						groupId, userId, Song.class.getName(), song.getSongId(),
						Constants.JUKEBOX_PORTLET_REPOSITORY,
						songFolder.getFolderId(), songInputStream, songFileName,
						StringPool.BLANK, true);

				DLProcessorRegistryUtil.trigger(fileEntry, null, true);
			}

			if (lyricsInputStream != null) {
				Folder lyricsFolder =
					PortletFileRepositoryUtil.addPortletFolder(
						userId, repository.getRepositoryId(),
						folder.getFolderId(), Constants.LYRICS_FOLDER_NAME,
						serviceContext);

				FileEntry fileEntry =
					PortletFileRepositoryUtil.addPortletFileEntry(
						groupId, userId, Song.class.getName(), song.getSongId(),
						Constants.JUKEBOX_PORTLET_REPOSITORY,
						lyricsFolder.getFolderId(), lyricsInputStream,
						lyricsFileName, StringPool.BLANK, true);

				DLProcessorRegistryUtil.trigger(fileEntry, null, true);
			}
		}

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

	public Song deleteSong(long songId)
		throws PortalException, SystemException {

		Song song = songPersistence.findByPrimaryKey(songId);

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				song.getGroupId(), Constants.JUKEBOX_PORTLET_REPOSITORY);

		if (repository != null) {
			try {
				Folder folder = PortletFileRepositoryUtil.getPortletFolder(
					0, repository.getRepositoryId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					String.valueOf(songId), null);

				PortletFileRepositoryUtil.deleteFolder(folder.getFolderId());
			}
			catch (Exception e) {
			}
		}

		return songPersistence.remove(songId);
	}

	public List<Song> getSongs(long groupId) throws SystemException {
		return songPersistence.findByGroupId(groupId);
	}

	public List<Song> getSongs(long groupId, int start, int end)
		throws SystemException {

		return songPersistence.findByGroupId(groupId, start, end);
	}

	public List<Song> getSongsByAlbumId(long albumId) throws SystemException {
		return songPersistence.findByAlbumId(albumId);
	}

	public int getSongsCount(long groupId) throws SystemException {
		return songPersistence.countByGroupId(groupId);
	}

	public void updateAsset(
			long userId, Song song, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException, SystemException {

		assetEntryLocalService.updateEntry(
			userId, song.getGroupId(), song.getCreateDate(),
			song.getModifiedDate(), Song.class.getName(), song.getSongId(),
			song.getUuid(), 0, assetCategoryIds, assetTagNames, true, null,
			null, null, ContentTypes.TEXT_HTML, song.getName(), null, null,
			null, null, 0, 0, null, false);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Song updateSong(
			long userId, long songId, long albumId, String name,
			String songFileName, InputStream songInputStream,
			String lyricsFileName, InputStream lyricsInputStream,
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

		if ((songInputStream != null) || (lyricsInputStream != null)) {
			Repository repository =
				PortletFileRepositoryUtil.addPortletRepository(
					serviceContext.getScopeGroupId(),
					Constants.JUKEBOX_PORTLET_REPOSITORY, serviceContext);

			Folder folder = PortletFileRepositoryUtil.addPortletFolder(
				userId, repository.getRepositoryId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				String.valueOf(song.getSongId()), serviceContext);

			if (songInputStream != null) {
				Folder songFolder = PortletFileRepositoryUtil.addPortletFolder(
					userId, repository.getRepositoryId(), folder.getFolderId(),
					Constants.SONGS_FOLDER_NAME, serviceContext);

				List<FileEntry> fileEntries =
					PortletFileRepositoryUtil.getPortletFileEntries(
						serviceContext.getScopeGroupId(),
						songFolder.getFolderId());

				for (FileEntry fileEntry : fileEntries) {
					PortletFileRepositoryUtil.deletePortletFileEntry(
						fileEntry.getFileEntryId());

					DLProcessorRegistryUtil.cleanUp(fileEntry);
				}

				FileEntry fileEntry =
					PortletFileRepositoryUtil.addPortletFileEntry(
						serviceContext.getScopeGroupId(), userId,
						Song.class.getName(), song.getSongId(),
						Constants.JUKEBOX_PORTLET_REPOSITORY,
						songFolder.getFolderId(), songInputStream, songFileName,
						StringPool.BLANK, true);

				triggerDLProcessors(fileEntry);
			}

			if (lyricsInputStream != null) {
				Folder lyricsFolder =
					PortletFileRepositoryUtil.addPortletFolder(
						userId, repository.getRepositoryId(),
						folder.getFolderId(), Constants.LYRICS_FOLDER_NAME,
						serviceContext);

				List<FileEntry> fileEntries =
					PortletFileRepositoryUtil.getPortletFileEntries(
						serviceContext.getScopeGroupId(),
						lyricsFolder.getFolderId());

				for (FileEntry fileEntry : fileEntries) {
					PortletFileRepositoryUtil.deletePortletFileEntry(
						fileEntry.getFileEntryId());

					DLProcessorRegistryUtil.cleanUp(fileEntry);
				}

				FileEntry fileEntry =
					PortletFileRepositoryUtil.addPortletFileEntry(
						serviceContext.getScopeGroupId(), userId,
						Song.class.getName(), song.getSongId(),
						Constants.JUKEBOX_PORTLET_REPOSITORY,
						lyricsFolder.getFolderId(), lyricsInputStream,
						lyricsFileName, StringPool.BLANK, true);

				triggerDLProcessors(fileEntry);
			}
		}

		// Asset

		updateAsset(
			userId, song, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return song;
	}

	protected void triggerDLProcessors(final FileEntry fileEntry) {
		TransactionCommitCallbackRegistryUtil.registerCallback(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					DLProcessorRegistryUtil.trigger(fileEntry, null, true);

					return null;
				}

			});
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new SongNameException();
		}
	}

}