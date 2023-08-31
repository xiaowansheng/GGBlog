package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.model.dto.LogErrorDto;
import com.wbxnl.blog.model.entity.LogError;
import com.wbxnl.blog.dao.LogErrorDao;
import com.wbxnl.blog.model.vo.params.ErrorLogParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.service.LogErrorService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统异常错误日志 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class LogErrorServiceImpl extends AbstractServiceImpl<LogErrorDao, LogError, LogErrorDto, LogError> implements LogErrorService {

    @Override
    public LambdaQueryWrapper<LogError> getQueryWrapper(QueryParams queryParams) {
        ErrorLogParams logParams = (ErrorLogParams) queryParams;
        LambdaQueryWrapper<LogError> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtils.isNotNull(logParams.getId())){
            queryWrapper.eq(LogError::getId,logParams.getId());
        }
        if(StringUtils.isNotBlank(logParams.getVersion())){
            queryWrapper.eq(LogError::getVersion,logParams.getVersion());
        }
        if(StringUtils.isNotBlank(logParams.getRequestUrl())){
            queryWrapper.like(LogError::getRequestUrl,logParams.getRequestUrl());
        }
        if(StringUtils.isNotBlank(logParams.getRequestMethod())){
            queryWrapper.eq(LogError::getRequestMethod,logParams.getRequestMethod());
        }
        if(StringUtils.isNotBlank(logParams.getRequestParam())){
            queryWrapper.like(LogError::getRequestParam,logParams.getRequestParam());
        }
        if(StringUtils.isNotBlank(logParams.getModule())){
            queryWrapper.eq(LogError::getModule,logParams.getModule());
        }
        if(StringUtils.isNotBlank(logParams.getCallingMethod())){
            queryWrapper.like(LogError::getCallingMethod,logParams.getCallingMethod());
        }
        if (StringUtils.isNotBlank(logParams.getErrorName())){
            queryWrapper.like(LogError::getErrorName,logParams.getErrorName());
        }
        if(StringUtils.isNotBlank(logParams.getErrorMessage())){
            queryWrapper.like(LogError::getErrorMessage,logParams.getErrorMessage());
        }
        if(StringUtils.isNotBlank(logParams.getDevice())){
            queryWrapper.like(LogError::getDevice,logParams.getDevice());
        }
        if(StringUtils.isNotBlank(logParams.getBrowser())){
            queryWrapper.like(LogError::getBrowser,logParams.getBrowser());
        }
        if(ObjectUtils.isNotNull(logParams.getBeginDate(),logParams.getEndDate())){
            queryWrapper.between(LogError::getCreateTime,logParams.getBeginDate(),logParams.getEndDate());
        }
        queryWrapper.orderByDesc(LogError::getCreateTime);
        return queryWrapper;
    }
}
