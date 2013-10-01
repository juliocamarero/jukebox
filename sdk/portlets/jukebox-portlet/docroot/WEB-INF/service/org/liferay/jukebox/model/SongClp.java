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

package org.liferay.jukebox.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.liferay.jukebox.service.ClpSerializer;
import org.liferay.jukebox.service.SongLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Julio Camarero
 */
public class SongClp extends BaseModelImpl<Song> implements Song {
	public SongClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Song.class;
	}

	@Override
	public String getModelClassName() {
		return Song.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _songId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSongId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _songId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("songId", getSongId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("artistId", getArtistId());
		attributes.put("albumId", getAlbumId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long songId = (Long)attributes.get("songId");

		if (songId != null) {
			setSongId(songId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long artistId = (Long)attributes.get("artistId");

		if (artistId != null) {
			setArtistId(artistId);
		}

		Long albumId = (Long)attributes.get("albumId");

		if (albumId != null) {
			setAlbumId(albumId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_songRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSongId() {
		return _songId;
	}

	@Override
	public void setSongId(long songId) {
		_songId = songId;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setSongId", long.class);

				method.invoke(_songRemoteModel, songId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_songRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_songRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_songRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_songRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_songRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_songRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getArtistId() {
		return _artistId;
	}

	@Override
	public void setArtistId(long artistId) {
		_artistId = artistId;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setArtistId", long.class);

				method.invoke(_songRemoteModel, artistId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAlbumId() {
		return _albumId;
	}

	@Override
	public void setAlbumId(long albumId) {
		_albumId = albumId;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setAlbumId", long.class);

				method.invoke(_songRemoteModel, albumId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_songRemoteModel != null) {
			try {
				Class<?> clazz = _songRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_songRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Song.class.getName()));
	}

	public BaseModel<?> getSongRemoteModel() {
		return _songRemoteModel;
	}

	public void setSongRemoteModel(BaseModel<?> songRemoteModel) {
		_songRemoteModel = songRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _songRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_songRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SongLocalServiceUtil.addSong(this);
		}
		else {
			SongLocalServiceUtil.updateSong(this);
		}
	}

	@Override
	public Song toEscapedModel() {
		return (Song)ProxyUtil.newProxyInstance(Song.class.getClassLoader(),
			new Class[] { Song.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SongClp clone = new SongClp();

		clone.setUuid(getUuid());
		clone.setSongId(getSongId());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setArtistId(getArtistId());
		clone.setAlbumId(getAlbumId());
		clone.setName(getName());

		return clone;
	}

	@Override
	public int compareTo(Song song) {
		long primaryKey = song.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SongClp)) {
			return false;
		}

		SongClp song = (SongClp)obj;

		long primaryKey = song.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", songId=");
		sb.append(getSongId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", artistId=");
		sb.append(getArtistId());
		sb.append(", albumId=");
		sb.append(getAlbumId());
		sb.append(", name=");
		sb.append(getName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("org.liferay.jukebox.model.Song");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>songId</column-name><column-value><![CDATA[");
		sb.append(getSongId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>artistId</column-name><column-value><![CDATA[");
		sb.append(getArtistId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>albumId</column-name><column-value><![CDATA[");
		sb.append(getAlbumId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _songId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _artistId;
	private long _albumId;
	private String _name;
	private BaseModel<?> _songRemoteModel;
}