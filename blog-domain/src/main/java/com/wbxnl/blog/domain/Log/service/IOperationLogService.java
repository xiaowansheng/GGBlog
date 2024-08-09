package com.wbxnl.blog.domain.Log.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogBaseInfoVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogQueryVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 22:39
 */
public interface IOperationLogService {
    /**
     * 添加操作日志
     * @param operationLogBaseInfoVo 操作日志信息
     * @return 操作日志
     */
    OperationLogEntity addOperationLog(OperationLogBaseInfoVo operationLogBaseInfoVo);

    /**
     * 删除操作日志
     * @param id 操作日志id
     */
    void deleteOperationLog(Integer id);

    /**
     * 批量删除操作日志
     * @param ids 操作日志id
     */
    void deleteOperationLog(Integer[] ids);

    /**
     * 获取操作日志
     * @param id 操作日志id
     * @return 操作日志
     */
    OperationLogEntity getOperationLog(Integer id);

    /**
     * 分页获取操作日志
     * @param pageParams 分页参数
     * @param operationLogQuery 查询条件
     * @return 操作日志
     */
    PageData<OperationLogEntity> getPageOfOperationLogs(PageParams pageParams, OperationLogQueryVo operationLogQuery);

}
