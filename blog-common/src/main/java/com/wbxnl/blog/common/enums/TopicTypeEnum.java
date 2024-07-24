package com.wbxnl.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/24 16:33
 */
@Getter
@AllArgsConstructor
public enum TopicTypeEnum {
    ARTICLE("article"),
    TALK("talk"),
    ALBUM("album"),
    PICTURE("picture"),
    FRIEND_LINK("friend"),
    ABOUT("about");

    private final String name;
}
