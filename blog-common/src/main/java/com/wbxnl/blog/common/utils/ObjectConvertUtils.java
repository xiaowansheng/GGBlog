package com.wbxnl.blog.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 23:58
 */
public class ObjectConvertUtils {


    /**
     * 将List<T>转换为List<U>
     * @param sourceList 原始List
     * @param targetClass 目标类class
     * @return 转换后的List
     * @param <T> 原类型
     * @param <U> 目标类型
     */
    public static <T, U> List<U> convertList(List<T> sourceList, Class<U> targetClass) {
        if (sourceList == null) {
            return null;
        }

        List<U> targetList = new ArrayList<>();
        for (T source : sourceList) {
            U target = convert(source, targetClass);
            targetList.add(target);
        }

        return targetList;
    }

    /**
     * 将T转换为U
     * @param source 原对象
     * @param targetClass 目标类
     * @return 转换后的对象
     * @param <T> 源类型
     * @param <U> 目标类型
     */
    public static <T, U> U convert(T source, Class<U> targetClass) {
        if (source == null) {
            return null;
        }

        try {
            U target = targetClass.getDeclaredConstructor().newInstance();

            Field[] sourceFields = source.getClass().getDeclaredFields();
            Field[] targetFields = targetClass.getDeclaredFields();

            for (Field sourceField : sourceFields) {
                sourceField.setAccessible(true);
                Object value = sourceField.get(source);

                for (Field targetField : targetFields) {
                    int modifiers = targetField.getModifiers();
                    if (Modifier.isFinal(modifiers)) {
                        continue;
                    }
                    if (Modifier.isStatic(modifiers)) {
                        continue;
                    }

                    if (sourceField.getName().equals(targetField.getName()) &&
                            sourceField.getType().equals(targetField.getType())) {
                        targetField.setAccessible(true);
                        targetField.set(target, value);
                        break;
                    }
                }
            }

            return target;
        } catch (Exception e) {
            throw new RuntimeException("Object conversion error", e);
        }
    }

}
