package com.wbxnl.blog.common.config;

import com.wbxnl.blog.common.enums.NoticeConfigEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/23 16:18
 */
@Data
@NoArgsConstructor(access = AccessLevel.NONE)
public class NoticeConfig {
    /**
     * 通知配置
     */
    public static final Map<NoticeConfigEnum, Boolean> CONFIG_MAP = new HashMap<>(NoticeConfigEnum.values().length);


    static {
        // 初始化配置
        for (NoticeConfigEnum configEnum : NoticeConfigEnum.values()) {
            CONFIG_MAP.put(configEnum, true);
        }
    }
}
