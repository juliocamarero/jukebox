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

import org.liferay.jukebox.service.ArtistServiceUtil;

import java.util.Arrays;

/**
 * @author Julio Camarero
 * @generated
 */
public class ArtistServiceClpInvoker {
	public ArtistServiceClpInvoker() {
		_methodName66 = "getBeanIdentifier";

		_methodParameterTypes66 = new String[] {  };

		_methodName67 = "setBeanIdentifier";

		_methodParameterTypes67 = new String[] { "java.lang.String" };

		_methodName72 = "addArtist";

		_methodParameterTypes72 = new String[] {
				"java.lang.String", "java.lang.String", "java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName73 = "deleteArtist";

		_methodParameterTypes73 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName74 = "getArtists";

		_methodParameterTypes74 = new String[] { "long" };

		_methodName75 = "getArtists";

		_methodParameterTypes75 = new String[] { "long", "int", "int" };

		_methodName76 = "getArtists";

		_methodParameterTypes76 = new String[] { "long", "java.lang.String" };

		_methodName77 = "getArtistsCount";

		_methodParameterTypes77 = new String[] { "long" };

		_methodName78 = "getArtistsCount";

		_methodParameterTypes78 = new String[] { "long", "java.lang.String" };

		_methodName79 = "updateArtist";

		_methodParameterTypes79 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return ArtistServiceUtil.getBeanIdentifier();
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			ArtistServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return ArtistServiceUtil.addArtist((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				(java.io.InputStream)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return ArtistServiceUtil.deleteArtist(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return ArtistServiceUtil.getArtists(((Long)arguments[0]).longValue());
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return ArtistServiceUtil.getArtists(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return ArtistServiceUtil.getArtists(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return ArtistServiceUtil.getArtistsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return ArtistServiceUtil.getArtistsCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return ArtistServiceUtil.updateArtist(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.io.InputStream)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
}