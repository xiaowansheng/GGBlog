package com.wbxnl.blog.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wansheng
 * @createDate 2022/9/11 21:47
 */
@AllArgsConstructor
@Getter
public enum ArticleTypeEums {
    DRAFT("draft", "草稿"),
    ORIGINAL("original", "原创"),
    REPRINT("reprint", "转载"),
    TRANSLATION("translate", "翻译");
    /**
     * 文章类型码
     */
    private final String code;

    /**
     * 文章类型名称
     */
    private final String name;

    /**
     * 根据名称获取文章类型枚举
     * @param name
     * @return
     */
    public static ArticleTypeEums getArticleTypeEums(String name){
        for (ArticleTypeEums value : values()) {
            if(value.getName().equals(name)){
                return value;
            }
        }
        return null;
    }

}
