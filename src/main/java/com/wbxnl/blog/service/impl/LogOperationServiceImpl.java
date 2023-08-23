package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.model.dto.LogOperationDto;
import com.wbxnl.blog.model.entity.LogError;
import com.wbxnl.blog.model.entity.LogOperation;
import com.wbxnl.blog.dao.LogOperationDao;
import com.wbxnl.blog.model.vo.params.ErrorLogParams;
import com.wbxnl.blog.model.vo.params.OperationLogParams;
import com.wbxnl.blog.model.vo.params.QueryParams;
import com.wbxnl.blog.service.LogOperationService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class LogOperationServiceImpl extends AbstractServiceImpl<LogOperationDao, LogOperation, LogOperationDto, LogOperation> implements LogOperationService {
    @Override
    public LambdaQueryWrapper<LogOperation> getQueryWrapper(QueryParams queryParams) {
        OperationLogParams logParams = (OperationLogParams) queryParams;
        LambdaQueryWrapper<LogOperation> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtils.isNotNull(logParams.getId())){
            queryWrapper.eq(LogOperation::getId,logParams.getId());
        }
        if(StringUtils.isNotBlank(logParams.getVersion())){
            queryWrapper.eq(LogOperation::getVersion,logParams.getVersion());
        }
        if(StringUtils.isNotBlank(logParams.getRequestUrl())){
            queryWrapper.like(LogOperation::getRequestUrl,logParams.getRequestUrl());
        }
        if(StringUtils.isNotBlank(logParams.getRequestMethod())){
            queryWrapper.eq(LogOperation::getRequestMethod,logParams.getRequestMethod());
        }
        if(StringUtils.isNotBlank(logParams.getRequestParam())){
            queryWrapper.like(LogOperation::getRequestParam,logParams.getRequestParam());
        }
        if(StringUtils.isNotBlank(logParams.getModule())){
            queryWrapper.eq(LogOperation::getModule,logParams.getModule());
        }
        if(StringUtils.isNotBlank(logParams.getCallingMethod())){
            queryWrapper.like(LogOperation::getCallingMethod,logParams.getCallingMethod());
        }
        if (StringUtils.isNotBlank(logParams.getType())){
            queryWrapper.like(LogOperation::getType,logParams.getType());
        }
        if(StringUtils.isNotBlank(logParams.getDescription())){
            queryWrapper.like(LogOperation::getDescription,logParams.getDescription());
        }
        if(StringUtils.isNotBlank(logParams.getDevice())){
            queryWrapper.like(LogOperation::getDevice,logParams.getDevice());
        }
        if(StringUtils.isNotBlank(logParams.getBrowser())){
            queryWrapper.like(LogOperation::getBrowser,logParams.getBrowser());
        }
        if(StringUtils.isNotBlank(logParams.getResponseData())){
            queryWrapper.like(LogOperation::getResponseData,logParams.getResponseData());
        }
        if(ObjectUtils.isNotNull(logParams.getMinElapsedTime(),logParams.getMaxElapsedTime())){
            queryWrapper.between(LogOperation::getElapsedTime,logParams.getMinElapsedTime(),logParams.getMaxElapsedTime());
        }
        if(ObjectUtils.isNotNull(logParams.getBeginDate(),logParams.getEndDate())){
            queryWrapper.between(LogOperation::getCreateTime,logParams.getBeginDate(),logParams.getEndDate());
        }
        queryWrapper.orderByDesc(LogOperation::getCreateTime);
        return queryWrapper;
    }
}
