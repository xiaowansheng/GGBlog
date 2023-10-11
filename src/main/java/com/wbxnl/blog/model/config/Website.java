package com.wbxnl.blog.model.config;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.constant.keys.WebsiteConfig;
import com.wbxnl.blog.model.entity.SystemConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 网站信息
 * @Author xiaowansheng
 * @Date 2023/8/15 21:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Website {

    public final static String KEY= WebsiteConfig.WEBSITE;
    public final static SystemConfig DEFAULT_CONFIG =new SystemConfig();
    static {
        String[] split = KEY.split(":");
        DEFAULT_CONFIG.setName(split[split.length-1])
                .setLabel("网站信息")
                .setDescription("网站信息json数组字符串");
        Website website =new Website()
                .setTitle("博客")
                .setIcon("")
                .setHomeTitle("满天星辰")
                .setWebsite("http://")
                .setGithub("https://github.com/xiaowanshneg")
                .setIntroduction("没有介绍")
                .setCreateTime("2023-10-11 00:00:00")
                .setRecordNumber("XXXXXXXXXXXXX")
                .setBulletin("没有通知信息~");
        DEFAULT_CONFIG.setValue(JSON.toJSONString(website));
    }
    /**
     * 网站标题
     */
    private String title;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 主页标题
     */
    private String homeTitle;
    /**
     * 网站地址
     */
    private String website;
    /**
     * GITHUB开源链接
     */
    private String github;
    /**
     * 网站介绍
     */
    private String introduction;
    /**
     * 建站时间
     */
    private String createTime;
    /**
     * 备案号
     */
    private String recordNumber;

    /**
     * 通知信息
     */
    private String bulletin;
}
