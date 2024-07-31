package com.wbxnl.blog.domain.config.service.impl;

import com.wbxnl.blog.common.enums.ConfigEnum;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigEntity;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigUpdateEntity;
import com.wbxnl.blog.domain.config.model.vo.SystemConfigUseVo;
import com.wbxnl.blog.domain.config.model.vo.SystemConfigVo;
import com.wbxnl.blog.domain.config.repository.ISystemConfigRepository;
import com.wbxnl.blog.domain.config.service.ISystemConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 10:54
 */
@Service
@RequiredArgsConstructor
public class SystemConfigService implements ISystemConfigService {

    private final ISystemConfigRepository systemConfigRepository;

    @Override
    public SystemConfigEntity addSystemConfig(SystemConfigVo systemConfigVo) {
        return systemConfigRepository.addSystemConfig(systemConfigVo);
    }

    @Override
    public boolean updateSystemConfig(SystemConfigUpdateEntity systemConfigUpdateEntity) {
        return systemConfigRepository.updateSystemConfig(systemConfigUpdateEntity);
    }

    @Override
    public boolean deleteSystemConfig(Integer id) {
        return systemConfigRepository.deleteSystemConfig(id);
    }

    @Override
    public boolean deleteSystemConfig(Integer[] ids) {
        return systemConfigRepository.deleteSystemConfig(ids);
    }

    @Override
    public SystemConfigEntity getSystemConfig(Integer id) {
        return systemConfigRepository.getSystemConfig(id);
    }

    @Override
    public SystemConfigEntity getSystemConfig(String configName) {
        return systemConfigRepository.getSystemConfig(configName);
    }

    @Override
    public List<SystemConfigEntity> getAllSystemConfig() {
        return systemConfigRepository.getAllSystemConfig();
    }

    @Override
    public List<SystemConfigUseVo> getSystemConfigListByUser() {
        ArrayList<SystemConfigUseVo> list = new ArrayList<>();
        List<String> configNameList = Arrays
                .stream(ConfigEnum.getUserConfigList())
                .map(Enum::name)
                .toList();
        for (String name : configNameList) {
            SystemConfigEntity systemConfig = getSystemConfig(name);
            SystemConfigUseVo useVo = new SystemConfigUseVo()
                    .setName(systemConfig.getName())
                    .setConfigKey(systemConfig.getConfigKey())
                    .setValue(systemConfig.getValue());
            list.add(useVo);
        }
        return list;
    }
}
