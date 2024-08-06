package com.wbxnl.blog.trigger.http.front;

import com.wbxnl.blog.api.front.model.res.ConfigInfoRes;
import com.wbxnl.blog.api.front.service.IConfigService;
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
 * @since 2024/8/6 11:32
 */
@Slf4j
@RestController
@RequestMapping("/front/config")
@RequiredArgsConstructor
public class ConfigController implements IConfigService {

    private final ISystemConfigService systemConfigService;

    @Override
    public ConfigInfoRes getConfig(String configKey) {
        return null;
    }

    @Override
    public List<ConfigInfoRes> getConfigList() {
        return List.of();
    }
}
