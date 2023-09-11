package com.wbxnl.blog.model.config;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.constant.keys.WebsiteConfig;
import com.wbxnl.blog.model.entity.SystemConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 默认头像配置
 * @Author xiaowansheng
 * @Date 2023/8/15 23:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {

    /**
     * 默认的头像配置
     */
    public final static String KEY= WebsiteConfig.AVATAR;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("默认头像设置")
                .setDescription("默认头像设置json数组字符串");
        // 默认的头像配置
        Avatar userDefault = new Avatar("User","用户默认头像","");
        Avatar visitorDefault = new Avatar("Visitor","游客默认头像","");
//        Avatar anonymousDefault = new Avatar("Anonymous","匿名用户默认头像","http://");
        Avatar[] at={userDefault,visitorDefault};
        DEFAULT_CONFIG.setValue(JSON.toJSONString(at));
    }

    /**
     * 属性名
     */
    private String name;
    /**
     * 头像名称
     */
    private String label;
    /**
     * 头像地址
     */
    private String url;
}
