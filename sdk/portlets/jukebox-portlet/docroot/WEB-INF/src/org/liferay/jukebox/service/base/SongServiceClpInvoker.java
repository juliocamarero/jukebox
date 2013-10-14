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

import org.liferay.jukebox.service.SongServiceUtil;

import java.util.Arrays;

/**
 * @author Julio Camarero
 * @generated
 */
public class SongServiceClpInvoker {
	public SongServiceClpInvoker() {
		_methodName76 = "getBeanIdentifier";

		_methodParameterTypes76 = new String[] {  };

		_methodName77 = "setBeanIdentifier";

		_methodParameterTypes77 = new String[] { "java.lang.String" };

		_methodName82 = "addSong";

		_methodParameterTypes82 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.io.InputStream", "java.lang.String", "java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName83 = "deleteSong";

		_methodParameterTypes83 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName84 = "getSongs";

		_methodParameterTypes84 = new String[] { "long" };

		_methodName85 = "getSongs";

		_methodParameterTypes85 = new String[] { "long", "int", "int" };

		_methodName86 = "getSongs";

		_methodParameterTypes86 = new String[] { "long", "java.lang.String" };

		_methodName87 = "getSongsByAlbumId";

		_methodParameterTypes87 = new String[] { "long", "long" };

		_methodName88 = "getSongsByAlbumId";

		_methodParameterTypes88 = new String[] { "long", "long", "int" };

		_methodName89 = "getSongsCount";

		_methodParameterTypes89 = new String[] { "long" };

		_methodName90 = "getSongsCount";

		_methodParameterTypes90 = new String[] { "long", "java.lang.String" };

		_methodName91 = "getSongsCountByAlbumId";

		_methodParameterTypes91 = new String[] { "long", "long" };

		_methodName92 = "getSongsCountByAlbumId";

		_methodParameterTypes92 = new String[] { "long", "long", "int" };

		_methodName93 = "moveSongToTrash";

		_methodParameterTypes93 = new String[] { "long" };

		_methodName94 = "restoreSongFromTrash";

		_methodParameterTypes94 = new String[] { "long" };

		_methodName95 = "updateSong";

		_methodParameterTypes95 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.io.InputStream", "java.lang.String", "java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return SongServiceUtil.getBeanIdentifier();
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			SongServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return SongServiceUtil.addSong(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.io.InputStream)arguments[3],
				(java.lang.String)arguments[4],
				(java.io.InputStream)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return SongServiceUtil.deleteSong(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return SongServiceUtil.getSongs(((Long)arguments[0]).longValue());
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return SongServiceUtil.getSongs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return SongServiceUtil.getSongs(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return SongServiceUtil.getSongsByAlbumId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return SongServiceUtil.getSongsByAlbumId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return SongServiceUtil.getSongsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return SongServiceUtil.getSongsCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return SongServiceUtil.getSongsCountByAlbumId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return SongServiceUtil.getSongsCountByAlbumId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return SongServiceUtil.moveSongToTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return SongServiceUtil.restoreSongFromTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return SongServiceUtil.updateSong(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.io.InputStream)arguments[4],
				(java.lang.String)arguments[5],
				(java.io.InputStream)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
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
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
}