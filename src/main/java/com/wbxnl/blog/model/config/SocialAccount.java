package com.wbxnl.blog.model.config;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.constant.keys.WebsiteConfig;
import com.wbxnl.blog.model.entity.SystemConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者的社交账号展示
 * @Author xiaowansheng
 * @Date 2023/8/15 21:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SocialAccount {
    /**
     * 默认的社交账号
     */
    public final static String KEY= WebsiteConfig.ASOCIAL_ACCOUNT;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("社交账号")
                .setDescription("作者社交信息json数组字符串");
        // 默认的社交账号
        SocialAccount email = new SocialAccount().setName("Email").setLabel("邮箱").setShow(1);
        SocialAccount qq = new SocialAccount().setName("QQ").setLabel("QQ").setShow(1);
        SocialAccount wechat = new SocialAccount().setName("Wechat").setLabel("微信").setShow(1);
        SocialAccount weibo = new SocialAccount().setName("Weibo").setLabel("微博").setShow(1);
        SocialAccount facebook = new SocialAccount().setName("Facebook").setLabel("脸书").setShow(1);
        SocialAccount twitter = new SocialAccount().setName("Twitter").setLabel("推特").setShow(1);
        SocialAccount telegram = new SocialAccount().setName("Telegram").setLabel("电报").setShow(1);
        SocialAccount github = new SocialAccount().setName("Github").setLabel("Github").setShow(1);
        SocialAccount gitee = new SocialAccount().setName("Gitee").setLabel("码云").setShow(1);
        SocialAccount[] accounts={
                email,qq,wechat,weibo,facebook,twitter,telegram,github,gitee
        };
        DEFAULT_CONFIG.setValue(JSON.toJSONString(accounts));
    }
    /**
     * 名称
     */
    private String name;
    /**
     * 标签
     */
    private String label;
    /**
     * 地址
     */
    private String value="http://";
    /**
     * 是否显示
     */
    private Integer show;
}
