package com.wbxnl.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/25 14:41
 */
@AllArgsConstructor
@Getter
public enum ConfigEnum {
    /**
     * 关于作者
     */
    ABOUT("about"),
    /**
     * 作者社交账号信息
     */
    ACCOUNT("account"),
    /**
     * 作者信息
     */
    AUTHOR("author"),
    /**
     * 默认头像
     */
    AVATAR("avatar"),
    /**
     * 组件配置
     */
    COMPONENT("component"),
    /**
     * 前台页面封面
     */
    COVER("cover"),
    /**
     * 登录配置
     */
    LOGIN("login"),
    /**
     * 菜单配置
     */
    MENU("menu"),
    /**
     * 公告配置
     */
    NOTICE("notice"),
    /**
     * 隐私配置
     */
    PRIVACY("privacy"),
    /**
     * 赞赏配置
     */
    REWARD("reward"),
    /**
     * 网站配置
     */
    WEBSITE("website");

    /**
     * 配置名称
     */
    private final String name;

    /**
     * 用户需要的配置信息列表
     */
    public static ConfigEnum[] getUserConfigList(){
        return new ConfigEnum[]{MENU, AUTHOR, LOGIN, PRIVACY, REWARD, ABOUT, ACCOUNT, COVER, WEBSITE, AVATAR, COMPONENT};
    }
}
