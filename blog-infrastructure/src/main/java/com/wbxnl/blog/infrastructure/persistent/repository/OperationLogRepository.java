package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogDetailVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogQueryVo;
import com.wbxnl.blog.domain.Log.repository.IOperationLogRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.LogOperationDao;
import com.wbxnl.blog.infrastructure.persistent.po.LogOperation;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/30 17:11
 */
@Service
@RequiredArgsConstructor
public class OperationLogRepository implements IOperationLogRepository {

    private final LogOperationDao logOperationDao;

    @Override
    public OperationLogEntity addOperationLog(OperationLogDetailVo operationLogDetailVo) {
        LogOperation logOperation = ObjectConvertUtils.convert(operationLogDetailVo, LogOperation.class);
        logOperationDao.insert(logOperation);
        return ObjectConvertUtils.convert(logOperation, OperationLogEntity.class);
    }

    @Override
    public boolean deleteOperationLog(Integer id) {
        return logOperationDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteOperationLog(Integer[] ids) {
        return logOperationDao.deleteByIds(List.of(ids)) > 0;
    }

    @Override
    public OperationLogEntity getOperationLog(Integer id) {
        LogOperation logOperation = logOperationDao.selectById(id);
        return ObjectConvertUtils.convert(logOperation, OperationLogEntity.class);
    }

    @Override
    public PageData<OperationLogEntity> getPageOperationLogs(PageParams pageParams, OperationLogQueryVo operationLogQuery) {
        Page<LogOperation> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<LogOperation> logOperationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        logOperationLambdaQueryWrapper
                .eq(operationLogQuery.getId() != null, LogOperation::getId, operationLogQuery.getId())
                .like(StringUtils.isNotBlank(operationLogQuery.getUsername()), LogOperation::getUsername, operationLogQuery.getUsername())
                .like(StringUtils.isNotBlank(operationLogQuery.getModule()), LogOperation::getModule, operationLogQuery.getModule())
                .like(StringUtils.isNotBlank(operationLogQuery.getDescription()), LogOperation::getDescription, operationLogQuery.getDescription())
                .like(StringUtils.isNotBlank(operationLogQuery.getType()), LogOperation::getType, operationLogQuery.getType())
                .like(StringUtils.isNotBlank(operationLogQuery.getRequestUrl()), LogOperation::getRequestUrl, operationLogQuery.getRequestUrl())
                .like(StringUtils.isNotBlank(operationLogQuery.getRequestMethod()), LogOperation::getRequestMethod, operationLogQuery.getRequestMethod())
                .like(StringUtils.isNotBlank(operationLogQuery.getIpAddress()), LogOperation::getIpAddress, operationLogQuery.getIpAddress())
                .like(StringUtils.isNotBlank(operationLogQuery.getIpSource()), LogOperation::getIpSource, operationLogQuery.getIpSource())
                .like(StringUtils.isNotBlank(operationLogQuery.getBrowser()), LogOperation::getBrowser, operationLogQuery.getBrowser())
                .like(StringUtils.isNotBlank(operationLogQuery.getDevice()), LogOperation::getDevice, operationLogQuery.getDevice())
                .like(StringUtils.isNotBlank(operationLogQuery.getCallingMethod()), LogOperation::getCallingMethod, operationLogQuery.getCallingMethod())
                .like(StringUtils.isNotBlank(operationLogQuery.getRequestParam()), LogOperation::getRequestParam, operationLogQuery.getRequestParam())
                .like(StringUtils.isNotBlank(operationLogQuery.getResponseData()), LogOperation::getResponseData, operationLogQuery.getResponseData())
                .like(StringUtils.isNotBlank(operationLogQuery.getVersion()), LogOperation::getVersion, operationLogQuery.getVersion())
                .between(operationLogQuery.getBeginCreateTime() != null && operationLogQuery.getEndCreateTime() != null, LogOperation::getCreateTime, operationLogQuery.getBeginCreateTime(), operationLogQuery.getEndCreateTime())
                .orderByDesc(LogOperation::getCreateTime);
        Page<LogOperation> logOperationPage = logOperationDao.selectPage(page, logOperationLambdaQueryWrapper);
        List<OperationLogEntity> operationLogEntities = ObjectConvertUtils.convertList(logOperationPage.getRecords(), OperationLogEntity.class);
        return PageUtils.convertPageData(pageParams.getNumber(),pageParams.getSize(),logOperationPage.getTotal(),operationLogEntities);
    }
}
