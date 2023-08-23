package com.wbxnl.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 网站菜单封面
 * @author wansheng
 * @createDate 2023/2/8 22:47
 */
@AllArgsConstructor
@Getter
public enum TopicTypeEums {
    ARTICLE("article","文章"),
    Talk("talk","说说"),
    Album("album","相册"),
    PICTURE("picture","图片"),
    Friend("friend","友链"),
    About("about","关于");
    /**
     * 话题类型关键字名称
     */
    private final String name;
    /**
     * 话题类型名称
     */
    private final String label;

    /**
     * 根据名称获取话题类型枚举
     * @param name
     * @return
     */
    public static TopicTypeEums getTopicTypeEums(String name){
        for (TopicTypeEums value : values()) {
            if(value.name.equals(name)){
                return value;
            }
        }
        return null;
    }

}
