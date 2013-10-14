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

package org.liferay.jukebox.service.base;

import org.liferay.jukebox.service.AlbumServiceUtil;

import java.util.Arrays;

/**
 * @author Julio Camarero
 * @generated
 */
public class AlbumServiceClpInvoker {
	public AlbumServiceClpInvoker() {
		_methodName76 = "getBeanIdentifier";

		_methodParameterTypes76 = new String[] {  };

		_methodName77 = "setBeanIdentifier";

		_methodParameterTypes77 = new String[] { "java.lang.String" };

		_methodName82 = "addAlbum";

		_methodParameterTypes82 = new String[] {
				"long", "java.lang.String", "int", "java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName83 = "deleteAlbum";

		_methodParameterTypes83 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName84 = "getAlbums";

		_methodParameterTypes84 = new String[] { "long" };

		_methodName85 = "getAlbums";

		_methodParameterTypes85 = new String[] { "long", "int", "int" };

		_methodName86 = "getAlbums";

		_methodParameterTypes86 = new String[] { "long", "java.lang.String" };

		_methodName87 = "getAlbumsByArtistId";

		_methodParameterTypes87 = new String[] { "long", "long" };

		_methodName88 = "getAlbumsCount";

		_methodParameterTypes88 = new String[] { "long" };

		_methodName89 = "getAlbumsCount";

		_methodParameterTypes89 = new String[] { "long", "java.lang.String" };

		_methodName90 = "getAlbumsCountByArtistId";

		_methodParameterTypes90 = new String[] { "long", "long" };

		_methodName91 = "moveAlbumToTrash";

		_methodParameterTypes91 = new String[] { "long" };

		_methodName92 = "restoreAlbumFromTrash";

		_methodParameterTypes92 = new String[] { "long" };

		_methodName93 = "updateAlbum";

		_methodParameterTypes93 = new String[] {
				"long", "long", "java.lang.String", "int", "java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return AlbumServiceUtil.getBeanIdentifier();
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			AlbumServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return AlbumServiceUtil.addAlbum(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(java.io.InputStream)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return AlbumServiceUtil.deleteAlbum(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return AlbumServiceUtil.getAlbums(((Long)arguments[0]).longValue());
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return AlbumServiceUtil.getAlbums(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return AlbumServiceUtil.getAlbums(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return AlbumServiceUtil.getAlbumsByArtistId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return AlbumServiceUtil.getAlbumsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return AlbumServiceUtil.getAlbumsCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return AlbumServiceUtil.getAlbumsCountByArtistId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return AlbumServiceUtil.moveAlbumToTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return AlbumServiceUtil.restoreAlbumFromTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return AlbumServiceUtil.updateAlbum(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.io.InputStream)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
}