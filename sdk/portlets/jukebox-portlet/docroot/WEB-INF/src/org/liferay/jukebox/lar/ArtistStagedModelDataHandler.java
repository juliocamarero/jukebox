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

package org.liferay.jukebox.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

import org.liferay.jukebox.model.Artist;
import org.liferay.jukebox.service.ArtistLocalServiceUtil;

/**
 * @author Mate Thurzo
 */
public class ArtistStagedModelDataHandler
	extends BaseStagedModelDataHandler<Artist> {

	public static final String[] CLASS_NAMES = {Artist.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Artist artist = ArtistLocalServiceUtil.fetchArtistByUuidAndGroupId(
			uuid, groupId);

		if (artist != null) {
			ArtistLocalServiceUtil.deleteArtist(artist);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Artist artist) {
		return artist.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Artist artist)
		throws Exception {

		Element artistElement = portletDataContext.getExportDataElement(artist);

		portletDataContext.addClassedModel(
			artistElement, ExportImportPathUtil.getModelPath(artist), artist);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Artist artist)
		throws Exception {

		long userId = portletDataContext.getUserId(artist.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			artist);

		Artist importedArtist = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Artist existingArtist =
				ArtistLocalServiceUtil.fetchArtistByUuidAndGroupId(
					artist.getUuid(), portletDataContext.getScopeGroupId());

			if (existingArtist == null) {
				serviceContext.setUuid(artist.getUuid());

				importedArtist = ArtistLocalServiceUtil.addArtist(
					userId, artist.getName(), artist.getBio(), null,
					serviceContext);
			}
			else {
				importedArtist = ArtistLocalServiceUtil.updateArtist(
					userId, existingArtist.getArtistId(), artist.getName(),
					artist.getBio(), null, serviceContext);
			}
		}
		else {
			importedArtist = ArtistLocalServiceUtil.addArtist(
				userId, artist.getName(), artist.getBio(), null,
				serviceContext);
		}

		portletDataContext.importClassedModel(artist, importedArtist);
	}

	@Override
	protected void doRestoreStagedModel(
			PortletDataContext portletDataContext, Artist artist)
		throws Exception {

		long userId = portletDataContext.getUserId(artist.getUserUuid());

		Artist existingArtist =
			ArtistLocalServiceUtil.fetchArtistByUuidAndGroupId(
				artist.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingArtist == null) || !existingArtist.isInTrash()) {
			return;
		}

		TrashHandler trashHandler = existingArtist.getTrashHandler();

		if (trashHandler.isRestorable(existingArtist.getArtistId())) {
			trashHandler.restoreTrashEntry(
				userId, existingArtist.getArtistId());
		}
	}

	@Override
	protected boolean validateMissingReference(
			String uuid, long companyId, long groupId)
		throws Exception {

		Artist artist = ArtistLocalServiceUtil.fetchArtistByUuidAndGroupId(
			uuid, groupId);

		if (artist == null) {
			return false;
		}

		return true;
	}

}