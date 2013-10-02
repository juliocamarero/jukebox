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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Song}.
 * </p>
 *
 * @author Julio Camarero
 * @see Song
 * @generated
 */
public class SongWrapper implements Song, ModelWrapper<Song> {
	public SongWrapper(Song song) {
		_song = song;
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

	/**
	* Returns the primary key of this song.
	*
	* @return the primary key of this song
	*/
	@Override
	public long getPrimaryKey() {
		return _song.getPrimaryKey();
	}

	/**
	* Sets the primary key of this song.
	*
	* @param primaryKey the primary key of this song
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_song.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this song.
	*
	* @return the uuid of this song
	*/
	@Override
	public java.lang.String getUuid() {
		return _song.getUuid();
	}

	/**
	* Sets the uuid of this song.
	*
	* @param uuid the uuid of this song
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_song.setUuid(uuid);
	}

	/**
	* Returns the song ID of this song.
	*
	* @return the song ID of this song
	*/
	@Override
	public long getSongId() {
		return _song.getSongId();
	}

	/**
	* Sets the song ID of this song.
	*
	* @param songId the song ID of this song
	*/
	@Override
	public void setSongId(long songId) {
		_song.setSongId(songId);
	}

	/**
	* Returns the company ID of this song.
	*
	* @return the company ID of this song
	*/
	@Override
	public long getCompanyId() {
		return _song.getCompanyId();
	}

	/**
	* Sets the company ID of this song.
	*
	* @param companyId the company ID of this song
	*/
	@Override
	public void setCompanyId(long companyId) {
		_song.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this song.
	*
	* @return the group ID of this song
	*/
	@Override
	public long getGroupId() {
		return _song.getGroupId();
	}

	/**
	* Sets the group ID of this song.
	*
	* @param groupId the group ID of this song
	*/
	@Override
	public void setGroupId(long groupId) {
		_song.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this song.
	*
	* @return the user ID of this song
	*/
	@Override
	public long getUserId() {
		return _song.getUserId();
	}

	/**
	* Sets the user ID of this song.
	*
	* @param userId the user ID of this song
	*/
	@Override
	public void setUserId(long userId) {
		_song.setUserId(userId);
	}

	/**
	* Returns the user uuid of this song.
	*
	* @return the user uuid of this song
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _song.getUserUuid();
	}

	/**
	* Sets the user uuid of this song.
	*
	* @param userUuid the user uuid of this song
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_song.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this song.
	*
	* @return the user name of this song
	*/
	@Override
	public java.lang.String getUserName() {
		return _song.getUserName();
	}

	/**
	* Sets the user name of this song.
	*
	* @param userName the user name of this song
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_song.setUserName(userName);
	}

	/**
	* Returns the create date of this song.
	*
	* @return the create date of this song
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _song.getCreateDate();
	}

	/**
	* Sets the create date of this song.
	*
	* @param createDate the create date of this song
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_song.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this song.
	*
	* @return the modified date of this song
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _song.getModifiedDate();
	}

	/**
	* Sets the modified date of this song.
	*
	* @param modifiedDate the modified date of this song
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_song.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the artist ID of this song.
	*
	* @return the artist ID of this song
	*/
	@Override
	public long getArtistId() {
		return _song.getArtistId();
	}

	/**
	* Sets the artist ID of this song.
	*
	* @param artistId the artist ID of this song
	*/
	@Override
	public void setArtistId(long artistId) {
		_song.setArtistId(artistId);
	}

	/**
	* Returns the album ID of this song.
	*
	* @return the album ID of this song
	*/
	@Override
	public long getAlbumId() {
		return _song.getAlbumId();
	}

	/**
	* Sets the album ID of this song.
	*
	* @param albumId the album ID of this song
	*/
	@Override
	public void setAlbumId(long albumId) {
		_song.setAlbumId(albumId);
	}

	/**
	* Returns the name of this song.
	*
	* @return the name of this song
	*/
	@Override
	public java.lang.String getName() {
		return _song.getName();
	}

	/**
	* Sets the name of this song.
	*
	* @param name the name of this song
	*/
	@Override
	public void setName(java.lang.String name) {
		_song.setName(name);
	}

	@Override
	public boolean isNew() {
		return _song.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_song.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _song.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_song.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _song.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _song.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_song.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _song.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_song.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_song.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_song.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SongWrapper((Song)_song.clone());
	}

	@Override
	public int compareTo(Song song) {
		return _song.compareTo(song);
	}

	@Override
	public int hashCode() {
		return _song.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<Song> toCacheModel() {
		return _song.toCacheModel();
	}

	@Override
	public Song toEscapedModel() {
		return new SongWrapper(_song.toEscapedModel());
	}

	@Override
	public Song toUnescapedModel() {
		return new SongWrapper(_song.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _song.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _song.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_song.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SongWrapper)) {
			return false;
		}

		SongWrapper songWrapper = (SongWrapper)obj;

		if (Validator.equals(_song, songWrapper._song)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _song.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Song getWrappedSong() {
		return _song;
	}

	@Override
	public Song getWrappedModel() {
		return _song;
	}

	@Override
	public void resetOriginalValues() {
		_song.resetOriginalValues();
	}

	private Song _song;
}