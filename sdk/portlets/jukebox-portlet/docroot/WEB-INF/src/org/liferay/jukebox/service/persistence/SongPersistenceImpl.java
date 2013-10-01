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

import org.liferay.jukebox.NoSuchSongException;
import org.liferay.jukebox.model.Song;
import org.liferay.jukebox.model.impl.SongImpl;
import org.liferay.jukebox.model.impl.SongModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the song service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Julio Camarero
 * @see SongPersistence
 * @see SongUtil
 * @generated
 */
public class SongPersistenceImpl extends BasePersistenceImpl<Song>
	implements SongPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SongUtil} to access the song persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SongImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SongModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the songs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the songs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @return the range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the songs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUuid(String uuid, int start, int end,
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

		List<Song> list = (List<Song>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Song song : list) {
				if (!Validator.equals(uuid, song.getUuid())) {
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

			query.append(_SQL_SELECT_SONG_WHERE);

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
				query.append(SongModelImpl.ORDER_BY_JPQL);
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
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Song>(list);
				}
				else {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first song in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByUuid_First(uuid, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the first song in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Song> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last song in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByUuid_Last(String uuid, OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByUuid_Last(uuid, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the last song in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Song> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the songs before and after the current song in the ordered set where uuid = &#63;.
	 *
	 * @param songId the primary key of the current song
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next song
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song[] findByUuid_PrevAndNext(long songId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = findByPrimaryKey(songId);

		Session session = null;

		try {
			session = openSession();

			Song[] array = new SongImpl[3];

			array[0] = getByUuid_PrevAndNext(session, song, uuid,
					orderByComparator, true);

			array[1] = song;

			array[2] = getByUuid_PrevAndNext(session, song, uuid,
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

	protected Song getByUuid_PrevAndNext(Session session, Song song,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SONG_WHERE);

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
			query.append(SongModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(song);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Song> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the songs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (Song song : findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(song);
		}
	}

	/**
	 * Returns the number of songs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching songs
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

			query.append(_SQL_COUNT_SONG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "song.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "song.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(song.uuid IS NULL OR song.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SongModelImpl.UUID_COLUMN_BITMASK |
			SongModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the song where uuid = &#63; and groupId = &#63; or throws a {@link org.liferay.jukebox.NoSuchSongException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByUUID_G(String uuid, long groupId)
		throws NoSuchSongException, SystemException {
		Song song = fetchByUUID_G(uuid, groupId);

		if (song == null) {
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

			throw new NoSuchSongException(msg.toString());
		}

		return song;
	}

	/**
	 * Returns the song where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the song where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Song) {
			Song song = (Song)result;

			if (!Validator.equals(uuid, song.getUuid()) ||
					(groupId != song.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SONG_WHERE);

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

				List<Song> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Song song = list.get(0);

					result = song;

					cacheResult(song);

					if ((song.getUuid() == null) ||
							!song.getUuid().equals(uuid) ||
							(song.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, song);
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
			return (Song)result;
		}
	}

	/**
	 * Removes the song where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the song that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song removeByUUID_G(String uuid, long groupId)
		throws NoSuchSongException, SystemException {
		Song song = findByUUID_G(uuid, groupId);

		return remove(song);
	}

	/**
	 * Returns the number of songs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching songs
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

			query.append(_SQL_COUNT_SONG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "song.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "song.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(song.uuid IS NULL OR song.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "song.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SongModelImpl.UUID_COLUMN_BITMASK |
			SongModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the songs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the songs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @return the range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUuid_C(String uuid, long companyId, int start,
		int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the songs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUuid_C(String uuid, long companyId, int start,
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

		List<Song> list = (List<Song>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Song song : list) {
				if (!Validator.equals(uuid, song.getUuid()) ||
						(companyId != song.getCompanyId())) {
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

			query.append(_SQL_SELECT_SONG_WHERE);

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
				query.append(SongModelImpl.ORDER_BY_JPQL);
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
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Song>(list);
				}
				else {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first song in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the first song in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Song> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last song in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the last song in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Song> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the songs before and after the current song in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param songId the primary key of the current song
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next song
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song[] findByUuid_C_PrevAndNext(long songId, String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = findByPrimaryKey(songId);

		Session session = null;

		try {
			session = openSession();

			Song[] array = new SongImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, song, uuid, companyId,
					orderByComparator, true);

			array[1] = song;

			array[2] = getByUuid_C_PrevAndNext(session, song, uuid, companyId,
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

	protected Song getByUuid_C_PrevAndNext(Session session, Song song,
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

		query.append(_SQL_SELECT_SONG_WHERE);

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
			query.append(SongModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(song);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Song> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the songs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (Song song : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(song);
		}
	}

	/**
	 * Returns the number of songs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching songs
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

			query.append(_SQL_COUNT_SONG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "song.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "song.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(song.uuid IS NULL OR song.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "song.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SongModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the songs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByGroupId(long groupId) throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the songs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @return the range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the songs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByGroupId(long groupId, int start, int end,
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

		List<Song> list = (List<Song>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Song song : list) {
				if ((groupId != song.getGroupId())) {
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

			query.append(_SQL_SELECT_SONG_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SongModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Song>(list);
				}
				else {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first song in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByGroupId_First(groupId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the first song in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Song> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last song in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByGroupId_Last(groupId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the last song in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Song> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the songs before and after the current song in the ordered set where groupId = &#63;.
	 *
	 * @param songId the primary key of the current song
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next song
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song[] findByGroupId_PrevAndNext(long songId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = findByPrimaryKey(songId);

		Session session = null;

		try {
			session = openSession();

			Song[] array = new SongImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, song, groupId,
					orderByComparator, true);

			array[1] = song;

			array[2] = getByGroupId_PrevAndNext(session, song, groupId,
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

	protected Song getByGroupId_PrevAndNext(Session session, Song song,
		long groupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SONG_WHERE);

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
			query.append(SongModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(song);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Song> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the songs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (Song song : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(song);
		}
	}

	/**
	 * Returns the number of songs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching songs
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

			query.append(_SQL_COUNT_SONG_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "song.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			SongModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the songs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the songs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @return the range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the songs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByUserId(long userId, int start, int end,
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

		List<Song> list = (List<Song>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Song song : list) {
				if ((userId != song.getUserId())) {
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

			query.append(_SQL_SELECT_SONG_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SongModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Song>(list);
				}
				else {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first song in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByUserId_First(userId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the first song in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Song> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last song in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByUserId_Last(userId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the last song in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<Song> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the songs before and after the current song in the ordered set where userId = &#63;.
	 *
	 * @param songId the primary key of the current song
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next song
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song[] findByUserId_PrevAndNext(long songId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = findByPrimaryKey(songId);

		Session session = null;

		try {
			session = openSession();

			Song[] array = new SongImpl[3];

			array[0] = getByUserId_PrevAndNext(session, song, userId,
					orderByComparator, true);

			array[1] = song;

			array[2] = getByUserId_PrevAndNext(session, song, userId,
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

	protected Song getByUserId_PrevAndNext(Session session, Song song,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SONG_WHERE);

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
			query.append(SongModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(song);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Song> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the songs where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (Song song : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(song);
		}
	}

	/**
	 * Returns the number of songs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching songs
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

			query.append(_SQL_COUNT_SONG_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "song.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			SongModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the songs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByCompanyId(long companyId) throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the songs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @return the range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the songs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByCompanyId(long companyId, int start, int end,
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

		List<Song> list = (List<Song>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Song song : list) {
				if ((companyId != song.getCompanyId())) {
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

			query.append(_SQL_SELECT_SONG_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SongModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Song>(list);
				}
				else {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first song in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByCompanyId_First(companyId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the first song in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Song> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last song in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByCompanyId_Last(companyId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the last song in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<Song> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the songs before and after the current song in the ordered set where companyId = &#63;.
	 *
	 * @param songId the primary key of the current song
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next song
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song[] findByCompanyId_PrevAndNext(long songId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = findByPrimaryKey(songId);

		Session session = null;

		try {
			session = openSession();

			Song[] array = new SongImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, song, companyId,
					orderByComparator, true);

			array[1] = song;

			array[2] = getByCompanyId_PrevAndNext(session, song, companyId,
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

	protected Song getByCompanyId_PrevAndNext(Session session, Song song,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SONG_WHERE);

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
			query.append(SongModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(song);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Song> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the songs where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (Song song : findByCompanyId(companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(song);
		}
	}

	/**
	 * Returns the number of songs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching songs
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

			query.append(_SQL_COUNT_SONG_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "song.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, SongImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			SongModelImpl.GROUPID_COLUMN_BITMASK |
			SongModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the songs where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByG_U(long groupId, long userId)
		throws SystemException {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the songs where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @return the range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByG_U(long groupId, long userId, int start, int end)
		throws SystemException {
		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the songs where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findByG_U(long groupId, long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Song> list = (List<Song>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Song song : list) {
				if ((groupId != song.getGroupId()) ||
						(userId != song.getUserId())) {
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

			query.append(_SQL_SELECT_SONG_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SongModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Song>(list);
				}
				else {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first song in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByG_U_First(groupId, userId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the first song in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Song> list = findByG_U(groupId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last song in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song
	 * @throws org.liferay.jukebox.NoSuchSongException if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = fetchByG_U_Last(groupId, userId, orderByComparator);

		if (song != null) {
			return song;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSongException(msg.toString());
	}

	/**
	 * Returns the last song in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching song, or <code>null</code> if a matching song could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_U(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<Song> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the songs before and after the current song in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param songId the primary key of the current song
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next song
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song[] findByG_U_PrevAndNext(long songId, long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSongException, SystemException {
		Song song = findByPrimaryKey(songId);

		Session session = null;

		try {
			session = openSession();

			Song[] array = new SongImpl[3];

			array[0] = getByG_U_PrevAndNext(session, song, groupId, userId,
					orderByComparator, true);

			array[1] = song;

			array[2] = getByG_U_PrevAndNext(session, song, groupId, userId,
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

	protected Song getByG_U_PrevAndNext(Session session, Song song,
		long groupId, long userId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SONG_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

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
			query.append(SongModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(song);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Song> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the songs where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_U(long groupId, long userId)
		throws SystemException {
		for (Song song : findByG_U(groupId, userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(song);
		}
	}

	/**
	 * Returns the number of songs where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_U(long groupId, long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SONG_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "song.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "song.userId = ?";

	public SongPersistenceImpl() {
		setModelClass(Song.class);
	}

	/**
	 * Caches the song in the entity cache if it is enabled.
	 *
	 * @param song the song
	 */
	@Override
	public void cacheResult(Song song) {
		EntityCacheUtil.putResult(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongImpl.class, song.getPrimaryKey(), song);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { song.getUuid(), song.getGroupId() }, song);

		song.resetOriginalValues();
	}

	/**
	 * Caches the songs in the entity cache if it is enabled.
	 *
	 * @param songs the songs
	 */
	@Override
	public void cacheResult(List<Song> songs) {
		for (Song song : songs) {
			if (EntityCacheUtil.getResult(SongModelImpl.ENTITY_CACHE_ENABLED,
						SongImpl.class, song.getPrimaryKey()) == null) {
				cacheResult(song);
			}
			else {
				song.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all songs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SongImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SongImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the song.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Song song) {
		EntityCacheUtil.removeResult(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongImpl.class, song.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(song);
	}

	@Override
	public void clearCache(List<Song> songs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Song song : songs) {
			EntityCacheUtil.removeResult(SongModelImpl.ENTITY_CACHE_ENABLED,
				SongImpl.class, song.getPrimaryKey());

			clearUniqueFindersCache(song);
		}
	}

	protected void cacheUniqueFindersCache(Song song) {
		if (song.isNew()) {
			Object[] args = new Object[] { song.getUuid(), song.getGroupId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args, song);
		}
		else {
			SongModelImpl songModelImpl = (SongModelImpl)song;

			if ((songModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { song.getUuid(), song.getGroupId() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					song);
			}
		}
	}

	protected void clearUniqueFindersCache(Song song) {
		SongModelImpl songModelImpl = (SongModelImpl)song;

		Object[] args = new Object[] { song.getUuid(), song.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((songModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					songModelImpl.getOriginalUuid(),
					songModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new song with the primary key. Does not add the song to the database.
	 *
	 * @param songId the primary key for the new song
	 * @return the new song
	 */
	@Override
	public Song create(long songId) {
		Song song = new SongImpl();

		song.setNew(true);
		song.setPrimaryKey(songId);

		String uuid = PortalUUIDUtil.generate();

		song.setUuid(uuid);

		return song;
	}

	/**
	 * Removes the song with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param songId the primary key of the song
	 * @return the song that was removed
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song remove(long songId) throws NoSuchSongException, SystemException {
		return remove((Serializable)songId);
	}

	/**
	 * Removes the song with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the song
	 * @return the song that was removed
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song remove(Serializable primaryKey)
		throws NoSuchSongException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Song song = (Song)session.get(SongImpl.class, primaryKey);

			if (song == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSongException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(song);
		}
		catch (NoSuchSongException nsee) {
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
	protected Song removeImpl(Song song) throws SystemException {
		song = toUnwrappedModel(song);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(song)) {
				song = (Song)session.get(SongImpl.class, song.getPrimaryKeyObj());
			}

			if (song != null) {
				session.delete(song);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (song != null) {
			clearCache(song);
		}

		return song;
	}

	@Override
	public Song updateImpl(org.liferay.jukebox.model.Song song)
		throws SystemException {
		song = toUnwrappedModel(song);

		boolean isNew = song.isNew();

		SongModelImpl songModelImpl = (SongModelImpl)song;

		if (Validator.isNull(song.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			song.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (song.isNew()) {
				session.save(song);

				song.setNew(false);
			}
			else {
				session.merge(song);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SongModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((songModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { songModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { songModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((songModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						songModelImpl.getOriginalUuid(),
						songModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						songModelImpl.getUuid(), songModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((songModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { songModelImpl.getOriginalGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { songModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((songModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { songModelImpl.getOriginalUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { songModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((songModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						songModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { songModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((songModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						songModelImpl.getOriginalGroupId(),
						songModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);

				args = new Object[] {
						songModelImpl.getGroupId(), songModelImpl.getUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);
			}
		}

		EntityCacheUtil.putResult(SongModelImpl.ENTITY_CACHE_ENABLED,
			SongImpl.class, song.getPrimaryKey(), song);

		clearUniqueFindersCache(song);
		cacheUniqueFindersCache(song);

		return song;
	}

	protected Song toUnwrappedModel(Song song) {
		if (song instanceof SongImpl) {
			return song;
		}

		SongImpl songImpl = new SongImpl();

		songImpl.setNew(song.isNew());
		songImpl.setPrimaryKey(song.getPrimaryKey());

		songImpl.setUuid(song.getUuid());
		songImpl.setSongId(song.getSongId());
		songImpl.setCompanyId(song.getCompanyId());
		songImpl.setGroupId(song.getGroupId());
		songImpl.setUserId(song.getUserId());
		songImpl.setUserName(song.getUserName());
		songImpl.setCreateDate(song.getCreateDate());
		songImpl.setModifiedDate(song.getModifiedDate());
		songImpl.setArtistId(song.getArtistId());
		songImpl.setAlbumId(song.getAlbumId());
		songImpl.setName(song.getName());

		return songImpl;
	}

	/**
	 * Returns the song with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the song
	 * @return the song
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSongException, SystemException {
		Song song = fetchByPrimaryKey(primaryKey);

		if (song == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSongException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return song;
	}

	/**
	 * Returns the song with the primary key or throws a {@link org.liferay.jukebox.NoSuchSongException} if it could not be found.
	 *
	 * @param songId the primary key of the song
	 * @return the song
	 * @throws org.liferay.jukebox.NoSuchSongException if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song findByPrimaryKey(long songId)
		throws NoSuchSongException, SystemException {
		return findByPrimaryKey((Serializable)songId);
	}

	/**
	 * Returns the song with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the song
	 * @return the song, or <code>null</code> if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Song song = (Song)EntityCacheUtil.getResult(SongModelImpl.ENTITY_CACHE_ENABLED,
				SongImpl.class, primaryKey);

		if (song == _nullSong) {
			return null;
		}

		if (song == null) {
			Session session = null;

			try {
				session = openSession();

				song = (Song)session.get(SongImpl.class, primaryKey);

				if (song != null) {
					cacheResult(song);
				}
				else {
					EntityCacheUtil.putResult(SongModelImpl.ENTITY_CACHE_ENABLED,
						SongImpl.class, primaryKey, _nullSong);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SongModelImpl.ENTITY_CACHE_ENABLED,
					SongImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return song;
	}

	/**
	 * Returns the song with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param songId the primary key of the song
	 * @return the song, or <code>null</code> if a song with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Song fetchByPrimaryKey(long songId) throws SystemException {
		return fetchByPrimaryKey((Serializable)songId);
	}

	/**
	 * Returns all the songs.
	 *
	 * @return the songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the songs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @return the range of songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the songs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.liferay.jukebox.model.impl.SongModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of songs
	 * @param end the upper bound of the range of songs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of songs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Song> findAll(int start, int end,
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

		List<Song> list = (List<Song>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SONG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SONG;

				if (pagination) {
					sql = sql.concat(SongModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Song>(list);
				}
				else {
					list = (List<Song>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the songs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Song song : findAll()) {
			remove(song);
		}
	}

	/**
	 * Returns the number of songs.
	 *
	 * @return the number of songs
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

				Query q = session.createQuery(_SQL_COUNT_SONG);

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
	 * Initializes the song persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.liferay.jukebox.model.Song")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Song>> listenersList = new ArrayList<ModelListener<Song>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Song>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SongImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SONG = "SELECT song FROM Song song";
	private static final String _SQL_SELECT_SONG_WHERE = "SELECT song FROM Song song WHERE ";
	private static final String _SQL_COUNT_SONG = "SELECT COUNT(song) FROM Song song";
	private static final String _SQL_COUNT_SONG_WHERE = "SELECT COUNT(song) FROM Song song WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "song.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Song exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Song exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SongPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static Song _nullSong = new SongImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Song> toCacheModel() {
				return _nullSongCacheModel;
			}
		};

	private static CacheModel<Song> _nullSongCacheModel = new CacheModel<Song>() {
			@Override
			public Song toEntityModel() {
				return _nullSong;
			}
		};
}