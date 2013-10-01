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

package org.liferay.jukebox.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.liferay.jukebox.NoSuchArtistException;
import org.liferay.jukebox.model.Artist;
import org.liferay.jukebox.model.impl.ArtistImpl;
import org.liferay.jukebox.model.impl.ArtistModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the artist service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Julio Camarero
 * @see ArtistPersistence
 * @see ArtistUtil
 * @generated
 */
public class ArtistPersistenceImpl extends BasePersistenceImpl<Artist>
	implements ArtistPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ArtistUtil} to access the artist persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ArtistImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ArtistModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the artists where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artists where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @return the range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artists where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<Artist> list = (List<Artist>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Artist artist : list) {
				if (!Validator.equals(uuid, artist.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ARTIST_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ArtistModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Artist>(list);
				}
				else {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first artist in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByUuid_First(uuid, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the first artist in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Artist> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artist in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByUuid_Last(uuid, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the last artist in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Artist> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artists before and after the current artist in the ordered set where uuid = &#63;.
	 *
	 * @param artistId the primary key of the current artist
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist[] findByUuid_PrevAndNext(long artistId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = findByPrimaryKey(artistId);

		Session session = null;

		try {
			session = openSession();

			Artist[] array = new ArtistImpl[3];

			array[0] = getByUuid_PrevAndNext(session, artist, uuid,
					orderByComparator, true);

			array[1] = artist;

			array[2] = getByUuid_PrevAndNext(session, artist, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Artist getByUuid_PrevAndNext(Session session, Artist artist,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTIST_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ArtistModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(artist);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Artist> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artists where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (Artist artist : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(artist);
		}
	}

	/**
	 * Returns the number of artists where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ARTIST_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "artist.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "artist.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(artist.uuid IS NULL OR artist.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ArtistModelImpl.UUID_COLUMN_BITMASK |
			ArtistModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the artist where uuid = &#63; and groupId = &#63; or throws a {@link org.liferay.jukebox.NoSuchArtistException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByUUID_G(String uuid, long groupId)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByUUID_G(uuid, groupId);

		if (artist == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArtistException(msg.toString());
		}

		return artist;
	}

	/**
	 * Returns the artist where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the artist where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Artist) {
			Artist artist = (Artist)result;

			if (!Validator.equals(uuid, artist.getUuid()) ||
					(groupId != artist.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ARTIST_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Artist> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Artist artist = list.get(0);

					result = artist;

					cacheResult(artist);

					if ((artist.getUuid() == null) ||
							!artist.getUuid().equals(uuid) ||
							(artist.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, artist);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Artist)result;
		}
	}

	/**
	 * Removes the artist where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the artist that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist removeByUUID_G(String uuid, long groupId)
		throws NoSuchArtistException, SystemException {
		Artist artist = findByUUID_G(uuid, groupId);

		return remove(artist);
	}

	/**
	 * Returns the number of artists where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTIST_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "artist.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "artist.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(artist.uuid IS NULL OR artist.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "artist.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ArtistModelImpl.UUID_COLUMN_BITMASK |
			ArtistModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the artists where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artists where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @return the range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUuid_C(String uuid, long companyId, int start,
		int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artists where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<Artist> list = (List<Artist>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Artist artist : list) {
				if (!Validator.equals(uuid, artist.getUuid()) ||
						(companyId != artist.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ARTIST_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ArtistModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Artist>(list);
				}
				else {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first artist in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the first artist in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Artist> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artist in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the last artist in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Artist> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artists before and after the current artist in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param artistId the primary key of the current artist
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist[] findByUuid_C_PrevAndNext(long artistId, String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = findByPrimaryKey(artistId);

		Session session = null;

		try {
			session = openSession();

			Artist[] array = new ArtistImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, artist, uuid,
					companyId, orderByComparator, true);

			array[1] = artist;

			array[2] = getByUuid_C_PrevAndNext(session, artist, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Artist getByUuid_C_PrevAndNext(Session session, Artist artist,
		String uuid, long companyId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTIST_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ArtistModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(artist);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Artist> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artists where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (Artist artist : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(artist);
		}
	}

	/**
	 * Returns the number of artists where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTIST_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "artist.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "artist.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(artist.uuid IS NULL OR artist.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "artist.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ArtistModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the artists where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByGroupId(long groupId) throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artists where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @return the range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artists where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Artist> list = (List<Artist>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Artist artist : list) {
				if ((groupId != artist.getGroupId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ARTIST_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ArtistModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Artist>(list);
				}
				else {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first artist in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByGroupId_First(groupId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the first artist in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Artist> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artist in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByGroupId_Last(groupId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the last artist in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Artist> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artists before and after the current artist in the ordered set where groupId = &#63;.
	 *
	 * @param artistId the primary key of the current artist
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist[] findByGroupId_PrevAndNext(long artistId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = findByPrimaryKey(artistId);

		Session session = null;

		try {
			session = openSession();

			Artist[] array = new ArtistImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, artist, groupId,
					orderByComparator, true);

			array[1] = artist;

			array[2] = getByGroupId_PrevAndNext(session, artist, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Artist getByGroupId_PrevAndNext(Session session, Artist artist,
		long groupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTIST_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ArtistModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(artist);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Artist> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artists where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (Artist artist : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(artist);
		}
	}

	/**
	 * Returns the number of artists where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ARTIST_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "artist.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			ArtistModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the artists where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artists where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @return the range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artists where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<Artist> list = (List<Artist>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Artist artist : list) {
				if ((userId != artist.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ARTIST_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ArtistModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Artist>(list);
				}
				else {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first artist in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByUserId_First(userId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the first artist in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Artist> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artist in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByUserId_Last(userId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the last artist in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<Artist> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artists before and after the current artist in the ordered set where userId = &#63;.
	 *
	 * @param artistId the primary key of the current artist
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist[] findByUserId_PrevAndNext(long artistId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = findByPrimaryKey(artistId);

		Session session = null;

		try {
			session = openSession();

			Artist[] array = new ArtistImpl[3];

			array[0] = getByUserId_PrevAndNext(session, artist, userId,
					orderByComparator, true);

			array[1] = artist;

			array[2] = getByUserId_PrevAndNext(session, artist, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Artist getByUserId_PrevAndNext(Session session, Artist artist,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTIST_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ArtistModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(artist);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Artist> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artists where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (Artist artist : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(artist);
		}
	}

	/**
	 * Returns the number of artists where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ARTIST_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "artist.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			ArtistModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the artists where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the artists where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @return the range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artists where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<Artist> list = (List<Artist>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Artist artist : list) {
				if ((companyId != artist.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ARTIST_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ArtistModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Artist>(list);
				}
				else {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first artist in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByCompanyId_First(companyId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the first artist in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Artist> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artist in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByCompanyId_Last(companyId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the last artist in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<Artist> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artists before and after the current artist in the ordered set where companyId = &#63;.
	 *
	 * @param artistId the primary key of the current artist
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist[] findByCompanyId_PrevAndNext(long artistId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = findByPrimaryKey(artistId);

		Session session = null;

		try {
			session = openSession();

			Artist[] array = new ArtistImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, artist, companyId,
					orderByComparator, true);

			array[1] = artist;

			array[2] = getByCompanyId_PrevAndNext(session, artist, companyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Artist getByCompanyId_PrevAndNext(Session session, Artist artist,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTIST_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ArtistModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(artist);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Artist> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artists where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (Artist artist : findByCompanyId(companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(artist);
		}
	}

	/**
	 * Returns the number of artists where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCompanyId(long companyId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ARTIST_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "artist.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_G = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, ArtistImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_G",
			new String[] { Long.class.getName(), Long.class.getName() },
			ArtistModelImpl.USERID_COLUMN_BITMASK |
			ArtistModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_G = new FinderPath(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_G",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the artists where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByU_G(long userId, long groupId)
		throws SystemException {
		return findByU_G(userId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the artists where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @return the range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByU_G(long userId, long groupId, int start, int end)
		throws SystemException {
		return findByU_G(userId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the artists where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findByU_G(long userId, long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G;
			finderArgs = new Object[] { userId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_G;
			finderArgs = new Object[] {
					userId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<Artist> list = (List<Artist>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Artist artist : list) {
				if ((userId != artist.getUserId()) ||
						(groupId != artist.getGroupId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ARTIST_WHERE);

			query.append(_FINDER_COLUMN_U_G_USERID_2);

			query.append(_FINDER_COLUMN_U_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ArtistModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Artist>(list);
				}
				else {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first artist in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByU_G_First(long userId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByU_G_First(userId, groupId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the first artist in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByU_G_First(long userId, long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Artist> list = findByU_G(userId, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last artist in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByU_G_Last(long userId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByU_G_Last(userId, groupId, orderByComparator);

		if (artist != null) {
			return artist;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArtistException(msg.toString());
	}

	/**
	 * Returns the last artist in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching artist, or <code>null</code> if a matching artist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByU_G_Last(long userId, long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_G(userId, groupId);

		if (count == 0) {
			return null;
		}

		List<Artist> list = findByU_G(userId, groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the artists before and after the current artist in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param artistId the primary key of the current artist
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist[] findByU_G_PrevAndNext(long artistId, long userId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchArtistException, SystemException {
		Artist artist = findByPrimaryKey(artistId);

		Session session = null;

		try {
			session = openSession();

			Artist[] array = new ArtistImpl[3];

			array[0] = getByU_G_PrevAndNext(session, artist, userId, groupId,
					orderByComparator, true);

			array[1] = artist;

			array[2] = getByU_G_PrevAndNext(session, artist, userId, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Artist getByU_G_PrevAndNext(Session session, Artist artist,
		long userId, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTIST_WHERE);

		query.append(_FINDER_COLUMN_U_G_USERID_2);

		query.append(_FINDER_COLUMN_U_G_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ArtistModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(artist);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Artist> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the artists where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByU_G(long userId, long groupId)
		throws SystemException {
		for (Artist artist : findByU_G(userId, groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(artist);
		}
	}

	/**
	 * Returns the number of artists where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByU_G(long userId, long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_G;

		Object[] finderArgs = new Object[] { userId, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTIST_WHERE);

			query.append(_FINDER_COLUMN_U_G_USERID_2);

			query.append(_FINDER_COLUMN_U_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_G_USERID_2 = "artist.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GROUPID_2 = "artist.groupId = ?";

	public ArtistPersistenceImpl() {
		setModelClass(Artist.class);
	}

	/**
	 * Caches the artist in the entity cache if it is enabled.
	 *
	 * @param artist the artist
	 */
	@Override
	public void cacheResult(Artist artist) {
		EntityCacheUtil.putResult(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistImpl.class, artist.getPrimaryKey(), artist);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { artist.getUuid(), artist.getGroupId() }, artist);

		artist.resetOriginalValues();
	}

	/**
	 * Caches the artists in the entity cache if it is enabled.
	 *
	 * @param artists the artists
	 */
	@Override
	public void cacheResult(List<Artist> artists) {
		for (Artist artist : artists) {
			if (EntityCacheUtil.getResult(
						ArtistModelImpl.ENTITY_CACHE_ENABLED, ArtistImpl.class,
						artist.getPrimaryKey()) == null) {
				cacheResult(artist);
			}
			else {
				artist.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all artists.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ArtistImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ArtistImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the artist.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Artist artist) {
		EntityCacheUtil.removeResult(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistImpl.class, artist.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(artist);
	}

	@Override
	public void clearCache(List<Artist> artists) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Artist artist : artists) {
			EntityCacheUtil.removeResult(ArtistModelImpl.ENTITY_CACHE_ENABLED,
				ArtistImpl.class, artist.getPrimaryKey());

			clearUniqueFindersCache(artist);
		}
	}

	protected void cacheUniqueFindersCache(Artist artist) {
		if (artist.isNew()) {
			Object[] args = new Object[] { artist.getUuid(), artist.getGroupId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args, artist);
		}
		else {
			ArtistModelImpl artistModelImpl = (ArtistModelImpl)artist;

			if ((artistModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						artist.getUuid(), artist.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					artist);
			}
		}
	}

	protected void clearUniqueFindersCache(Artist artist) {
		ArtistModelImpl artistModelImpl = (ArtistModelImpl)artist;

		Object[] args = new Object[] { artist.getUuid(), artist.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((artistModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					artistModelImpl.getOriginalUuid(),
					artistModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new artist with the primary key. Does not add the artist to the database.
	 *
	 * @param artistId the primary key for the new artist
	 * @return the new artist
	 */
	@Override
	public Artist create(long artistId) {
		Artist artist = new ArtistImpl();

		artist.setNew(true);
		artist.setPrimaryKey(artistId);

		String uuid = PortalUUIDUtil.generate();

		artist.setUuid(uuid);

		return artist;
	}

	/**
	 * Removes the artist with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param artistId the primary key of the artist
	 * @return the artist that was removed
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist remove(long artistId)
		throws NoSuchArtistException, SystemException {
		return remove((Serializable)artistId);
	}

	/**
	 * Removes the artist with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the artist
	 * @return the artist that was removed
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist remove(Serializable primaryKey)
		throws NoSuchArtistException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Artist artist = (Artist)session.get(ArtistImpl.class, primaryKey);

			if (artist == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchArtistException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(artist);
		}
		catch (NoSuchArtistException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Artist removeImpl(Artist artist) throws SystemException {
		artist = toUnwrappedModel(artist);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(artist)) {
				artist = (Artist)session.get(ArtistImpl.class,
						artist.getPrimaryKeyObj());
			}

			if (artist != null) {
				session.delete(artist);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (artist != null) {
			clearCache(artist);
		}

		return artist;
	}

	@Override
	public Artist updateImpl(org.liferay.jukebox.model.Artist artist)
		throws SystemException {
		artist = toUnwrappedModel(artist);

		boolean isNew = artist.isNew();

		ArtistModelImpl artistModelImpl = (ArtistModelImpl)artist;

		if (Validator.isNull(artist.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			artist.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (artist.isNew()) {
				session.save(artist);

				artist.setNew(false);
			}
			else {
				session.merge(artist);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ArtistModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((artistModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { artistModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { artistModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((artistModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						artistModelImpl.getOriginalUuid(),
						artistModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						artistModelImpl.getUuid(),
						artistModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((artistModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						artistModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { artistModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((artistModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { artistModelImpl.getOriginalUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { artistModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((artistModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						artistModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { artistModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((artistModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						artistModelImpl.getOriginalUserId(),
						artistModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G,
					args);

				args = new Object[] {
						artistModelImpl.getUserId(),
						artistModelImpl.getGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G,
					args);
			}
		}

		EntityCacheUtil.putResult(ArtistModelImpl.ENTITY_CACHE_ENABLED,
			ArtistImpl.class, artist.getPrimaryKey(), artist);

		clearUniqueFindersCache(artist);
		cacheUniqueFindersCache(artist);

		return artist;
	}

	protected Artist toUnwrappedModel(Artist artist) {
		if (artist instanceof ArtistImpl) {
			return artist;
		}

		ArtistImpl artistImpl = new ArtistImpl();

		artistImpl.setNew(artist.isNew());
		artistImpl.setPrimaryKey(artist.getPrimaryKey());

		artistImpl.setUuid(artist.getUuid());
		artistImpl.setArtistId(artist.getArtistId());
		artistImpl.setCompanyId(artist.getCompanyId());
		artistImpl.setGroupId(artist.getGroupId());
		artistImpl.setUserId(artist.getUserId());
		artistImpl.setUserName(artist.getUserName());
		artistImpl.setCreateDate(artist.getCreateDate());
		artistImpl.setModifiedDate(artist.getModifiedDate());
		artistImpl.setName(artist.getName());

		return artistImpl;
	}

	/**
	 * Returns the artist with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the artist
	 * @return the artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByPrimaryKey(Serializable primaryKey)
		throws NoSuchArtistException, SystemException {
		Artist artist = fetchByPrimaryKey(primaryKey);

		if (artist == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchArtistException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return artist;
	}

	/**
	 * Returns the artist with the primary key or throws a {@link org.liferay.jukebox.NoSuchArtistException} if it could not be found.
	 *
	 * @param artistId the primary key of the artist
	 * @return the artist
	 * @throws org.liferay.jukebox.NoSuchArtistException if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist findByPrimaryKey(long artistId)
		throws NoSuchArtistException, SystemException {
		return findByPrimaryKey((Serializable)artistId);
	}

	/**
	 * Returns the artist with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the artist
	 * @return the artist, or <code>null</code> if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Artist artist = (Artist)EntityCacheUtil.getResult(ArtistModelImpl.ENTITY_CACHE_ENABLED,
				ArtistImpl.class, primaryKey);

		if (artist == _nullArtist) {
			return null;
		}

		if (artist == null) {
			Session session = null;

			try {
				session = openSession();

				artist = (Artist)session.get(ArtistImpl.class, primaryKey);

				if (artist != null) {
					cacheResult(artist);
				}
				else {
					EntityCacheUtil.putResult(ArtistModelImpl.ENTITY_CACHE_ENABLED,
						ArtistImpl.class, primaryKey, _nullArtist);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ArtistModelImpl.ENTITY_CACHE_ENABLED,
					ArtistImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return artist;
	}

	/**
	 * Returns the artist with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param artistId the primary key of the artist
	 * @return the artist, or <code>null</code> if a artist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Artist fetchByPrimaryKey(long artistId) throws SystemException {
		return fetchByPrimaryKey((Serializable)artistId);
	}

	/**
	 * Returns all the artists.
	 *
	 * @return the artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @return the range of artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the artists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.ArtistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artists
	 * @param end the upper bound of the range of artists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Artist> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Artist> list = (List<Artist>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ARTIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ARTIST;

				if (pagination) {
					sql = sql.concat(ArtistModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Artist>(list);
				}
				else {
					list = (List<Artist>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the artists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Artist artist : findAll()) {
			remove(artist);
		}
	}

	/**
	 * Returns the number of artists.
	 *
	 * @return the number of artists
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ARTIST);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the artist persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.liferay.jukebox.model.Artist")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Artist>> listenersList = new ArrayList<ModelListener<Artist>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Artist>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ArtistImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ARTIST = "SELECT artist FROM Artist artist";
	private static final String _SQL_SELECT_ARTIST_WHERE = "SELECT artist FROM Artist artist WHERE ";
	private static final String _SQL_COUNT_ARTIST = "SELECT COUNT(artist) FROM Artist artist";
	private static final String _SQL_COUNT_ARTIST_WHERE = "SELECT COUNT(artist) FROM Artist artist WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "artist.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Artist exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Artist exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ArtistPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static Artist _nullArtist = new ArtistImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Artist> toCacheModel() {
				return _nullArtistCacheModel;
			}
		};

	private static CacheModel<Artist> _nullArtistCacheModel = new CacheModel<Artist>() {
			@Override
			public Artist toEntityModel() {
				return _nullArtist;
			}
		};
}