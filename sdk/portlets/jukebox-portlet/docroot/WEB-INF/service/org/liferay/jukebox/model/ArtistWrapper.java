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
 * This class is a wrapper for {@link Artist}.
 * </p>
 *
 * @author Julio Camarero
 * @see Artist
 * @generated
 */
public class ArtistWrapper implements Artist, ModelWrapper<Artist> {
	public ArtistWrapper(Artist artist) {
		_artist = artist;
	}

	@Override
	public Class<?> getModelClass() {
		return Artist.class;
	}

	@Override
	public String getModelClassName() {
		return Artist.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("artistId", getArtistId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long artistId = (Long)attributes.get("artistId");

		if (artistId != null) {
			setArtistId(artistId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	* Returns the primary key of this artist.
	*
	* @return the primary key of this artist
	*/
	@Override
	public long getPrimaryKey() {
		return _artist.getPrimaryKey();
	}

	/**
	* Sets the primary key of this artist.
	*
	* @param primaryKey the primary key of this artist
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_artist.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this artist.
	*
	* @return the uuid of this artist
	*/
	@Override
	public java.lang.String getUuid() {
		return _artist.getUuid();
	}

	/**
	* Sets the uuid of this artist.
	*
	* @param uuid the uuid of this artist
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_artist.setUuid(uuid);
	}

	/**
	* Returns the artist ID of this artist.
	*
	* @return the artist ID of this artist
	*/
	@Override
	public long getArtistId() {
		return _artist.getArtistId();
	}

	/**
	* Sets the artist ID of this artist.
	*
	* @param artistId the artist ID of this artist
	*/
	@Override
	public void setArtistId(long artistId) {
		_artist.setArtistId(artistId);
	}

	/**
	* Returns the company ID of this artist.
	*
	* @return the company ID of this artist
	*/
	@Override
	public long getCompanyId() {
		return _artist.getCompanyId();
	}

	/**
	* Sets the company ID of this artist.
	*
	* @param companyId the company ID of this artist
	*/
	@Override
	public void setCompanyId(long companyId) {
		_artist.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this artist.
	*
	* @return the group ID of this artist
	*/
	@Override
	public long getGroupId() {
		return _artist.getGroupId();
	}

	/**
	* Sets the group ID of this artist.
	*
	* @param groupId the group ID of this artist
	*/
	@Override
	public void setGroupId(long groupId) {
		_artist.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this artist.
	*
	* @return the user ID of this artist
	*/
	@Override
	public long getUserId() {
		return _artist.getUserId();
	}

	/**
	* Sets the user ID of this artist.
	*
	* @param userId the user ID of this artist
	*/
	@Override
	public void setUserId(long userId) {
		_artist.setUserId(userId);
	}

	/**
	* Returns the user uuid of this artist.
	*
	* @return the user uuid of this artist
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _artist.getUserUuid();
	}

	/**
	* Sets the user uuid of this artist.
	*
	* @param userUuid the user uuid of this artist
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_artist.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this artist.
	*
	* @return the user name of this artist
	*/
	@Override
	public java.lang.String getUserName() {
		return _artist.getUserName();
	}

	/**
	* Sets the user name of this artist.
	*
	* @param userName the user name of this artist
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_artist.setUserName(userName);
	}

	/**
	* Returns the create date of this artist.
	*
	* @return the create date of this artist
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _artist.getCreateDate();
	}

	/**
	* Sets the create date of this artist.
	*
	* @param createDate the create date of this artist
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_artist.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this artist.
	*
	* @return the modified date of this artist
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _artist.getModifiedDate();
	}

	/**
	* Sets the modified date of this artist.
	*
	* @param modifiedDate the modified date of this artist
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_artist.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this artist.
	*
	* @return the name of this artist
	*/
	@Override
	public java.lang.String getName() {
		return _artist.getName();
	}

	/**
	* Sets the name of this artist.
	*
	* @param name the name of this artist
	*/
	@Override
	public void setName(java.lang.String name) {
		_artist.setName(name);
	}

	@Override
	public boolean isNew() {
		return _artist.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_artist.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _artist.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_artist.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _artist.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _artist.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_artist.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _artist.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_artist.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_artist.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_artist.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ArtistWrapper((Artist)_artist.clone());
	}

	@Override
	public int compareTo(org.liferay.jukebox.model.Artist artist) {
		return _artist.compareTo(artist);
	}

	@Override
	public int hashCode() {
		return _artist.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.liferay.jukebox.model.Artist> toCacheModel() {
		return _artist.toCacheModel();
	}

	@Override
	public org.liferay.jukebox.model.Artist toEscapedModel() {
		return new ArtistWrapper(_artist.toEscapedModel());
	}

	@Override
	public org.liferay.jukebox.model.Artist toUnescapedModel() {
		return new ArtistWrapper(_artist.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _artist.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _artist.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_artist.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArtistWrapper)) {
			return false;
		}

		ArtistWrapper artistWrapper = (ArtistWrapper)obj;

		if (Validator.equals(_artist, artistWrapper._artist)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _artist.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Artist getWrappedArtist() {
		return _artist;
	}

	@Override
	public Artist getWrappedModel() {
		return _artist;
	}

	@Override
	public void resetOriginalValues() {
		_artist.resetOriginalValues();
	}

	private Artist _artist;
}