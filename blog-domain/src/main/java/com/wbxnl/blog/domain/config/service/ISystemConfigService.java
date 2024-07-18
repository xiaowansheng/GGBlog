package com.wbxnl.blog.domain.config.service;

import com.wbxnl.blog.domain.config.model.entity.SystemConfigEntity;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigQueryEntity;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigUpdateEntity;
import com.wbxnl.blog.domain.config.model.vo.SystemConfigUseVo;
import com.wbxnl.blog.domain.config.model.vo.SystemConfigVo;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 10:18
 */
public interface ISystemConfigService {
    /**
     * 添加系统配置
     * @param systemConfigVo 系统配置
     * @return 系统配置结果
     */
    SystemConfigEntity addSystemConfig(SystemConfigVo systemConfigVo);

    /**
     * 修改系统配置
     * @param systemConfigUpdateEntity 系统配置
     * @return 系统配置结果
     */
    boolean updateSystemConfig(SystemConfigUpdateEntity systemConfigUpdateEntity);

    /**
     * 删除系统配置
     * @param id 系统配置id
     * @return 系统配置结果
     */
    boolean deleteSystemConfig(Integer id);

    /**
     * 批量删除系统配置
     * @param ids 系统配置id
     * @return 系统配置结果
     */
    boolean deleteSystemConfig(Integer[] ids);

    /**
     * 获取系统配置
     * @param id 系统配置id
     * @return 系统配置
     */
    SystemConfigEntity getSystemConfig(Integer id);

    /**
     * 获取系统配置
     * @param configName 系统配置名称
     * @return 系统配置
     */
    SystemConfigEntity getSystemConfig(String configName);

    /**
     * 获取系统配置列表
     * @param systemConfigQueryEntity 系统配置查询条件
     * @return 系统配置
     */
    List<SystemConfigEntity> getSystemConfigList(SystemConfigQueryEntity systemConfigQueryEntity);

    /**
     * 用户获取系统配置列表
     * @return 系统配置
     */
    List<SystemConfigUseVo> getSystemConfigListByUser();

}
