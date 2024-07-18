package com.wbxnl.blog.domain.config.repository;

import com.wbxnl.blog.domain.config.model.entity.SystemConfigEntity;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigQueryEntity;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigUpdateEntity;
import com.wbxnl.blog.domain.config.model.vo.SystemConfigVo;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 10:54
 */
public interface ISystemConfigRepository {
    SystemConfigEntity addSystemConfig(SystemConfigVo systemConfigVo);

    boolean updateSystemConfig(SystemConfigUpdateEntity systemConfigUpdateEntity);

    boolean deleteSystemConfig(Integer id);

    boolean deleteSystemConfig(Integer[] ids);

    SystemConfigEntity getSystemConfig(Integer id);

    SystemConfigEntity getSystemConfig(String configName);

    List<SystemConfigEntity> getSystemConfigList(SystemConfigQueryEntity systemConfigQueryEntity);

}
