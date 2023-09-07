package com.wbxnl.blog.model.config;

import com.wbxnl.blog.constant.keys.WebsiteConfig;
import com.wbxnl.blog.model.entity.SystemConfig;
import lombok.Data;

/**
 * @Author xiaowansheng
 * @Date 2023/8/16 21:01
 */
public class About {

    public final static String KEY= WebsiteConfig.ABOUT;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("关于作者")
                .setValue("作者没有写任何的介绍信息哦~")
                .setDescription("关于作者的信息");
    }
}
