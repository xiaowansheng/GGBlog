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
 * 启用的第三方登录
 * @Author xiaowansheng
 * @Date 2023/8/15 22:27
 */@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ThirdPartyLogin {
    public final static String KEY= WebsiteConfig.THIRD_PART;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("第三方的登录")
                .setDescription("第三方登录信息json数组字符串");
        // 默认的登录配置
        ThirdPartyLogin email = new ThirdPartyLogin("Email", "邮箱", 1);
        ThirdPartyLogin qq = new ThirdPartyLogin("QQ", "QQ", 1);
        ThirdPartyLogin weibo = new ThirdPartyLogin("Weibo", "微博", 1);
        ThirdPartyLogin google = new ThirdPartyLogin("Google", "谷歌", 1);
        ThirdPartyLogin twitter = new ThirdPartyLogin("Twitter", "推特", 1);
        ThirdPartyLogin facebook = new ThirdPartyLogin("Facebook", "脸书", 1);
        ThirdPartyLogin github = new ThirdPartyLogin("Github", "Github", 1);
        ThirdPartyLogin gitee = new ThirdPartyLogin("Gitee", "码云", 1);
        ThirdPartyLogin[] tpl={email,qq,weibo,google,twitter,facebook,github,gitee};
        DEFAULT_CONFIG.setValue(JSON.toJSONString(tpl));
    }

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单标签
     */
    private String label;
    /**
     * 是否显示
     */
    private Integer show;
}
