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
 * 启用的组件
 * @Author xiaowansheng
 * @Date 2023/8/15 22:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Component {
    public final static String KEY= WebsiteConfig.COMPONENT;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("功能组件")
                .setDescription("组件信息json数组字符串");
        // 默认的组件配置
        Component login = new Component("Login", "登录组件", 1);
        Component comment = new Component("Comment", "评论组件", 1);
        Component thirdPartLogin = new Component("ThirdPartLogin", "第三方登录组件", 1);
        Component register = new Component("Register", "注册组件", 1);
        Component search = new Component("Search", "搜索组件", 1);
        Component[] ct={login,comment,thirdPartLogin,register,search};
        DEFAULT_CONFIG.setValue(JSON.toJSONString(ct));
    }

    /**
     * 组件名称
     */
    private String name;
    /**
     * 组件标签
     */
    private String label;
    /**
     * 是否启用
     */
    private Integer show;
}
