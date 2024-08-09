package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.ConfigDataReq;
import com.wbxnl.blog.api.admin.model.res.ConfigDetailRes;
import com.wbxnl.blog.common.vo.KeyData;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 14:52
 */
public interface IConfigService {

    /**
     * 添加配置
     *
     * @param configDataReq 配置信息
     * @return 添加结果
     */
    KeyData addConfig(ConfigDataReq configDataReq);

    /**
     * 修改配置
     * @param configDataReq 配置信息
     */
    void updateConfig(ConfigDataReq configDataReq);

    /**
     * 删除配置
     * @param id 配置id
     */
    void deleteConfig(Integer id);

    /**
     * 删除配置
     * @param ids 配置id
     */
    void deleteConfig(Integer[] ids);

    /**
     * 获取配置
     * @param id 配置id
     * @return 配置
     */
    ConfigDetailRes getConfig(Integer id);

    /**
     * 获取配置
     * @param configKey 配置名称
     * @return 配置
     */
    ConfigDetailRes getConfig(String configKey);

    /**
     * 获取配置列表
     * @return 配置列表
     */
    List<ConfigDetailRes> getAllConfig();
}
