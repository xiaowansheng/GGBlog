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
 * 通知信息配置
 * @Author xiaowansheng
 * @Date 2023/8/15 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Notice {
    public final static String KEY= WebsiteConfig.NOTICE;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    /**
     * 注册通知
     */
    public final static String REGISTER="Register";
    /**
     * 登录通知
     */
    public final static String LOGIN="Login";
    /**
     * 留言通知
     */
    public final static String LEAVE_WORD="LeaveWord";
    /**
     * 通知相关的用户
     */
    public final static String RELATE="Relate";
    /**
     * 新留言通知
     */
    public final static String COMMENT="Comment";
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("通知设置")
                .setDescription("通知设置json数组字符串");
        // 默认的通知配置
        Notice register = new Notice(REGISTER, "用户注册通知", 1);
        Notice login = new Notice(LOGIN, "用户登录通知", 1);
        Notice leaveWord = new Notice(LEAVE_WORD, "新留言通知", 1);
        Notice relate = new Notice(RELATE, "“与用户相关”通知", 1);
        Notice comment= new Notice(COMMENT, "新增根评论通知", 1);
//        Notice reply = new Notice("Reply", "被回复通知", 1);
        Notice[] no={comment,relate,leaveWord,register,login};
        DEFAULT_CONFIG.setValue(JSON.toJSONString(no));
    }

    /**
     * 通知名称
     */
    private String name;
    /**
     * 通知标签
     */
    private String label;
    /**
     * 是否开启
     */
    private Integer show;
}
