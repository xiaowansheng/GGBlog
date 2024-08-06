package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationExceptionLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogDetailVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogQueryVo;
import com.wbxnl.blog.domain.Log.repository.IOperationExceptionLogRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.LogErrorDao;
import com.wbxnl.blog.infrastructure.persistent.po.LogError;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/31 15:48
 */
@Service
@RequiredArgsConstructor
public class OperationExceptionLogRepository implements IOperationExceptionLogRepository {

    private final LogErrorDao logErrorDao;

    @Override
    public OperationExceptionLogEntity addOperationExceptionLog(OperationExceptionLogDetailVo operationExceptionLogDetailVo) {
        LogError logError = ObjectConvertUtils.convert(operationExceptionLogDetailVo, LogError.class);
        int insert = logErrorDao.insert(logError);
        if (insert <= 0) {
            return null;
        }
        return ObjectConvertUtils.convert(logError, OperationExceptionLogEntity.class);
    }

    @Override
    public boolean deleteOperationExceptionLog(Integer id) {
        return logErrorDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteOperationExceptionLog(Integer[] ids) {
        return logErrorDao.deleteByIds(List.of(ids)) > 0;
    }

    @Override
    public OperationExceptionLogEntity getOperationExceptionLog(Integer id) {
        LogError logError = logErrorDao.selectById(id);
        return ObjectConvertUtils.convert(logError, OperationExceptionLogEntity.class);
    }

    @Override
    public PageData<OperationExceptionLogEntity> getPageOperationExceptionLogs(PageParams pageParams, OperationExceptionLogQueryVo operationExceptionLogQuery) {
        Page<LogError> page = new Page<>();
        LambdaQueryWrapper<LogError> logErrorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        logErrorLambdaQueryWrapper
                .eq(ObjectUtils.isNotEmpty(operationExceptionLogQuery.getId()), LogError::getId, operationExceptionLogQuery.getId())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getUsername()), LogError::getUsername, operationExceptionLogQuery.getUsername())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getModule()), LogError::getModule, operationExceptionLogQuery.getModule())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getRequestUrl()), LogError::getRequestUrl, operationExceptionLogQuery.getRequestUrl())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getRequestMethod()), LogError::getRequestMethod, operationExceptionLogQuery.getRequestMethod())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getRequestParam()), LogError::getRequestParam, operationExceptionLogQuery.getRequestParam())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getErrorName()), LogError::getErrorName, operationExceptionLogQuery.getErrorName())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getErrorMessage()), LogError::getErrorMessage, operationExceptionLogQuery.getErrorMessage())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getIpAddress()), LogError::getIpAddress, operationExceptionLogQuery.getIpAddress())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getIpSource()), LogError::getIpSource, operationExceptionLogQuery.getIpSource())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getDevice()), LogError::getDevice, operationExceptionLogQuery.getDevice())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getBrowser()), LogError::getBrowser, operationExceptionLogQuery.getBrowser())
                .like(StringUtils.isNotBlank(operationExceptionLogQuery.getCallingMethod()), LogError::getCallingMethod, operationExceptionLogQuery.getCallingMethod())
                .between(ObjectUtils.isNotEmpty(operationExceptionLogQuery.getBeginCreateTime())&&ObjectUtils.isNotEmpty(operationExceptionLogQuery.getEndCreateTime()), LogError::getCreateTime, operationExceptionLogQuery.getBeginCreateTime(), operationExceptionLogQuery.getEndCreateTime())
                .orderByDesc(LogError::getCreateTime);
        page = logErrorDao.selectPage(page, logErrorLambdaQueryWrapper);
        List<OperationExceptionLogEntity> operationExceptionLogEntities = ObjectConvertUtils.convertList(page.getRecords(), OperationExceptionLogEntity.class);
        return PageUtils.convertPageData(pageParams.getNumber(),pageParams.getSize(), page.getTotal(), operationExceptionLogEntities);
    }
}
