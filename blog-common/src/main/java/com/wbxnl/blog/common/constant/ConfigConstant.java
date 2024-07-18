package com.wbxnl.blog.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 10:29
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class ConfigConstant {
    /**
     * 前台菜单配置
     */
    public static final String MENU = "menu";
    /**
     * 作者信息设置
     */
    public static final String AUTHOR = "author";
    /**
     * 登录可选项配置
     */
    public static final String LOGIN = "login";
    /**
     * 隐私设置
     */
    public static final String PRIVACY = "privacy";
    /**
     * 打赏配置
     */
    public static final String REWARD = "reward";
    /**
     * 关于网站作者配置
     */
    public static final String ABOUT = "about";
    /**
     * 系统通知配置
     */
    public static final String NOTICE = "notice";
    /**
     * 作者社交账号配置
     */
    public static final String ACCOUNT = "account";
    /**
     * 前台页面封面配置
     */
    public static final String COVER = "cover";
    /**
     * 网站信息配置
     */
    public static final String WEBSITE = "website";
    /**
     * 用户默认头像配置
     */
    public static final String AVATAR = "avatar";
    /**
     * 前台组件配置
     */
    public static final String COMPONENT = "component";
    /**
     * 用户前台显示配置
     */
    public static final String[] USER_CONFIG_LIST = {MENU, AUTHOR, LOGIN, PRIVACY, REWARD, ABOUT, ACCOUNT, COVER, WEBSITE, AVATAR, COMPONENT};
}
