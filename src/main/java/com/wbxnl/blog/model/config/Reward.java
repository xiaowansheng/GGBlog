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
 * 打赏配置
 * @Author xiaowansheng
 * @Date 2023/8/15 23:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {

    /**
     * 默认的支付界面配置
     */
    public final static String KEY= WebsiteConfig.REWARD;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("用户打赏")
                .setDescription("打赏配置信息json数组字符串");
        // 默认的头像配置
        Reward alipay = new Reward("Alipay","支付宝打赏","http://");
        Reward wechatPay = new Reward("Wechat","微信打赏","http://");
        Reward[] rd={alipay,wechatPay};
        DEFAULT_CONFIG.setValue(JSON.toJSONString(rd));
    }

    /**
     * 属性名
     */
    private String name;
    /**
     * 支付名称名称
     */
    private String label;
    /**
     * 支付二维码地址
     */
    private String url;
}
