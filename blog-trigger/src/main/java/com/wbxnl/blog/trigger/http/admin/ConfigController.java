package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.ConfigDataReq;
import com.wbxnl.blog.api.admin.model.res.ConfigDetailRes;
import com.wbxnl.blog.api.admin.service.IConfigService;
import com.wbxnl.blog.common.vo.KeyData;
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
        return null;
    }

    @Override
    public void updateConfig(ConfigDataReq configDataReq) {

    }

    @Override
    public void deleteConfig(Integer id) {

    }

    @Override
    public void deleteConfig(Integer[] ids) {

    }

    @Override
    public ConfigDetailRes getConfig(Integer id) {
        return null;
    }

    @Override
    public ConfigDetailRes getConfig(String configKey) {
        return null;
    }

    @Override
    public List<KeyData> getAllConfig() {
        return List.of();
    }
}
