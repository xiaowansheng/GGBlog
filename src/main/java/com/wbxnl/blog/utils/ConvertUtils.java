/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.wbxnl.blog.utils;

import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.exception.BlogException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 转换工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ConvertUtils {
    private static Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    /**
     * 将一个对象转换为另一个目标对象
     * @param source 源对象
     * @param target 目标对象类型
     * @return 返回目标对象
     * @param <T>
     */
    public static <T> T sourceToTarget(Object source, Class<T> target){
        if(source == null){
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.getConstructor().newInstance();
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            logger.error("convert error ", e);
            throw new BlogException(OperationStateCode.CONVERT_DATA_FAILURE);
        }

        return targetObject;
    }

    /**
     * 将一个对象集合，转换为另外一个对象集合
     * @param sourceList 源对象集合
     * @param target 目标对象类型
     * @return 返回目标对象集合
     * @param <T>
     */
    public static <T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> target){
        if(sourceList == null){
            return null;
        }

        List targetList = new ArrayList<>(sourceList.size());
        try {
            for(Object source : sourceList){
                T targetObject= target.getConstructor().newInstance();
                BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        }catch (Exception e){
            logger.error("convert error ", e);
            throw new BlogException(OperationStateCode.CONVERT_DATA_FAILURE);
        }
        return targetList;
    }


    /**
     * 将map集合中的属性转换到bean中
     * @param map 源map集合
     * @param target 目标对象类型
     * @return 返回目标对象实体
     * @param <T>
     */
    public static <T>T mapToBean(Map<String,Object> map, Class<T> target){
        T obj = null;
        try {
            obj = target.getDeclaredConstructor().newInstance();
            if (CollectionUtils.isEmpty(map)) {
                return obj;
            }
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    Object o = map.get(property.getName());
                    if (o != null) {
                        setter.invoke(obj, o);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    /**
     * 用来处理控制器接收的map参数集合（因为map的键值属性都为string），将map转换为需要的对象
     *
     * 将map集合中的String转换到bean中
     * @param map 源map集合
     * @param target 目标对象类型
     * @return 返回目标对象实体
     * @param <T>
     */
    public static <T>T mapStringToBean(Map<String,Object> map, Class<T> target){
        T obj = null;
        try {
            obj = target.getDeclaredConstructor().newInstance();
            if (CollectionUtils.isEmpty(map)) {
                return obj;
            }
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    Object o = map.get(property.getName());
                    if (o != null) {
                        Class<?> returnType = property.getPropertyType();
                        Object result = convertStringToType((String) o, returnType);
                        setter.invoke(obj, result);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    /**
     * 将字符串转换为指定类型
     * @param stringValue 字符串
     * @param targetType 需要转换的类型
     * @return 返回需要转换的类型的对象
     * @param <T>
     * @throws Exception
     */
    public static <T> T convertStringToType(String stringValue, Class<T> targetType) throws Exception {
        if (stringValue == null) {
            throw new IllegalArgumentException("String value cannot be null");
        }

        if (targetType == null) {
            throw new IllegalArgumentException("Target type cannot be null");
        }

        try {
            if (targetType == String.class) {
                return targetType.cast(stringValue);
            } else if (targetType == Integer.class || targetType == int.class) {
                return targetType.cast(Integer.parseInt(stringValue));
            } else if (targetType == Long.class || targetType == long.class) {
                return targetType.cast(Long.parseLong(stringValue));
            } else if (targetType == Double.class || targetType == double.class) {
                return targetType.cast(Double.parseDouble(stringValue));
            } else if (targetType == Boolean.class || targetType == boolean.class) {
                return targetType.cast(Boolean.parseBoolean(stringValue));
            } else {
                Constructor<T> constructor = targetType.getDeclaredConstructor(String.class);
                return constructor.newInstance(stringValue);
            }
        } catch (NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Failed to convert string to specified type", e);
        }
    }
}