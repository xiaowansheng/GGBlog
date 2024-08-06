package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigEntity;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigQueryEntity;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigUpdateEntity;
import com.wbxnl.blog.domain.config.model.vo.SystemConfigVo;
import com.wbxnl.blog.domain.config.repository.ISystemConfigRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.SystemConfigDao;
import com.wbxnl.blog.infrastructure.persistent.po.SystemConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/31 17:41
 */
@Service
@RequiredArgsConstructor
public class SystemConfigRepository implements ISystemConfigRepository {

    private final SystemConfigDao systemConfigDao;

    @Override
    public SystemConfigEntity addSystemConfig(SystemConfigVo systemConfigVo) {
        SystemConfig systemConfig = ObjectConvertUtils.convert(systemConfigVo, SystemConfig.class);
        int insert = systemConfigDao.insert(systemConfig);
        if (insert <= 0) {
            return null;
        }
        return ObjectConvertUtils.convert(systemConfig, SystemConfigEntity.class);
    }

    @Override
    public boolean updateSystemConfig(SystemConfigUpdateEntity systemConfigUpdateEntity) {
        SystemConfig systemConfig = ObjectConvertUtils.convert(systemConfigUpdateEntity, SystemConfig.class);
        return systemConfigDao.updateById(systemConfig) > 0;
    }

    @Override
    public boolean deleteSystemConfig(Integer id) {
        return systemConfigDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteSystemConfig(Integer[] ids) {
        return systemConfigDao.deleteByIds(List.of(ids)) > 0;
    }

    @Override
    public SystemConfigEntity getSystemConfig(Integer id) {
        SystemConfig systemConfig = systemConfigDao.selectById(id);
        return ObjectConvertUtils.convert(systemConfig, SystemConfigEntity.class);
    }

    @Override
    public SystemConfigEntity getSystemConfig(String configName) {
        LambdaQueryWrapper<SystemConfig> systemConfigLambdaQueryWrapper = new LambdaQueryWrapper<>();
        systemConfigLambdaQueryWrapper.eq(SystemConfig::getName, configName);
        SystemConfig systemConfig = systemConfigDao.selectOne(systemConfigLambdaQueryWrapper);
        return ObjectConvertUtils.convert(systemConfig, SystemConfigEntity.class);
    }

    @Override
    public List<SystemConfigEntity> getAllSystemConfig() {
        List<SystemConfig> systemConfigs = systemConfigDao.selectList(null);
        return ObjectConvertUtils.convertList(systemConfigs, SystemConfigEntity.class);
    }
}
