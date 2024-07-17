package com.wbxnl.blog.domain.Log.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationExceptionLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogDetailVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogQueryVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 23:43
 */
public interface IOperationExceptionLogRepository {

    OperationExceptionLogEntity addOperationExceptionLog(OperationExceptionLogDetailVo operationExceptionLogDetailVo);

    boolean deleteOperationExceptionLog(Integer id);

    boolean deleteOperationExceptionLog(Integer[] ids);

    OperationExceptionLogEntity getOperationExceptionLog(Integer id);

    PageData<OperationExceptionLogEntity> getPageOperationExceptionLogs(PageParams pageParams, OperationExceptionLogQueryVo operationExceptionLogQuery);
}
