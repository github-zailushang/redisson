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
package org.redisson.micronaut;

import io.micronaut.context.annotation.*;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.scheduling.TaskExecutors;
import org.redisson.MapCacheNativeWrapper;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RMapCacheNative;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.micronaut.cache.RedissonCacheConfiguration;
import org.redisson.micronaut.cache.RedissonCacheNativeConfiguration;
import org.redisson.micronaut.cache.RedissonSyncCache;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Nikita Koksharov
 *
 */
@Factory
public class RedissonFactory {

    @Requires(beans = Config.class)
    @Singleton
    @Bean(preDestroy = "shutdown")
    public RedissonClient redisson(Config config) {
        return Redisson.create(config);
    }

    @EachBean(RedissonCacheConfiguration.class)
    public RedissonSyncCache cache(@Parameter RedissonCacheConfiguration configuration,
                                   RedissonClient redisson,
                                   ConversionService conversionService,
                                   @Named(TaskExecutors.IO) ExecutorService executorService) {
        if (configuration.getExpireAfterAccess().toMillis() != 0
                || configuration.getExpireAfterWrite().toMillis() != 0
                || configuration.getMaxSize() != 0) {

            RMapCache<Object, Object> mapCache = redisson.getMapCache(configuration.getMapCacheOptions());
            return new RedissonSyncCache(conversionService, mapCache, mapCache, executorService, configuration);
        }
        RMap<Object, Object> map = redisson.getMap(configuration.getMapOptions());
        return new RedissonSyncCache(conversionService, null, map, executorService, configuration);
    }

    @EachBean(RedissonCacheNativeConfiguration.class)
    public RedissonSyncCache cache(@Parameter RedissonCacheNativeConfiguration configuration,
                                   RedissonClient redisson,
                                   ConversionService conversionService,
                                   @Named(TaskExecutors.IO) ExecutorService executorService) {

        RMapCache<Object, Object> mapCache = null;
        RMapCacheNative<Object, Object> map = redisson.getMapCacheNative(configuration.getMapOptions());
        if (configuration.getExpireAfterWrite().toMillis() != 0) {
            mapCache = new MapCacheNativeWrapper<>(map);
        }

        return new RedissonSyncCache(conversionService, mapCache, map, executorService, configuration);
    }


}
