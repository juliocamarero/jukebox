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
package org.liferay.jukebox.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import org.liferay.jukebox.model.Album;
import org.liferay.jukebox.service.AlbumLocalServiceUtil;

/**
 * @author Julio Camarero
 */
public class AlbumPermission {
	
	public static void check(
			PermissionChecker permissionChecker, long albumId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, albumId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long albumId, String actionId)
		throws PortalException, SystemException {

		Album album = AlbumLocalServiceUtil.getAlbum(albumId);

		return permissionChecker.hasPermission(
			album.getGroupId(), Album.class.getName(), album.getAlbumId(),
			actionId);
	}
}