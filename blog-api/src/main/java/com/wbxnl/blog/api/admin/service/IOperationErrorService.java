package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.OperationErrorLogQueryReq;
import com.wbxnl.blog.api.admin.model.res.OperationErrorDetailRes;
import com.wbxnl.blog.api.admin.model.res.OperationErrorLogSimpleRes;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 15:56
 */
public interface IOperationErrorService {

    /**
     * 分页查询
     * @param pageParams 分页参数
     * @param operationErrorLogQueryReq 查询参数
     * @return 分页日志数据
     */
    PageData<OperationErrorLogSimpleRes> getPageOfOperationErrorLog(PageParams pageParams, OperationErrorLogQueryReq operationErrorLogQueryReq);

    /**
     * 获取日志详情
     * @param id 日志id
     * @return 日志详情
     */
    OperationErrorDetailRes getOperationErrorLogDetail(Integer id);

    /**
     * 删除日志
     * @param id 日志id
     */
    void deleteOperationErrorLog(Integer id);

    /**
     * 批量删除日志
     * @param ids 日志id
     */
    void deleteOperationErrorLog(Integer[] ids);
}
