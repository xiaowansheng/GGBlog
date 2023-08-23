package com.wbxnl.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wansheng
 * @createDate 2023/2/27 5:57
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEmus {

    Email("Email","邮箱"),
    QQ("QQ","QQ"),
    Wechat("Wechat","微信"),
    Weibo("Weibo","微博");

    private final String name;

    private final String label;

}
