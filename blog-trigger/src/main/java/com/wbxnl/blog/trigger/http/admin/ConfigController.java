package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.ConfigDataReq;
import com.wbxnl.blog.api.admin.model.res.ConfigDetailRes;
import com.wbxnl.blog.api.admin.service.IConfigService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigEntity;
import com.wbxnl.blog.domain.config.model.entity.SystemConfigUpdateEntity;
import com.wbxnl.blog.domain.config.model.vo.SystemConfigVo;
import com.wbxnl.blog.domain.config.service.ISystemConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:04
 */
@Slf4j
@RestController
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class ConfigController implements IConfigService {

    private final ISystemConfigService systemConfigService;

    @Override
    public KeyData addConfig(ConfigDataReq configDataReq) {
        SystemConfigVo systemConfigVo = ObjectConvertUtils.convert(configDataReq, SystemConfigVo.class);
        SystemConfigEntity systemConfigEntity = systemConfigService.addSystemConfig(systemConfigVo);
        return KeyData.builder()
                .id(systemConfigEntity.getId())
                .key(systemConfigEntity.getConfigKey())
                .build();
    }

    @Override
    public void updateConfig(ConfigDataReq configDataReq) {
        SystemConfigUpdateEntity configUpdateEntity = ObjectConvertUtils.convert(configDataReq, SystemConfigUpdateEntity.class);
        systemConfigService.updateSystemConfig(configUpdateEntity);
    }

    @Override
    public void deleteConfig(Integer id) {
        systemConfigService.deleteSystemConfig(id);
    }

    @Override
    public void deleteConfig(Integer[] ids) {
        systemConfigService.deleteSystemConfig(ids);
    }

    @Override
    public ConfigDetailRes getConfig(Integer id) {
        SystemConfigEntity systemConfigEntity = systemConfigService.getSystemConfig(id);
        return ObjectConvertUtils.convert(systemConfigEntity, ConfigDetailRes.class);
    }

    @Override
    public ConfigDetailRes getConfig(String configKey) {
        SystemConfigEntity systemConfigEntity = systemConfigService.getSystemConfig(configKey);
        return ObjectConvertUtils.convert(systemConfigEntity, ConfigDetailRes.class);
    }

    @Override
    public List<ConfigDetailRes> getAllConfig() {
        List<SystemConfigEntity> allSystemConfig = systemConfigService.getAllSystemConfig();
        return ObjectConvertUtils.convertList(allSystemConfig, ConfigDetailRes.class);
    }
}
