package com.wbxnl.blog.constant.keys;

import static com.wbxnl.blog.constant.keys.ConfigPrefix.CONFIG_PREFIX;
import static com.wbxnl.blog.constant.keys.ConfigPrefix.INFO_PREFIX;

/**
 * 网站配置查询key
 * @Author xiaowansheng
 * @Date 2023/8/15 23:50
 */
public interface WebsiteConfig {

//信息配置
    /**
     * 网站信息
     */
    String WEBSITE=INFO_PREFIX+"website";
    /**
     * 作者信息
     */
    String AUTHOR=INFO_PREFIX+"author";
    /**
     * 社交账号
     */
    String ASOCIAL_ACCOUNT=INFO_PREFIX+"account";

//    系统配置
    /**
     * 网站封面图
     */
    String COVER=CONFIG_PREFIX+"cover";
    /**
     * 菜单展示
     */
    String MENU=CONFIG_PREFIX+"menu";
    /**
     * 第三方登录
     */
    String THIRD_PART =CONFIG_PREFIX+"login";
    /**
     * 系统组件启用配置
     */
    String COMPONENT =CONFIG_PREFIX+"component";
    /**
     * 隐私和安全配置
     */
    String PRIVACY =CONFIG_PREFIX+"privacy";
    /**
     * 通知信息配置
     */
    String NOTICE =CONFIG_PREFIX+"notice";
    /**
     * 用户默认头像配置
     */
    String AVATAR =CONFIG_PREFIX+"avatar";
    /**
     * 打赏配置
     */
    String REWARD =CONFIG_PREFIX+"reward";
    /**
     * 关于作者
     */
    String ABOUT=CONFIG_PREFIX+"about";
}
