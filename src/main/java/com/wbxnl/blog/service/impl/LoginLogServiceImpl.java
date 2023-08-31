package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.LoginLogDto;
import com.wbxnl.blog.model.entity.LoginLog;
import com.wbxnl.blog.dao.LoginLogDao;
import com.wbxnl.blog.model.vo.params.LoginLogParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.service.LoginLogService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.PageUtils;
import com.wbxnl.blog.utils.SecurityUtils;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 登录日志，记录用户的登录信息 服务实现类
 * </p>
 *
 * @author wansheng
 * @since 2023-08-07
 */
@Service
public class LoginLogServiceImpl extends AbstractServiceImpl<LoginLogDao, LoginLog, LoginLogDto, LoginLog> implements LoginLogService {


    @Override
    public PageData<LoginLogDto> getLoginLogByUser(PageParams pageParams) {
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        Page<LoginLog> loginLogPage = new Page<>(page, limit);
        LoginLogParams loginLogParams = new LoginLogParams()
                .setUserAuthId(SecurityUtils.getUserAuthId());
        LambdaQueryWrapper<LoginLog> queryWrapper = getQueryWrapper(loginLogParams);
        Page<LoginLog> selectPage = baseMapper.selectPage(loginLogPage, queryWrapper);
        return PageUtils.getPageData(selectPage, LoginLogDto.class);
    }

    @Override
    public PageData<LoginLogDto> getLoginLog(PageParams pageParams, Integer userAuthId) {
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        Page<LoginLog> loginLogPage = new Page<>(page, limit);
        LoginLogParams loginLogParams = new LoginLogParams()
                .setUserAuthId(userAuthId);
        LambdaQueryWrapper<LoginLog> queryWrapper = getQueryWrapper(loginLogParams);
        Page<LoginLog> selectPage = baseMapper.selectPage(loginLogPage, queryWrapper);
        return PageUtils.getPageData(selectPage, LoginLogDto.class);
    }

    @Override
    public LambdaQueryWrapper<LoginLog> getQueryWrapper(QueryParams queryParams) {
        LoginLogParams logParams = (LoginLogParams) queryParams;
        LambdaQueryWrapper<LoginLog> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtils.isNotNull(logParams.getId())){
            queryWrapper.eq(LoginLog::getId,logParams.getId());
        }
        if(ObjectUtils.isNotNull(logParams.getUserAuthId())){
            queryWrapper.eq(LoginLog::getUserAuthId,logParams.getUserAuthId());
        }
        if(StringUtils.isNotBlank(logParams.getDevice())){
            queryWrapper.like(LoginLog::getDevice,logParams.getDevice());
        }
        if(StringUtils.isNotBlank(logParams.getBrowser())){
            queryWrapper.like(LoginLog::getBrowser,logParams.getBrowser());
        }
        if(StringUtils.isNotBlank(logParams.getLocation())){
            queryWrapper.and(wrapper->{
                wrapper.like(LoginLog::getIpSource,logParams.getLocation())
                        .or()
                        .like(LoginLog::getLocation,logParams.getLocation());
            });
        }
        if(ObjectUtils.isNotNull(logParams.getBeginDate(),logParams.getEndDate())){
            queryWrapper.between(LoginLog::getCreateTime,logParams.getBeginDate(),logParams.getEndDate());
        }
        queryWrapper.orderByDesc(LoginLog::getCreateTime);
        return queryWrapper;
    }
}
