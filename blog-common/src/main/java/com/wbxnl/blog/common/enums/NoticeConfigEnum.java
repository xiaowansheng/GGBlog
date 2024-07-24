package com.wbxnl.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/24 15:50
 */
@Getter
@AllArgsConstructor
public enum NoticeConfigEnum {
    REGISTER("register"),
    LOGIN("login"),
    LEAVE_WORD("leaveWord"),
    COMMENT("comment"),
    REPLIED("replied");

    private final String name;

}
