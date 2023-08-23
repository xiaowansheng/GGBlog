package com.wbxnl.blog.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xiaowansheng
 * @Date 2023/8/14 10:22
 */
@Getter
public enum ViewTypeEums {

    WEBSITE("website","网站"),
    ARTICLE("article","文章"),
    TALK("talk","说说"),
    ALBUM("album","相册"),
    FRIEND("friend","友链"),
    GUESTBOOK("guestbook","留言板"),
    ABOUT("about","关于");
    /**
     * 视图类型关键字名称
     */
    private final String name;
    /**
     * 视图类型名称
     */
    private final String label;

    ViewTypeEums(String name, String label) {
        this.name = name;
        this.label = label;
    }


    /**
     * 检查 name 是否是枚举中的类型
     * @param name
     * @return
     */
    public static boolean isExist(String name){
        boolean match = Arrays.stream(values()).allMatch(viewTypeEums -> viewTypeEums.getName().equals(name));
        return match;
    }

    /**
     * 根据 name 获取枚举类型
     * @param name
     * @return
     */
    public static ViewTypeEums getViewType(String name){
        List<ViewTypeEums> typeEums = Arrays.stream(values()).filter(viewTypeEums -> viewTypeEums.getName().equals(name)).collect(Collectors.toList());
        if(typeEums!=null&&typeEums.size()==1){
            return typeEums.get(0);
        }
        return null;
    }
}
