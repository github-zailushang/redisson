/**
 * Copyright (c) 2013-2024 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;

import org.redisson.api.options.*;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonCodec;
import org.redisson.config.Config;

import java.util.Collection;
import java.util.List;

/**
 * Main Redisson interface for access
 * to all redisson objects with Reactive interface.
 *
 * @see RedissonRxClient
 * @see RedissonClient
 *
 * @author Nikita Koksharov
 *
 */
public interface RedissonReactiveClient {

    /**
     * Returns time-series instance by <code>name</code>
     *
     * @param <V> value type
     * @param <L> label type
     * @param name name of instance
     * @return RTimeSeries object
     */
    <V, L> RTimeSeriesReactive<V, L> getTimeSeries(String name);

    /**
     * Returns time-series instance by <code>name</code>
     * using provided <code>codec</code> for values.
     *
     * @param <V> value type
     * @param <L> label type
     * @param name name of instance
     * @param codec codec for values
     * @return RTimeSeries object
     */
    <V, L> RTimeSeriesReactive<V, L> getTimeSeries(String name, Codec codec);

    /**
     * Returns time-series instance with specified <code>options</code>.
     *
     * @param <V> value type
     * @param <L> label type
     * @param options instance options
     * @return RTimeSeries object
     */
    <V, L> RTimeSeriesReactive<V, L> getTimeSeries(PlainOptions options);

    /**
     * Returns stream instance by <code>name</code>
     * <p>
     * Requires <b>Redis 5.0.0 and higher.</b>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name of stream
     * @return RStream object
     */
    <K, V> RStreamReactive<K, V> getStream(String name);
    
    /**
     * Returns stream instance by <code>name</code>
     * using provided <code>codec</code> for entries.
     * <p>
     * Requires <b>Redis 5.0.0 and higher.</b>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of stream
     * @param codec codec for entry
     * @return RStream object
     */
    <K, V> RStreamReactive<K, V> getStream(String name, Codec codec);

    /**
     * Returns time-series instance with specified <code>options</code>.
     * <p>
     * Requires <b>Redis 5.0.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return RStream object
     */
    <K, V> RStreamReactive<K, V> getStream(PlainOptions options);

    /**
     * Returns API for RediSearch module
     *
     * @return RSearch object
     */
    RSearchReactive getSearch();

    /**
     * Returns API for RediSearch module using defined codec for attribute values.
     *
     * @return RSearch object
     */
    RSearchReactive getSearch(Codec codec);

    /**
     * Returns API for RediSearch module with specified <code>options</code>.
     *
     * @param options instance options
     * @return RSearch object
     */
    RSearchReactive getSearch(OptionalOptions options);

    /**
     * Returns geospatial items holder instance by <code>name</code>.
     * 
     * @param <V> type of value
     * @param name name of object
     * @return Geo object
     */
    <V> RGeoReactive<V> getGeo(String name);
    
    /**
     * Returns geospatial items holder instance by <code>name</code>
     * using provided codec for geospatial members.
     * 
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for value
     * @return Geo object
     */
    <V> RGeoReactive<V> getGeo(String name, Codec codec);

    /**
     * Returns geospatial items holder instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return Geo object
     */
    <V> RGeoReactive<V> getGeo(PlainOptions options);

    /**
     * Returns rate limiter instance by <code>name</code>
     * 
     * @param name of rate limiter
     * @return RateLimiter object
     */
    RRateLimiterReactive getRateLimiter(String name);

    /**
     * Returns rate limiter instance with specified <code>options</code>.
     *
     * @param options instance options
     * @return RateLimiter object
     */
    RRateLimiterReactive getRateLimiter(CommonOptions options);

    /**
     * Returns binary stream holder instance by <code>name</code>
     *
     * @param name of binary stream
     * @return BinaryStream object
     */
    RBinaryStreamReactive getBinaryStream(String name);

    /**
     * Returns binary stream holder instance with specified <code>options</code>.
     *
     * @param options instance options
     * @return BinaryStream object
     */
    RBinaryStreamReactive getBinaryStream(CommonOptions options);

    /**
     * Returns semaphore instance by name
     *
     * @param name name of object
     * @return Semaphore object
     */
    RSemaphoreReactive getSemaphore(String name);

    /**
     * Returns semaphore instance with specified <code>options</code>.
     *
     * @param options instance options
     * @return Semaphore object
     */
    RSemaphoreReactive getSemaphore(CommonOptions options);

    /**
     * Returns semaphore instance by name.
     * Supports lease time parameter for each acquired permit.
     * 
     * @param name name of object
     * @return PermitExpirableSemaphore object
     */
    RPermitExpirableSemaphoreReactive getPermitExpirableSemaphore(String name);

    /**
     * Returns semaphore instance with specified <code>options</code>.
     * Supports lease time parameter for each acquired permit.
     *
     * @param options instance options
     * @return PermitExpirableSemaphore object
     */
    RPermitExpirableSemaphoreReactive getPermitExpirableSemaphore(CommonOptions options);

    /**
     * Returns ReadWriteLock instance by name.
     * <p>
     * To increase reliability during failover, all operations wait for propagation to all Redis slaves.
     *
     * @param name name of object
     * @return Lock object
     */
    RReadWriteLockReactive getReadWriteLock(String name);

    /**
     * Returns ReadWriteLock instance with specified <code>options</code>.
     * <p>
     * To increase reliability during failover, all operations wait for propagation to all Redis slaves.
     *
     * @param options instance options
     * @return Lock object
     */
    RReadWriteLockReactive getReadWriteLock(CommonOptions options);

    /**
     * Returns Lock instance by name.
     * <p>
     * Implements a <b>fair</b> locking so it guarantees an acquire order by threads.
     * <p>
     * To increase reliability during failover, all operations wait for propagation to all Redis slaves.
     * 
     * @param name name of object
     * @return Lock object
     */
    RLockReactive getFairLock(String name);

    /**
     * Returns Lock instance with specified <code>options</code>.
     * <p>
     * Implements a <b>fair</b> locking so it guarantees an acquire order by threads.
     * <p>
     * To increase reliability during failover, all operations wait for propagation to all Redis slaves.
     *
     * @param options instance options
     * @return Lock object
     */
    RLockReactive getFairLock(CommonOptions options);

    /**
     * Returns Lock instance by name.
     * <p>
     * Implements a <b>non-fair</b> locking so doesn't guarantees an acquire order by threads.
     * <p>
     * To increase reliability during failover, all operations wait for propagation to all Redis slaves.
     *
     * @param name name of object
     * @return Lock object
     */
    RLockReactive getLock(String name);

    /**
     * Returns Lock instance with specified <code>options</code>.
     * <p>
     * Implements a <b>non-fair</b> locking so doesn't guarantees an acquire order by threads.
     * <p>
     * To increase reliability during failover, all operations wait for propagation to all Redis slaves.
     *
     * @param options instance options
     * @return Lock object
     */
    RLockReactive getLock(CommonOptions options);

    /**
     * Returns Spin lock instance by name.
     * <p>
     * Implements a <b>non-fair</b> locking so doesn't guarantees an acquire order by threads.
     * <p>
     * Lock doesn't use a pub/sub mechanism
     *
     * @param name name of object
     * @return Lock object
     */
    RLockReactive getSpinLock(String name);

    /**
     * Returns Spin lock instance by name with specified back off options.
     * <p>
     * Implements a <b>non-fair</b> locking so doesn't guarantees an acquire order by threads.
     * <p>
     * Lock doesn't use a pub/sub mechanism
     *
     * @param name name of object
     * @return Lock object
     */
    RLockReactive getSpinLock(String name, LockOptions.BackOff backOff);

    /**
     * Returns Fenced Lock by name.
     * <p>
     * Implements a <b>non-fair</b> locking so doesn't guarantee an acquire order by threads.
     *
     * @param name name of object
     * @return Lock object
     */
    RFencedLockReactive getFencedLock(String name);

    /**
     * Returns Fenced Lock instance with specified <code>options</code>..
     * <p>
     * Implements a <b>non-fair</b> locking so doesn't guarantee an acquire order by threads.
     *
     * @param options instance options
     * @return Lock object
     */
    RFencedLockReactive getFencedLock(CommonOptions options);

    /**
     * Returns MultiLock instance associated with specified <code>locks</code>
     *
     * @param locks collection of locks
     * @return MultiLock object
     */
    RLockReactive getMultiLock(RLockReactive... locks);
    /**
     * Returns RedissonFasterMultiLock instance associated with specified <code>group</code> and <code>values</code>
     *
     * @param group the group of values
     * @param values lock values
     * @return BatchLock object
     */
    RLockReactive getMultiLock(String group, Collection<Object> values);
    /*
     * Use getMultiLock(RLockReactive) method instead
     */
    @Deprecated
    RLockReactive getMultiLock(RLock... locks);
    
    /*
     * Use getMultiLock method instead. Returned instance uses Redis Slave synchronization
     */
    @Deprecated
    RLockReactive getRedLock(RLock... locks);

    /**
     * Returns CountDownLatch instance by name.
     *
     * @param name name of object
     * @return CountDownLatch object
     */
    RCountDownLatchReactive getCountDownLatch(String name);

    /**
     * Returns countDownLatch instance with specified <code>options</code>.
     *
     * @param options instance options
     * @return CountDownLatch object
     */
    RCountDownLatchReactive getCountDownLatch(CommonOptions options);

    /**
     * Returns set-based cache instance by <code>name</code>.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(String, Codec)}.</p>
     *
     * @param <V> type of values
     * @param name name of object
     * @return SetCache object
     */
    <V> RSetCacheReactive<V> getSetCache(String name);

    /**
     * Returns set-based cache instance by <code>name</code>.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(String, Codec)}.</p>
     *
     * @param <V> type of values
     * @param name name of object
     * @param codec codec for values
     * @return SetCache object
     */
    <V> RSetCacheReactive<V> getSetCache(String name, Codec codec);

    /**
     * Returns set-based cache instance with specified <code>options</code>.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(PlainOptions)}.</p>
     *
     * @param <V> type of value
     * @param options instance options
     * @return SetCache object
     */
    <V> RSetCacheReactive<V> getSetCache(PlainOptions options);

    /**
     * Returns map-based cache instance by name
     * using provided codec for both cache keys and values.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(String, Codec)}.
     *
     * @param <K> type of keys
     * @param <V> type of values
     * @param name name of object
     * @param codec codec for values
     * @return MapCache object
     */
    <K, V> RMapCacheReactive<K, V> getMapCache(String name, Codec codec);

    /**
     * Returns map-based cache instance by <code>name</code>
     * using provided <code>codec</code> for both cache keys and values.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(String, Codec, MapOptions)}.
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name object name
     * @param codec codec for keys and values
     * @param options map options
     * @return MapCache object
     */
    @Deprecated
    <K, V> RMapCacheReactive<K, V> getMapCache(String name, Codec codec, MapCacheOptions<K, V> options);

    /**
     * Returns map-based cache instance by name.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(String)}.
     *
     * @param <K> type of keys
     * @param <V> type of values
     * @param name name of object
     * @return MapCache object
     */
    <K, V> RMapCacheReactive<K, V> getMapCache(String name);

    /**
     * Returns map-based cache instance by name.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(String, MapOptions)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param options map options
     * @return MapCache object
     */
    @Deprecated
    <K, V> RMapCacheReactive<K, V> getMapCache(String name, MapCacheOptions<K, V> options);

    /**
     * Returns map-based cache instance with specified <code>options</code>.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(org.redisson.api.options.MapOptions)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return MapCache object
     */
    <K, V> RMapCacheReactive<K, V> getMapCache(org.redisson.api.options.MapCacheOptions<K, V> options);

    /**
     * Returns map instance by name.
     * Supports entry eviction with a given TTL.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @return Map object
     */
    <K, V> RMapCacheNativeReactive<K, V> getMapCacheNative(String name);

    /**
     * Returns map instance by name
     * using provided codec for both map keys and values.
     * Supports entry eviction with a given TTL.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @return Map object
     */
    <K, V> RMapCacheNativeReactive<K, V> getMapCacheNative(String name, Codec codec);

    /**
     * Returns map instance.
     * Supports entry eviction with a given TTL.
     * Configured by the parameters of the options-object.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return Map object
     */
    <K, V> RMapCacheNativeReactive<K, V> getMapCacheNative(org.redisson.api.options.MapOptions<K, V> options);

    /**
     * Returns object holder instance by name
     * 
     * @param <V> type of value
     * @param name name of object
     * @return Bucket object
     */
    <V> RBucketReactive<V> getBucket(String name);

    /**
     * Returns object holder instance by name
     * using provided codec for object.
     *
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for value
     * @return Bucket object
     */
    <V> RBucketReactive<V> getBucket(String name, Codec codec);

    /**
     * Returns object holder instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return Bucket object
     */
    <V> RBucketReactive<V> getBucket(PlainOptions options);

    /**
     * Returns interface for mass operations with Bucket objects.
     *
     * @return Buckets
     */
    RBucketsReactive getBuckets();

    /**
     * Returns interface for mass operations with Bucket objects
     * using provided codec for object.
     *
     * @param codec codec for bucket objects
     * @return Buckets
     */
    RBucketsReactive getBuckets(Codec codec);

    /**
     * Returns API for mass operations over Bucket objects with specified <code>options</code>.
     *
     * @param options instance options
     * @return Buckets object
     */
    RBucketsReactive getBuckets(OptionalOptions options);

    /**
     * Use {@link #getBuckets()} instead.
     *
     * @param <V> type of value
     * @param pattern pattern for name of buckets
     * @return list of buckets 
     */
    @Deprecated
    <V> List<RBucketReactive<V>> findBuckets(String pattern);

    /**
     * Returns JSON data holder instance by name using provided codec.
     *
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for values
     * @return JsonBucket object
     */
    <V> RJsonBucketReactive<V> getJsonBucket(String name, JsonCodec codec);

    /**
     * Returns JSON data holder instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return JsonBucket object
     */
    <V> RJsonBucketReactive<V> getJsonBucket(JsonBucketOptions<V> options);
    
    /**
     * Returns API for mass operations over JsonBucket objects
     * using provided codec for JSON object with default path.
     *
     * @param codec using provided codec for JSON object with default path.
     * @return JsonBuckets
     */
    RJsonBucketsReactive getJsonBuckets(JsonCodec codec);
    
    /**
     * Returns HyperLogLog instance by name.
     * 
     * @param <V> type of values
     * @param name name of object
     * @return HyperLogLog object
     */
    <V> RHyperLogLogReactive<V> getHyperLogLog(String name);

    /**
     * Returns HyperLogLog instance by name
     * using provided codec for hll objects.
     *
     * @param <V> type of values
     * @param name name of object
     * @param codec codec of values
     * @return HyperLogLog object
     */
    <V> RHyperLogLogReactive<V> getHyperLogLog(String name, Codec codec);

    /**
     * Returns HyperLogLog instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return HyperLogLog object
     */
    <V> RHyperLogLogReactive<V> getHyperLogLog(PlainOptions options);

    /**
     * Returns id generator by name.
     *
     * @param name name of object
     * @return IdGenerator object
     */
    RIdGeneratorReactive getIdGenerator(String name);

    /**
     * Returns id generator instance with specified <code>options</code>.
     *
     * @param options instance options
     * @return IdGenerator object
     */
    RIdGeneratorReactive getIdGenerator(CommonOptions options);

    /**
     * Returns list instance by name.
     *
     * @param <V> type of values
     * @param name name of object
     * @return List object
     */
    <V> RListReactive<V> getList(String name);

    /**
     * Returns list instance by name
     * using provided codec for list objects.
     *
     * @param <V> type of values
     * @param name name of object
     * @param codec codec for values
     * @return List object
     */
    <V> RListReactive<V> getList(String name, Codec codec);

    /**
     * Returns list instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return List object
     */
    <V> RListReactive<V> getList(PlainOptions options);

    /**
     * Returns List based Multimap instance by name.
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @return ListMultimap object
     */
    <K, V> RListMultimapReactive<K, V> getListMultimap(String name);

    /**
     * Returns List based Multimap instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @return RListMultimapReactive object
     */
    <K, V> RListMultimapReactive<K, V> getListMultimap(String name, Codec codec);

    /**
     * Returns List based Multimap instance with specified <code>options</code>.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return ListMultimap object
     */
    <K, V> RListMultimapReactive<K, V> getListMultimap(PlainOptions options);

    /**
     * Returns List based Multimap cache instance by name.
     * Supports key eviction by specifying a time to live.
     * If eviction is not required then it's better to use regular list multimap {@link #getListMultimap(String)}.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @return RListMultimapCacheReactive object
     */
    <K, V> RListMultimapCacheReactive<K, V> getListMultimapCache(String name);

    /**
     * Returns List based Multimap cache instance by name using provided codec for both map keys and values.
     * Supports key eviction by specifying a time to live.
     * If eviction is not required then it's better to use regular list multimap {@link #getListMultimap(String, Codec)}.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @return RListMultimapCacheReactive object
     */
    <K, V> RListMultimapCacheReactive<K, V> getListMultimapCache(String name, Codec codec);

    /**
     * Returns List based Multimap instance by name.
     * Supports key-entry eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return ListMultimapCache object
     */
    <K, V> RListMultimapCacheReactive<K, V> getListMultimapCache(PlainOptions options);

    /**
     * Returns List based Multimap instance by name.
     * Supports key-entry eviction with a given TTL value.
     * Stores insertion order and allows duplicates for values mapped to key.
     * <p>
     * Uses Redis native commands for entry expiration and not a scheduled eviction task.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @return ListMultimapCache object
     */
    <K, V> RListMultimapCacheNativeReactive<K, V> getListMultimapCacheNative(String name);

    /**
     * Returns List based Multimap instance by name
     * using provided codec for both map keys and values.
     * Supports key-entry eviction with a given TTL value.
     * Stores insertion order and allows duplicates for values mapped to key.
     * <p>
     * Uses Redis native commands for entry expiration and not a scheduled eviction task.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @return ListMultimapCache object
     */
    <K, V> RListMultimapCacheNativeReactive<K, V> getListMultimapCacheNative(String name, Codec codec);

    /**
     * Returns List based Multimap instance by name.
     * Supports key-entry eviction with a given TTL value.
     * Stores insertion order and allows duplicates for values mapped to key.
     * <p>
     * Uses Redis native commands for entry expiration and not a scheduled eviction task.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return ListMultimapCache object
     */
    <K, V> RListMultimapCacheNativeReactive<K, V> getListMultimapCacheNative(PlainOptions options);

    /**
     * Returns Set based Multimap instance by name.
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @return SetMultimap object
     */
    <K, V> RSetMultimapReactive<K, V> getSetMultimap(String name);

    /**
     * Returns Set based Multimap instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @return SetMultimap object
     */
    <K, V> RSetMultimapReactive<K, V> getSetMultimap(String name, Codec codec);

    /**
     * Returns Set based Multimap instance with specified <code>options</code>.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return SetMultimap object
     */
    <K, V> RSetMultimapReactive<K, V> getSetMultimap(PlainOptions options);

    /**
     * Returns Set based Multimap cache instance by name.
     * Supports key eviction by specifying a time to live.
     * If eviction is not required then it's better to use regular set multimap {@link #getSetMultimap(String)}.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @return RSetMultimapCacheReactive object
     */
    <K, V> RSetMultimapCacheReactive<K, V> getSetMultimapCache(String name);

    /**
     * Returns Set based Multimap cache instance by name using provided codec for both map keys and values.
     * Supports key eviction by specifying a time to live.
     * If eviction is not required then it's better to use regular set multimap {@link #getSetMultimap(String, Codec)}.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @return RSetMultimapCacheReactive object
     */
    <K, V> RSetMultimapCacheReactive<K, V> getSetMultimapCache(String name, Codec codec);

    /**
     * Returns Set based Multimap instance with specified <code>options</code>.
     * Supports key-entry eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(PlainOptions)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return SetMultimapCache object
     */
    <K, V> RSetMultimapCacheReactive<K, V> getSetMultimapCache(PlainOptions options);

    /**
     * Returns Set based Multimap instance by name.
     * Supports key-entry eviction with a given TTL value.
     * Doesn't allow duplications for values mapped to key.
     * <p>
     * Uses Redis native commands for entry expiration and not a scheduled eviction task.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @return SetMultimapCache object
     */
    <K, V> RSetMultimapCacheNativeReactive<K, V> getSetMultimapCacheNative(String name);

    /**
     * Returns Set based Multimap instance by name
     * using provided codec for both map keys and values.
     * Supports key-entry eviction with a given TTL value.
     * Doesn't allow duplications for values mapped to key.
     * <p>
     * Uses Redis native commands for entry expiration and not a scheduled eviction task.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @return SetMultimapCache object
     */
    <K, V> RSetMultimapCacheNativeReactive<K, V> getSetMultimapCacheNative(String name, Codec codec);

    /**
     * Returns Set based Multimap instance with specified <code>options</code>.
     * Supports key-entry eviction with a given TTL value.
     * Doesn't allow duplications for values mapped to key.
     * <p>
     * Uses Redis native commands for entry expiration and not a scheduled eviction task.
     * <p>
     * Requires <b>Redis 7.4.0 and higher.</b>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return SetMultimapCache object
     */
    <K, V> RSetMultimapCacheNativeReactive<K, V> getSetMultimapCacheNative(PlainOptions options);

    /**
     * Returns map instance by name.
     *
     * @param <K> type of keys
     * @param <V> type of values
     * @param name name of object
     * @return Map object
     */
    <K, V> RMapReactive<K, V> getMap(String name);

    /**
     * Returns map instance by name.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param options map options
     * @return Map object
     */
    @Deprecated
    <K, V> RMapReactive<K, V> getMap(String name, MapOptions<K, V> options);

    /**
     * Returns map instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of keys
     * @param <V> type of values
     * @param name name of object
     * @param codec codec for keys and values
     * @return Map object
     */
    <K, V> RMapReactive<K, V> getMap(String name, Codec codec);

    /**
     * Returns map instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @param options map options
     * @return Map object
     */
    @Deprecated
    <K, V> RMapReactive<K, V> getMap(String name, Codec codec, MapOptions<K, V> options);

    /**
     * Returns map instance by name.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return Map object
     */
    <K, V> RMapReactive<K, V> getMap(org.redisson.api.options.MapOptions<K, V> options);

    /**
     * Returns local cached map instance by name.
     * Configured by parameters of options-object.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param options local map options
     * @return LocalCachedMap object
     */
    @Deprecated
    <K, V> RLocalCachedMapReactive<K, V> getLocalCachedMap(String name, LocalCachedMapOptions<K, V> options);

    /**
     * Returns local cached map instance by name
     * using provided codec. Configured by parameters of options-object.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for keys and values
     * @param options local map options
     * @return LocalCachedMap object
     */
    @Deprecated
    <K, V> RLocalCachedMapReactive<K, V> getLocalCachedMap(String name, Codec codec, LocalCachedMapOptions<K, V> options);

    /**
     * Returns local cached map instance with specified <code>options</code>.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param options instance options
     * @return LocalCachedMap object
     */
    <K, V> RLocalCachedMapReactive<K, V> getLocalCachedMap(org.redisson.api.options.LocalCachedMapOptions<K, V> options);

    /**
     * Returns local cached map cache instance by name.
     * Configured by parameters of options-object.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param options - local map options
     * @return LocalCachedMapCache object
     */
    <K, V> RLocalCachedMapCacheReactive<K, V> getLocalCachedMapCache(String name, LocalCachedMapCacheOptions<K, V> options);

    /**
     * Returns local cached map cache instance by name using provided codec.
     * Configured by parameters of options-object.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @param options - local map options
     * @return LocalCachedMap object
     */
    <K, V> RLocalCachedMapCacheReactive<K, V> getLocalCachedMapCache(String name, Codec codec, LocalCachedMapCacheOptions<K, V> options);

    /**
     * Returns set instance by name.
     *
     * @param <V> type of values
     * @param name name of object
     * @return Set object
     */
    <V> RSetReactive<V> getSet(String name);

    /**
     * Returns set instance by name
     * using provided codec for set objects.
     *
     * @param <V> type of values
     * @param name name of set
     * @param codec codec for values
     * @return Set object
     */
    <V> RSetReactive<V> getSet(String name, Codec codec);

    /**
     * Returns set instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return Set object
     */
    <V> RSetReactive<V> getSet(PlainOptions options);

    /**
     * Returns Redis Sorted Set instance by name.
     * This sorted set sorts objects by object score.
     * 
     * @param <V> type of values
     * @param name of scored sorted set
     * @return ScoredSortedSet object
     */
    <V> RScoredSortedSetReactive<V> getScoredSortedSet(String name);

    /**
     * Returns Redis Sorted Set instance by name
     * using provided codec for sorted set objects.
     * This sorted set sorts objects by object score.
     * 
     * @param <V> type of values
     * @param name name of scored sorted set
     * @param codec codec for values
     * @return ScoredSortedSet object
     */
    <V> RScoredSortedSetReactive<V> getScoredSortedSet(String name, Codec codec);

    /**
     * Returns Redis Sorted Set instance with specified <code>options</code>.
     * This sorted set sorts objects by object score.
     *
     * @param <V> type of value
     * @param options instance options
     * @return ScoredSortedSet object
     */
    <V> RScoredSortedSetReactive<V> getScoredSortedSet(PlainOptions options);

    /**
     * Returns String based Redis Sorted Set instance by name
     * All elements are inserted with the same score during addition,
     * in order to force lexicographical ordering
     *
     * @param name name of object
     * @return LexSortedSet object
     */
    RLexSortedSetReactive getLexSortedSet(String name);

    /**
     * Returns String based Redis Sorted Set instance with specified <code>options</code>.
     * All elements are inserted with the same score during addition,
     * in order to force lexicographical ordering
     *
     * @param options instance options
     * @return LexSortedSet object
     */
    RLexSortedSetReactive getLexSortedSet(CommonOptions options);

    /**
     * Returns Sharded Topic instance by name.
     * <p>
     * Messages are delivered to message listeners connected to the same Topic.
     * <p>
     *
     * @param name name of object
     * @return Topic object
     */
    RShardedTopicReactive getShardedTopic(String name);

    /**
     * Returns Sharded Topic instance by name using provided codec for messages.
     * <p>
     * Messages are delivered to message listeners connected to the same Topic.
     * <p>
     *
     * @param name name of object
     * @param codec codec for message
     * @return Topic object
     */
    RShardedTopicReactive getShardedTopic(String name, Codec codec);

    /**
     * Returns Sharded Topic instance with specified <code>options</code>.
     * <p>
     * Messages are delivered to message listeners connected to the same Topic.
     * <p>
     *
     * @param options instance options
     * @return Topic object
     */
    RShardedTopicReactive getShardedTopic(PlainOptions options);

    /**
     * Returns topic instance by name.
     *
     * @param name name of object
     * @return Topic object
     */
    RTopicReactive getTopic(String name);

    /**
     * Returns topic instance by name
     * using provided codec for messages.
     *
     * @param name name of object
     * @param codec codec for message
     * @return Topic object
     */
    RTopicReactive getTopic(String name, Codec codec);

    /**
     * Returns topic instance with specified <code>options</code>.
     * <p>
     * Messages are delivered to message listeners connected to the same Topic.
     * <p>
     *
     * @param options instance options
     * @return Topic object
     */
    RTopicReactive getTopic(PlainOptions options);

    /**
     * Returns reliable topic instance by name.
     * <p>
     * Dedicated Redis connection is allocated per instance (subscriber) of this object.
     * Messages are delivered to all listeners attached to the same Redis setup.
     * <p>
     * Requires <b>Redis 5.0.0 and higher.</b>
     *
     * @param name name of object
     * @return ReliableTopic object
     */
    RReliableTopicReactive getReliableTopic(String name);

    /**
     * Returns reliable topic instance by name
     * using provided codec for messages.
     * <p>
     * Dedicated Redis connection is allocated per instance (subscriber) of this object.
     * Messages are delivered to all listeners attached to the same Redis setup.
     * <p>
     * Requires <b>Redis 5.0.0 and higher.</b>
     *
     * @param name name of object
     * @param codec codec for message
     * @return ReliableTopic object
     */
    RReliableTopicReactive getReliableTopic(String name, Codec codec);

    /**
     * Returns reliable topic instance with specified <code>options</code>.
     * <p>
     * Dedicated Redis connection is allocated per instance (subscriber) of this object.
     * Messages are delivered to all listeners attached to the same Redis setup.
     * <p>
     * Requires <b>Redis 5.0.0 and higher.</b>
     *
     * @param options instance options
     * @return ReliableTopic object
     */
    RReliableTopicReactive getReliableTopic(PlainOptions options);

    /**
     * Returns topic instance satisfies by pattern name.
     *
     *  Supported glob-style patterns:
     *    h?llo subscribes to hello, hallo and hxllo
     *    h*llo subscribes to hllo and heeeello
     *    h[ae]llo subscribes to hello and hallo, but not hillo
     *
     * @param pattern of the topic
     * @return PatternTopic object
     */
    RPatternTopicReactive getPatternTopic(String pattern);

    /**
     * Returns topic instance satisfies by pattern name
     * using provided codec for messages.
     *
     *  Supported glob-style patterns:
     *    h?llo subscribes to hello, hallo and hxllo
     *    h*llo subscribes to hllo and heeeello
     *    h[ae]llo subscribes to hello and hallo, but not hillo
     *
     * @param pattern of the topic
     * @param codec codec for message
     * @return PatternTopic object
     */
    RPatternTopicReactive getPatternTopic(String pattern, Codec codec);

    /**
     * Returns topic instance satisfies pattern name and specified <code>options</code>..
     *
     *  Supported glob-style patterns:
     *    h?llo subscribes to hello, hallo and hxllo
     *    h*llo subscribes to hllo and heeeello
     *    h[ae]llo subscribes to hello and hallo, but not hillo
     *
     * @param options instance options
     * @return PatterTopic object
     */
    RPatternTopicReactive getPatternTopic(PatternTopicOptions options);

    /**
     * Returns queue instance by name.
     *
     * @param <V> type of values
     * @param name name of object
     * @return Queue object
     */
    <V> RQueueReactive<V> getQueue(String name);

    /**
     * Returns queue instance by name
     * using provided codec for queue objects.
     * 
     * @param <V> type of values
     * @param name name of object
     * @param codec codec for values
     * @return Queue object
     */
    <V> RQueueReactive<V> getQueue(String name, Codec codec);

    /**
     * Returns unbounded queue instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return queue object
     */
    <V> RQueueReactive<V> getQueue(PlainOptions options);

    /**
     * Returns a reliable queue instance by name.
     * <p>
     * The reliable queue provides guaranteed message delivery through acknowledgment mechanisms
     * and synchronous replication.
     *
     * @param name the name of the queue
     * @param <V> the type of elements in this queue
     * @return Reliable queue instance
     */
    <V> RReliableQueueReactive<V> getReliableQueue(String name);

    /**
     * Returns a reliable queue instance by name and provided codec.
     * <p>
     * The reliable queue provides guaranteed message delivery through acknowledgment mechanisms
     * and synchronous replication.
     *
     * @param name the name of the queue
     * @param codec the codec used for message serialization and deserialization
     * @param <V> the type of elements in this queue
     * @return Reliable queue instance
     */
    <V> RReliableQueueReactive<V> getReliableQueue(String name, Codec codec);

    /**
     * Returns a reliable queue instance with the specified configuration options.
     * <p>
     * The reliable queue provides guaranteed message delivery through acknowledgment mechanisms
     * and synchronous replication.
     *
     * @param options configuration options for the reliable queue
     * @param <V> the type of elements in this queue
     * @return Reliable queue instance
     */
    <V> RReliableQueueReactive<V> getReliableQueue(PlainOptions options);

    /**
     * Returns RingBuffer based queue.
     * 
     * @param <V> value type
     * @param name name of object
     * @return RingBuffer object
     */
    <V> RRingBufferReactive<V> getRingBuffer(String name);
    
    /**
     * Returns RingBuffer based queue.
     * 
     * @param <V> value type
     * @param name name of object
     * @param codec codec for values
     * @return RingBuffer object
     */
    <V> RRingBufferReactive<V> getRingBuffer(String name, Codec codec);

    /**
     * Returns RingBuffer based queue instance with specified <code>options</code>.
     *
     * @param <V> value type
     * @param options instance options
     * @return RingBuffer object
     */
    <V> RRingBufferReactive<V> getRingBuffer(PlainOptions options);

    /**
     * Returns blocking queue instance by name.
     * 
     * @param <V> type of values
     * @param name name of object
     * @return BlockingQueue object
     */
    <V> RBlockingQueueReactive<V> getBlockingQueue(String name);

    /**
     * Returns blocking queue instance by name
     * using provided codec for queue objects.
     * 
     * @param <V> type of values
     * @param name name of object
     * @param codec code for values
     * @return BlockingQueue object
     */
    <V> RBlockingQueueReactive<V> getBlockingQueue(String name, Codec codec);

    /**
     * Returns unbounded blocking queue instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return BlockingQueue object
     */
    <V> RBlockingQueueReactive<V> getBlockingQueue(PlainOptions options);

    /**
     * Returns unbounded blocking deque instance by name.
     * 
     * @param <V> type of value
     * @param name name of object
     * @return BlockingDeque object
     */
    <V> RBlockingDequeReactive<V> getBlockingDeque(String name);

    /**
     * Returns unbounded blocking deque instance by name
     * using provided codec for deque objects.
     * 
     * @param <V> type of value
     * @param name name of object
     * @param codec deque objects codec
     * @return BlockingDeque object
     */
    <V> RBlockingDequeReactive<V> getBlockingDeque(String name, Codec codec);

    /**
     * Returns unbounded blocking deque instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return BlockingDeque object
     */
    <V> RBlockingDequeReactive<V> getBlockingDeque(PlainOptions options);

    /**
     * Returns transfer queue instance by name.
     *
     * @param <V> type of values
     * @param name name of object
     * @return TransferQueue object
     */
    <V> RTransferQueueReactive<V> getTransferQueue(String name);

    /**
     * Returns transfer queue instance by name
     * using provided codec for queue objects.
     *
     * @param <V> type of values
     * @param name name of object
     * @param codec code for values
     * @return TransferQueue object
     */
    <V> RTransferQueueReactive<V> getTransferQueue(String name, Codec codec);

    /**
     * Returns transfer queue instance with specified <code>options</code>.
     *
     * @param <V> type of values
     * @param options instance options
     * @return TransferQueue object
     */
    <V> RTransferQueueReactive<V> getTransferQueue(PlainOptions options);

    /**
     * Returns deque instance by name.
     * 
     * @param <V> type of values
     * @param name name of object
     * @return Deque object
     */
    <V> RDequeReactive<V> getDeque(String name);

    /**
     * Returns deque instance by name
     * using provided codec for deque objects.
     * 
     * @param <V> type of values
     * @param name name of object
     * @param codec coded for values
     * @return Deque object
     */
    <V> RDequeReactive<V> getDeque(String name, Codec codec);

    /**
     * Returns unbounded deque instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return Deque object
     */
    <V> RDequeReactive<V> getDeque(PlainOptions options);

    /**
     * Returns "atomic long" instance by name.
     *
     * @param name of the "atomic long"
     * @return AtomicLong object
     */
    RAtomicLongReactive getAtomicLong(String name);

    /**
     * Returns atomicLong instance with specified <code>options</code>.
     *
     * @param options instance options
     * @return AtomicLong object
     */
    RAtomicLongReactive getAtomicLong(CommonOptions options);

    /**
     * Returns "atomic double" instance by name.
     *
     * @param name of the "atomic double"
     * @return AtomicLong object
     */
    RAtomicDoubleReactive getAtomicDouble(String name);

    /**
     * Returns atomicDouble instance with specified <code>options</code>.
     *
     * @param options instance options
     * @return AtomicDouble object
     */
    RAtomicDoubleReactive getAtomicDouble(CommonOptions options);

    /**
     * Returns object for remote operations prefixed with the default name (redisson_remote_service)
     * 
     * @return RemoteService object
     */
    @Deprecated
    RRemoteService getRemoteService();
    
    /**
     * Returns object for remote operations prefixed with the default name (redisson_remote_service)
     * and uses provided codec for method arguments and result.
     * 
     * @param codec codec for response and request
     * @return RemoteService object
     */
    @Deprecated
    RRemoteService getRemoteService(Codec codec);

    /**
     * Returns object for remote operations prefixed with the specified name
     *
     * @param name the name used as the Redis key prefix for the services
     * @return RemoteService object
     */
    RRemoteService getRemoteService(String name);
    
    /**
     * Returns object for remote operations prefixed with the specified name
     * and uses provided codec for method arguments and result.
     *
     * @param name the name used as the Redis key prefix for the services
     * @param codec codec for response and request
     * @return RemoteService object
     */
    RRemoteService getRemoteService(String name, Codec codec);

    /**
     * Returns object for remote operations prefixed with specified <code>options</code>.
     *
     * @param options instance options
     * @return RemoteService object
     */
    RRemoteService getRemoteService(PlainOptions options);

    /**
     * Returns bitSet instance by name.
     *
     * @param name name of object
     * @return BitSet object
     */
    RBitSetReactive getBitSet(String name);

    /**
     * Returns bitSet instance with specified <code>options</code>.
     *
     * @param options instance options
     * @return BitSet object
     */
    RBitSetReactive getBitSet(CommonOptions options);

    /**
     * Returns bloom filter instance by name.
     *
     * @param <V> type of value
     * @param name name of object
     * @return BloomFilter object
     */
    <V> RBloomFilterReactive<V> getBloomFilter(String name);

    /**
     * Returns bloom filter instance by name
     * using provided codec for objects.
     *
     * @param <V> type of value
     * @param name name of object
     * @param codec codec for values
     * @return BloomFilter object
     */
    <V> RBloomFilterReactive<V> getBloomFilter(String name, Codec codec);

    /**
     * Returns bloom filter instance with specified <code>options</code>.
     *
     * @param <V> type of value
     * @param options instance options
     * @return BloomFilter object
     */
    <V> RBloomFilterReactive<V> getBloomFilter(PlainOptions options);

    /**
     * Returns interface for Redis Function feature
     *
     * @return function object
     */
    RFunctionReactive getFunction();

    /**
     * Returns interface for Redis Function feature using provided codec
     *
     * @param codec codec for params and result
     * @return function interface
     */
    RFunctionReactive getFunction(Codec codec);

    /**
     * Returns interface for Redis Function feature with specified <code>options</code>.
     *
     * @param options instance options
     * @return function object
     */
    RFunctionReactive getFunction(OptionalOptions options);

    /**
     * Returns script operations object
     *
     * @return Script object
     */
    RScriptReactive getScript();

    /**
     * Returns script operations object using provided codec.
     * 
     * @param codec codec for params and result
     * @return Script object
     */
    RScriptReactive getScript(Codec codec);

    /**
     * Returns script operations object with specified <code>options</code>.
     *
     * @param options instance options
     * @return Script object
     */
    RScriptReactive getScript(OptionalOptions options);

    /**
     * Returns vector set instance by name.
     * <p>
     * Requires <b>Redis 8.0.0 and higher.</b>
     *
     * @param name - name of vector set
     * @return vector set instance
     */
    RVectorSetReactive getVectorSet(String name);

    /**
     * Returns vector set instance by name with specified <code>options</code>.
     * <p>
     * Requires <b>Redis 8.0.0 and higher.</b>
     *
     * @param options instance options
     * @return vector set instance
     */
    RVectorSetReactive getVectorSet(CommonOptions options);

    /**
     * Creates transaction with <b>READ_COMMITTED</b> isolation level.
     * 
     * @param options transaction configuration
     * @return Transaction object
     */
    RTransactionReactive createTransaction(TransactionOptions options);
    
    /**
     * Return batch object which executes group of
     * command in pipeline.
     *
     * See <a href="http://redis.io/topics/pipelining">http://redis.io/topics/pipelining</a>
     *
     * @param options batch configuration
     * @return Batch object
     */
    RBatchReactive createBatch(BatchOptions options);

    /**
     * Return batch object which executes group of
     * command in pipeline.
     *
     * See <a href="http://redis.io/topics/pipelining">http://redis.io/topics/pipelining</a>
     *
     * @return Batch object
     */
    RBatchReactive createBatch();

    /**
     * Returns keys operations.
     * Each of Redis/Redisson object associated with own key
     *
     * @return Keys object
     */
    RKeysReactive getKeys();

    /**
     * Returns interface for operations over Redis keys with specified <code>options</code>.
     * Each of Redis/Redisson object is associated with own key.
     *
     * @return Keys object
     */
    RKeysReactive getKeys(KeysOptions options);

    /**
     * Use {@link RedissonClient#shutdown()} instead
     */
    @Deprecated
    void shutdown();

    /**
     * Allows to get configuration provided
     * during Redisson instance creation. Further changes on
     * this object not affect Redisson instance.
     *
     * @return Config object
     */
    Config getConfig();
    
    /**
     * Use {@link org.redisson.api.RedissonClient#getRedisNodes(org.redisson.api.redisnode.RedisNodes)} instead
     *
     * @return NodesGroup object
     */
    @Deprecated
    NodesGroup<Node> getNodesGroup();

    /**
     * Use {@link org.redisson.api.RedissonClient#getRedisNodes(org.redisson.api.redisnode.RedisNodes)} instead
     *
     * @return NodesGroup object
     */
    @Deprecated
    NodesGroup<ClusterNode> getClusterNodesGroup();

    /**
     * Returns {@code true} if this Redisson instance has been shut down.
     *
     * @return <code>true</code> if this Redisson instance has been shut down otherwise <code>false</code>
     */
    boolean isShutdown();

    /**
     * Returns {@code true} if this Redisson instance was started to be shutdown
     * or was shutdown {@link #isShutdown()} already.
     *
     * @return <code>true</code> if this Redisson instance was started to be shutdown
     * or was shutdown {@link #isShutdown()} already otherwise <code>false</code>
     */
    boolean isShuttingDown();

    /**
     * Returns id of this Redisson instance
     * 
     * @return id
     */
    String getId();
    
}
