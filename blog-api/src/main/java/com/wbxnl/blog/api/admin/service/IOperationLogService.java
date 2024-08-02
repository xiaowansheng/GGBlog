package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.OperationLogQueryReq;
import com.wbxnl.blog.api.admin.model.res.OperationLogDetailRes;
import com.wbxnl.blog.api.admin.model.res.OperationLogSimpleRes;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 16:59
 */
public interface IOperationLogService {

    /**
     * 分页查询操作日志信息
     * @param pageParams 分页参数
     * @param operationLogQueryReq 查询参数
     * @return 操作日志分页数据
     */
    PageData<OperationLogSimpleRes> getPageOfOperationLog(PageParams pageParams, OperationLogQueryReq operationLogQueryReq);

    /**
     * 获取操作日志详情
     * @param id 操作日志id
     * @return 操作日志
     */
    OperationLogDetailRes getOperationLogDetail(Integer id);

    /**
     * 删除操作日志
     * @param id 操作日志id
     * @return Void
     */
    Void deleteOperationLog(Integer id);

    /**
     * 批量删除操作日志
     * @param ids 操作日志id
     * @return Void
     */
    Void deleteOperationLog(Integer[] ids);
}
