package com.wbxnl.blog.infrastructure.persistent.redis;


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/26 9:34
 */
public interface IRedisService {
    /**
     * 根据key获取缓存的String类型数据
     *
     * @param key key
     * @return value
     */
    String get(String key);

    /**
     * 设置String类型的缓存
     *
     * @param key   key
     * @param value value
     */
    void set(String key, String value);

    /**
     * 获取一个String类型的缓存
     * @param key key
     * @return value
     */
    String getString(String key);

    /**
     * 设置一个有过期时间的String类型的缓存,单位秒
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     */
    void set(String key, String value, long timeout);


    /**
     * 设置Object类型的缓存
     *
     * @param key   key
     * @param value value
     */
    void setObject(String key, Object value);

    /**
     * 根据key获取缓存的Object类型数据
     *
     * @param key key
     * @return value
     */
    <T> T getObject(String key);

    /**
     * 设置一个有过期时间的Object类型的缓存,单位秒
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     */
    void setObject(String key, Object value, long timeout);

    /**
     * 添加一个值到set集合
     *
     * @param key   key
     * @param value value
     */
    long addValueToSet(String key, Object value);

    /**
     * 从Set中删除一个值
     *
     * @param key   key
     * @param value value
     */
    long removeValueFromSet(String key, Object value);

    /**
     * 判断一个值是否在Set中
     *
     * @param key   key
     * @param value value
     * @return true or false
     */
    boolean isValueInSet(String key, Object value);

    /**
     * 获取Set的长度
     *
     * @param key key
     * @return 长度
     */
    long getSetSize(String key);

    /**
     * 添加一个值到List
     *
     * @param key   key
     * @param value value
     * @return 长度
     */
    long addValueToList(String key, Object value);

    /**
     * 获取List的长度
     *
     * @param key key
     * @return 长度
     */
    long getListSize(String key);


    /**
     * 从List中删除一个值
     *
     * @param key   key
     * @param value value
     */
    void removeValueFromList(String key, Object value);

    /**
     * 添加一个值到Map
     *
     * @param key    key
     * @param mapKey mapKey
     * @param value  value
     * @return 长度
     */
    long addValueToMap(String key, Object mapKey, Object value);

    /**
     * 获取Map的长度
     *
     * @param key key
     * @return 长度
     */
    long getMapSize(String key);

    /**
     * 获取Map的长度
     *
     * @param key key
     * @return 长度
     */
    <T> T getMapValue(String key, Object mapKey);

    /**
     * 判断Map中是否存在一个key
     *
     * @param key    key
     * @param mapKey mapKey
     * @return
     */
    boolean isMapValueInMap(String key, Object mapKey);

    /**
     * 删除Map中的值
     *
     * @param key    key
     * @param mapKey mapKey
     */
    void removeValueFromMap(String key, Object mapKey);

    /**
     * 根据key删除缓存的数据
     *
     * @param key key
     */
    void del(String key);


    /**
     * 判断是否存在一个key
     *
     * @param key key
     * @return true or false
     */
    boolean contains(String key);

    /**
     * 设置key过期时间
     *
     * @param key     key
     * @param timeout 过期时间
     */
    void expire(String key, long timeout);

    /**
     * 获取key过期时间
     *
     * @param key key
     * @return 过期时间
     */
    long getExpire(String key);

}