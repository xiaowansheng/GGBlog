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
 * 隐私和安全设置
 * @Author xiaowansheng
 * @Date 2023/8/15 22:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PrivacyAndSecurity {
    public final static String KEY= WebsiteConfig.PRIVACY;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("隐私和安全设置")
                .setDescription("隐私和安全设置信息json数组字符串");
        // 默认的隐私配置
        PrivacyAndSecurity address = new PrivacyAndSecurity().setName("Address").setLabel("显示归属地信息").setShow(1);
        PrivacyAndSecurity device = new PrivacyAndSecurity().setName("Device").setLabel("显示设备信息").setShow(1);
        PrivacyAndSecurity browser = new PrivacyAndSecurity().setName("Browser").setLabel("显示浏览器信息").setShow(1);
        PrivacyAndSecurity location = new PrivacyAndSecurity().setName("Location").setLabel("显示定位信息").setShow(1);
        PrivacyAndSecurity visitor = new PrivacyAndSecurity().setName("Visitor").setLabel("允许游客操作").setShow(1);
        PrivacyAndSecurity loginVerificationCode = new PrivacyAndSecurity().setName("LoginVerificationCode").setLabel("登录验证码").setShow(1);
        PrivacyAndSecurity commentVerificationCode = new PrivacyAndSecurity().setName("CommentVerificationCode").setLabel("评论验证码").setShow(1);
        PrivacyAndSecurity leaveWordVerificationCode = new PrivacyAndSecurity().setName("LeaveWordVerificationCode").setLabel("留言验证码").setShow(1);
        PrivacyAndSecurity[] pas={address,device,browser,location,visitor,loginVerificationCode,commentVerificationCode,leaveWordVerificationCode};
        DEFAULT_CONFIG.setValue(JSON.toJSONString(pas));
    }

    /**
     * 隐私名称
     */
    private String name;
    /**
     * 隐私标签
     */
    private String label;
    /**
     * 是否开启
     */
    private Integer show;
}
