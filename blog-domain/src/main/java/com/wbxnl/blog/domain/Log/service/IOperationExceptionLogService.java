package com.wbxnl.blog.domain.Log.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationExceptionLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogBaseInfoVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogQueryVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 23:06
 */
public interface IOperationExceptionLogService {
    /**
     * 添加异常日志
     * @param operationExceptionLogBaseInfoVo 基本操作信息
     * @return 异常日志
     */
    OperationExceptionLogEntity addOperationExceptionLog(OperationExceptionLogBaseInfoVo operationExceptionLogBaseInfoVo);

    /**
     * 删除异常日志
     * @param id 异常日志id
     */
    void deleteOperationExceptionLog(Integer id);

    /**
     * 批量删除异常日志
     * @param ids 异常日志id
     */
    void deleteOperationExceptionLog(Integer[] ids);

    /**
     * 获取异常日志
     * @param id 异常日志
     * @return 异常日志
     */
    OperationExceptionLogEntity getOperationExceptionLog(Integer id);

    /**
     * 分页获取异常日志
     * @param pageParams 分页参数
     * @param operationExceptionLogQuery 查询条件
     * @return 异常日志
     */
    PageData<OperationExceptionLogEntity> getPageOperationExceptionLogs(PageParams pageParams, OperationExceptionLogQueryVo operationExceptionLogQuery );
}
