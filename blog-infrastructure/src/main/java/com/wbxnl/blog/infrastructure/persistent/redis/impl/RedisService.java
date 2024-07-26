package com.wbxnl.blog.infrastructure.persistent.redis.impl;

import com.wbxnl.blog.infrastructure.persistent.redis.IRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/26 9:34
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService implements IRedisService {

    private final StringRedisTemplate stringRedisTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }
    @Override
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setObject(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public long addValueToSet(String key, Object value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public long removeValueFromSet(String key, Object value) {
        return redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    public boolean isValueInSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public long getSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public long addValueToList(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public void removeValueFromList(String key, Object value) {
        redisTemplate.opsForList().remove(key, 0, value);
    }

    @Override
    public long addValueToMap(String key, Object mapKey, Object value) {
        redisTemplate.opsForHash().put(key, mapKey, value);
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public long getMapSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public <T> T getMapValue(String key, Object mapKey) {
        return (T) redisTemplate.opsForHash().get(key, mapKey);
    }

    @Override
    public boolean isMapValueInMap(String key, Object mapKey) {
        return redisTemplate.opsForHash().hasKey(key, mapKey);
    }

    @Override
    public void removeValueFromMap(String key, Object mapKey) {
        redisTemplate.opsForHash().delete(key, mapKey);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
        stringRedisTemplate.delete(key);
    }

    @Override
    public boolean contains(String key) {
        return redisTemplate.hasKey(key) || stringRedisTemplate.hasKey(key);
    }

    @Override
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

}
