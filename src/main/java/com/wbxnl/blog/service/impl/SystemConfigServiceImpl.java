package com.wbxnl.blog.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONValidator;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.constant.keys.KeyOfStatistics;
import com.wbxnl.blog.constant.keys.WebsiteConfig;
import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.exception.BlogException;
import com.wbxnl.blog.model.config.*;
import com.wbxnl.blog.model.dto.SystemConfigDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.SystemConfig;
import com.wbxnl.blog.dao.SystemConfigDao;
import com.wbxnl.blog.model.vo.SystemConfigVo;
import com.wbxnl.blog.service.PageViewService;
import com.wbxnl.blog.service.SystemConfigService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.ConvertUtils;
import com.wbxnl.blog.utils.IPUtils;
import com.wbxnl.blog.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 网站配置 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
@Slf4j
public class SystemConfigServiceImpl extends AbstractServiceImpl<SystemConfigDao, SystemConfig, SystemConfigDto, SystemConfigVo> implements SystemConfigService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PageViewService pageViewService;

    /**
     * 访问量
     * 用作返回查询结果的name
     */
    private static final String PAGE_VIEW = "pageView";


    @Override
    public List<NameValueDto> getConfigList() {
        // 获取相关的配置信息
        SystemConfig cover = getSystemConfig(Cover.KEY, Cover.DEFAULT_CONFIG);
        SystemConfig menu = getSystemConfig(Menu.KEY, Menu.DEFAULT_CONFIG);
        SystemConfig author = getSystemConfig(Author.KEY, Author.DEFAULT_CONFIG);
        SystemConfig socialAccount = getSystemConfig(SocialAccount.KEY, SocialAccount.DEFAULT_CONFIG);
        SystemConfig website = getSystemConfig(Website.KEY, Website.DEFAULT_CONFIG);
        SystemConfig thirdPart = getSystemConfig(ThirdPartyLogin.KEY, ThirdPartyLogin.DEFAULT_CONFIG);
        SystemConfig component = getSystemConfig(Component.KEY, Component.DEFAULT_CONFIG);
        SystemConfig privacy = getSystemConfig(PrivacyAndSecurity.KEY, PrivacyAndSecurity.DEFAULT_CONFIG);
        SystemConfig avatar = getSystemConfig(Avatar.KEY, Avatar.DEFAULT_CONFIG);
        SystemConfig reward = getSystemConfig(Reward.KEY, Reward.DEFAULT_CONFIG);
        SystemConfig[] configs = {cover, menu, author, socialAccount, website, thirdPart, component, privacy, avatar, reward};
        List<NameValueDto> configList = Arrays.stream(configs)
                .map(config ->
                        new NameValueDto(config.getName(), getJsonObject(config.getValue()))
                )
                .collect(Collectors.toList());

        // 网站访问量增加
        Long pageView = pageViewService.increasePageView(ViewTypeEums.WEBSITE.getName(), null);
        // 添加统计
        addStatisticsOfIp();
        // 响应体中，添加网站访问量
        configList.add(new NameValueDto(PAGE_VIEW, pageView));
        return configList;
    }

    /**
     * 添加ip统计数据
     */
    @Async
    public void addStatisticsOfIp(){
        // 添加用户的位置信息到一个单独的redis集合，方便之后可视化看各个地区的访客数量
        // TODO 存在缓存丢失后，统计数据没了的情况，之后还要根据 visitor 表的访客数据来进行统计
        //          解决办法暂时可以是：visitor 定期持久化的时候，同时也再来一次统计ip数据
        String ipAddress = IPUtils.getIpAddress(request);
        redisUtils.addObjectOfSet(KeyOfStatistics.IP_ADDRESS,ipAddress);
    }

    @Override
    public Object getJsonObjectOfConfig(String key, SystemConfig defaultConfig) {
        SystemConfig systemConfig = getSystemConfig(key, defaultConfig);
        return getJsonObject(systemConfig.getValue());
    }

    /**
     * JSON字符串转为对应json对象类型
     *
     * @param jsonStr
     * @return
     */
    private Object getJsonObject(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        JSONValidator validator = JSONValidator.from(jsonStr);
        if (validator.validate()) {
            if (JSONValidator.Type.Object.equals(validator.getType())) {
                return JSON.parseObject(jsonStr);
            } else if (JSONValidator.Type.Array.equals(validator.getType())) {
                return JSON.parseArray(jsonStr);
            } else if (JSONValidator.Type.Value.equals(validator.getType())) {
                return jsonStr;
            }
        }
        throw new BlogException(OperationStateCode.JSON_FORMAT_ERROR);
    }

    @Override
    public SystemConfig getSystemConfig(String key, SystemConfig defaultConfig) {
        SystemConfig systemConfig = redisUtils.getCacheObject(key);
        // 查看redis是否有
        if (ObjectUtils.isNull(systemConfig)) {
            // redis没有则查看数据库有没有
            systemConfig = getSystemConfig(defaultConfig.getName());
            if (ObjectUtils.isNull(systemConfig)) {
                //数据库没有,则插入到数据库
                save(defaultConfig);
                systemConfig = defaultConfig;
            }
            // 存储配置到redis
            redisUtils.setCacheObject(key, systemConfig);
        }
        return systemConfig;
    }

    @Override
    public SystemConfigDto getNoticeConfig() {
        SystemConfig config = getSystemConfig(Notice.KEY, Notice.DEFAULT_CONFIG);
        return ConvertUtils.sourceToTarget(config,SystemConfigDto.class);
    }

    /**
     * 根据配置名称获取配置对象
     *
     * @param configName
     * @return
     */
    private SystemConfig getSystemConfig(String configName) {
        return lambdaQuery().eq(SystemConfig::getName, configName).one();
    }

    @Override
    public void update(SystemConfigVo vo) {
        SystemConfig systemConfig = ConvertUtils.sourceToTarget(vo, SystemConfig.class);
        updateById(systemConfig);
        String key=null;
        // 这里是因为 redis的key和实体的name不相同，name是key的最后拼接的名称，所以需要根据key来获取name
        // 为了省事可以把redis的 key 和实体中的 name 置为相同，这样通过name就可以直接修改和删除redis的缓存和数据库数据

        Field[] declaredFields = WebsiteConfig.class.getDeclaredFields();
        for (Field field : declaredFields) {
            try {
                String str=(String)field.get(null);
                String[] split = str.split(":");
                if(split[split.length-1].equals(systemConfig.getName())){
                    key=str;
                    break;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        // 遍历枚举类，找到被修改的配置的key，根据key，删除redis中的缓存
//        for (Config config : Config.values()) {
//            if(config.getName().equals(systemConfig.getName())){
//                key=config.getKey();
//                break;
//            }
//        }
        if(StringUtils.isNotBlank(key)){
            // 更新数据后，删除在redis中的缓存
            redisUtils.deleteObject(key);
        }
    }
}
