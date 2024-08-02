package com.wbxnl.blog.api.front.service;

import com.wbxnl.blog.api.front.model.res.ConfigInfoRes;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 16:15
 */
public interface IConfigService {

    /**
     * 获取配置
     * @param configKey 配置key
     * @return 配置
     */
    ConfigInfoRes getConfig(String configKey);

    /**
     * 获取前台所需的配置列表
     * @return 配置列表
     */
    List<ConfigInfoRes> getConfigList();
}
