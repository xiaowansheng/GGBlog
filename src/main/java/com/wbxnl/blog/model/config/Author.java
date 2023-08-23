package com.wbxnl.blog.model.config;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.constant.keys.WebsiteConfig;
import com.wbxnl.blog.model.entity.SystemConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者信息
 *
 * @Author xiaowansheng
 * @Date 2023/8/15 21:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    /**
     * 默认redis查询的key
     */
    public static final String KEY = WebsiteConfig.AUTHOR;
    /**
     * 默认的作者信息
     */
    public final static SystemConfig DEFAULT_CONFIG = new SystemConfig();

    static {

        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length - 1])
                .setLabel("作者信息")
                .setDescription("作者信息json字符串");
        Author author = new Author("作者", "http://", "没有介绍");
        DEFAULT_CONFIG.setValue(JSON.toJSONString(author));
    }

    /**
     * 作者昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;
    /**
     * 个人介绍
     */
    private String introduction;
}
