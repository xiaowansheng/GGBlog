package com.wbxnl.blog.service;

import com.alibaba.fastjson2.JSONObject;
import com.wbxnl.blog.model.dto.SystemConfigDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.SystemConfig;
import com.wbxnl.blog.model.vo.SystemConfigVo;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 网站配置 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface SystemConfigService extends BaseService<SystemConfig, SystemConfigDto, SystemConfigVo> {

    /**
     * 用户获取网站主页的所以配置信息
     * 同时网站访问量增加
     * @return
     */
    Map<String,Object> getConfigList();
    /**
     * 获取指定配置信息D的json对象,可能是json对象也可能是json数组
     * @param key redis查询key
     * @param defaultConfig 默认的配置参数
     * @return
     */

    Object getJsonObjectOfConfig(String key, SystemConfig defaultConfig);


    /**
     * 根据key获取配置信息
     * @param key
     * @param defaultConfig
     * @return
     */
    SystemConfig getSystemConfig(String key,SystemConfig defaultConfig);

    /**
     * 获取通知配置
     * @return
     */
    SystemConfigDto getNoticeConfig();
}

