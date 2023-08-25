package com.wbxnl.blog.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.model.config.*;
import com.wbxnl.blog.model.dto.SystemConfigDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.SystemConfig;
import com.wbxnl.blog.model.vo.SystemConfigVo;
import com.wbxnl.blog.service.PageViewService;
import com.wbxnl.blog.utils.ConvertUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.SystemConfigService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站配置 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/website")
@Tag(name = "SystemConfigController",description = "网站配置模块")
public class SystemConfigController extends AbstractController<SystemConfigService, SystemConfig, SystemConfigDto, SystemConfigVo> {
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private PageViewService pageViewService;

    @GetMapping("/config/list")
    @Operation(summary = "用户获取所有配置信息")
    public Result<List<NameValueDto>> getConfigList(){
        List<NameValueDto> list=systemConfigService.getConfigList();
        return new Result().ok(list);
    }

//    @GetMapping("/covers")
//    @Operation(summary = "用户获取主页菜单封面图")
//    public Result getCoversByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(Cover.KEY, Cover.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }
    @GetMapping("/config/covers")
    @Operation(summary = "获取主页菜单封面图")
    public Result<SystemConfigDto> getCovers(){
        SystemConfig config = systemConfigService.getSystemConfig(Cover.KEY, Cover.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

//    @GetMapping("/menu")
//    @Operation(summary = "用户获取网站菜单显示信息")
//    public Result getMenuShowByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(Menu.KEY, Menu.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }

    @GetMapping("/config/menu")
    @Operation(summary = "获取网站菜单显示信息")
    public Result<SystemConfigDto> getMenuShow(){
        SystemConfig config = systemConfigService.getSystemConfig(Menu.KEY, Menu.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

//    @GetMapping("/author/info")
//    @Operation(summary = "用户获取作者信息")
//    public Result getAuthorInformationByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(Author.KEY, Author.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }

    @GetMapping("/config/author/info")
    @Operation(summary = "获取作者信息")
    public Result<SystemConfigDto> getAuthorInformation(){
        SystemConfig config = systemConfigService.getSystemConfig(Author.KEY, Author.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

//    @GetMapping("/author/contact")
//    @Operation(summary = "用户获取作者社交账号")
//    public Result getAuthorContactByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(SocialAccount.KEY, SocialAccount.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }

    @GetMapping("/config/author/contact")
    @Operation(summary = "获取作者社交账号")
    public Result<SystemConfigDto> getAuthorContact(){
        SystemConfig config = systemConfigService.getSystemConfig(SocialAccount.KEY, SocialAccount.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }
//    @GetMapping("/information")
//    @Operation(summary = "用户获取网站信息")
//    public Result getWebSitInformationByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(Website.KEY, Website.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }

    @GetMapping("/config/information")
    @Operation(summary = "获取网站信息")
    public Result<SystemConfigDto> getWebSitInformation(){
        SystemConfig config = systemConfigService.getSystemConfig(Website.KEY, Website.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

//    @GetMapping("/login/thirdparty")
//    @Operation(summary = "用户获取网站第三方登录信息")
//    public Result getThirdPartyLoginByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(ThirdPartyLogin.KEY, ThirdPartyLogin.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }

    @GetMapping("/config/login/thirdparty")
    @Operation(summary = "获取网站第三方登录信息")
    public Result<SystemConfigDto> getThirdPartyLogin(){
        SystemConfig config = systemConfigService.getSystemConfig(ThirdPartyLogin.KEY, ThirdPartyLogin.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

//    @GetMapping("/component")
//    @Operation(summary = "用户获取网站功能模块显示信息")
//    public Result getModuleShowByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(Component.KEY, Component.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }

    @GetMapping("/config/component")
    @Operation(summary = "获取网站功能模块显示信息")
    public Result<SystemConfigDto> getModuleShow(){
        SystemConfig config = systemConfigService.getSystemConfig(Component.KEY, Component.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

//    @GetMapping("/privacy")
//    @Operation(summary = "用户获取网站隐私设置信息")
//    public Result getPrivacySetByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(PrivacyAndSecurity.KEY, PrivacyAndSecurity.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }

    @GetMapping("/config/privacy")
    @Operation(summary = "获取网站隐私设置信息")
    public Result<SystemConfigDto> getPrivacySet(){
        SystemConfig config = systemConfigService.getSystemConfig(PrivacyAndSecurity.KEY, PrivacyAndSecurity.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

    @GetMapping("/about")
    @Operation(summary = "用户获取作者和网站介绍")
    public Result<SystemConfigDto> getAboutByUser(){
        Object config = systemConfigService.getJsonObjectOfConfig(About.KEY, About.DEFAULT_CONFIG);
        // 添加访客记录
        if(ObjectUtils.isNotNull(config)){
            pageViewService.increasePageView(ViewTypeEums.ABOUT.getName(), null);
        }
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

    @GetMapping("/config/about")
    @Operation(summary = "获取作者和网站介绍")
    public Result<SystemConfigDto> getAbout(){
        SystemConfig config = systemConfigService.getSystemConfig(About.KEY, About.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }


//    @GetMapping("/avatar")
//    @Operation(summary = "用户获取网站头像配置")
//    public Result getAvatarByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(Avatar.KEY, Avatar.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }
    @GetMapping("/config/avatar")
    @Operation(summary = "获取网站头像配置")
    public Result<SystemConfigDto> getAvatar(){
        SystemConfig config = systemConfigService.getSystemConfig(Avatar.KEY, Avatar.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

//    @GetMapping("/reward")
//    @Operation(summary = "用户获取网站打赏配置")
//    public Result getRewardByUser(){
//        Object config = systemConfigService.getJsonObjectOfConfig(Reward.KEY, Reward.DEFAULT_CONFIG);
//        return new Result().ok(config);
//    }

    @GetMapping("/config/reward")
    @Operation(summary = "获取网站打赏配置")
    public Result<SystemConfigDto> getReward(){
        SystemConfig config = systemConfigService.getSystemConfig(Reward.KEY, Reward.DEFAULT_CONFIG);
        return new Result().ok(ConvertUtils.sourceToTarget(config,SystemConfigDto.class));
    }

    @GetMapping("/config/notice")
    @Operation(summary = "获取网站通知设置配置")
    public Result<SystemConfigDto> getNotice(){
        SystemConfigDto systemConfigDto=systemConfigService.getNoticeConfig();
        return new Result().ok(systemConfigDto);
    }

}
