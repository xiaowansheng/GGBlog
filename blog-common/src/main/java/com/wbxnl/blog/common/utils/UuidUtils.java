package com.wbxnl.blog.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 14:45
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class UuidUtils {
    /**
     * 构造一个短的uuid
     * @return uuid
     */
    public static String shortUuid() {
        return UuidUtils.shortUuid();
    }

    /**
     * 正常长度的uuid
     * @return uuid
     */
    public static String uuid() {
        return UuidUtils.uuid();
    }
}
