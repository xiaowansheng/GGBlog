package com.wbxnl.blog.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wansheng
 * @createDate 2022/9/11 21:47
 */
@AllArgsConstructor
@Getter
public enum PictureTypeEums {
    DRAFT("unknown", "未知"),
    ORIGINAL("original", "原创"),
    REPRINT("reprint", "转载"),
    TRANSLATION("second", "二创");
    /**
     * 文章类型码
     */
    private final String name;

    /**
     * 文章类型名称
     */
    private final String label;

    /**
     * 根据名称获取图片类型枚举
     * @param name
     * @return
     */
    public static PictureTypeEums getPictureTypeEums(String name){
        for (PictureTypeEums value : values()) {
            if(value.getName().equals(name)){
                return value;
            }
        }
        return null;
    }

}
