package com.wbxnl.blog.domain.Log.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogDetailVo;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogQueryVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 23:43
 */
public interface IOperationLogRepository {


    OperationLogEntity addOperationLog(OperationLogDetailVo operationLogDetailVo);

    boolean deleteOperationLog(Integer id);

    boolean deleteOperationLog(Integer[] ids);

    OperationLogEntity getOperationLog(Integer id);

    PageData<OperationLogEntity> getPageOperationLogs(PageParams pageParams, OperationLogQueryVo operationLogQuery);
}
