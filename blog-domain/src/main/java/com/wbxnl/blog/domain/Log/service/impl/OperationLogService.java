package com.wbxnl.blog.domain.Log.service.impl;

import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogBaseInfoVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogDetailVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogQueryVo;
import com.wbxnl.blog.domain.Log.repository.IOperationLogRepository;
import com.wbxnl.blog.domain.Log.service.IOperationLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 23:41
 */
@Service
@RequiredArgsConstructor
public class OperationLogService implements IOperationLogService {

    private final IOperationLogRepository operationLogRepository;

    private final HttpServletRequest httpServletRequest;

    @Override
    public OperationLogEntity addOperationLog(OperationLogBaseInfoVo operationLogBaseInfoVo) {
        OperationLogDetailVo operationLogDetailVo = ObjectConvertUtils.convert(operationLogBaseInfoVo, OperationLogDetailVo.class);
        // TODO 添加额外日志信息
        return operationLogRepository.addOperationLog(operationLogDetailVo);
    }

    @Override
    public boolean deleteOperationLog(Integer id) {
        return operationLogRepository.deleteOperationLog(id);
    }

    @Override
    public boolean deleteOperationLog(Integer[] ids) {
        return operationLogRepository.deleteOperationLog(ids);
    }

    @Override
    public OperationLogEntity getOperationLog(Integer id) {
        return operationLogRepository.getOperationLog(id);
    }

    @Override
    public PageData<OperationLogEntity> getPageOperationLogs(PageParams pageParams, OperationLogQueryVo operationLogQuery) {
        return operationLogRepository.getPageOperationLogs(pageParams, operationLogQuery);
    }
}
