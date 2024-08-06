package com.wbxnl.blog.domain.Log.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationExceptionLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogBaseInfoVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogDetailVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogQueryVo;
import com.wbxnl.blog.domain.Log.repository.IOperationExceptionLogRepository;
import com.wbxnl.blog.domain.Log.service.IOperationExceptionLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 0:26
 */
@Service
@RequiredArgsConstructor
public class OperationExceptionLogService implements IOperationExceptionLogService {

    private final IOperationExceptionLogRepository operationExceptionLogRepository;

    @Override
    public OperationExceptionLogEntity addOperationExceptionLog(OperationExceptionLogBaseInfoVo operationExceptionLogBaseInfoVo) {
        OperationExceptionLogDetailVo operationExceptionLogDetailVo = ObjectConvertUtils.convert(operationExceptionLogBaseInfoVo, OperationExceptionLogDetailVo.class);
        // TODO 添加详细请求信息
        return operationExceptionLogRepository.addOperationExceptionLog(operationExceptionLogDetailVo);
    }

    @Override
    public void deleteOperationExceptionLog(Integer id) {
        boolean updated = operationExceptionLogRepository.deleteOperationExceptionLog(id);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteOperationExceptionLog(Integer[] ids) {
        boolean updated = operationExceptionLogRepository.deleteOperationExceptionLog(ids);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public OperationExceptionLogEntity getOperationExceptionLog(Integer id) {
        return operationExceptionLogRepository.getOperationExceptionLog(id);
    }

    @Override
    public PageData<OperationExceptionLogEntity> getPageOperationExceptionLogs(PageParams pageParams, OperationExceptionLogQueryVo operationExceptionLogQuery) {
        return operationExceptionLogRepository.getPageOperationExceptionLogs(pageParams, operationExceptionLogQuery);
    }
}
