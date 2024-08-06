package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.OperationLogQueryReq;
import com.wbxnl.blog.api.admin.model.res.OperationLogDetailRes;
import com.wbxnl.blog.api.admin.model.res.OperationLogSimpleRes;
import com.wbxnl.blog.api.admin.service.IOperationLogService;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:14
 */
@Slf4j
@RestController
@RequestMapping("/admin/log/operation")
@RequiredArgsConstructor
public class OperationLogController implements IOperationLogService {

    private final com.wbxnl.blog.domain.Log.service.IOperationLogService operationLogService;

    @Override
    public PageData<OperationLogSimpleRes> getPageOfOperationLog(PageParams pageParams, OperationLogQueryReq operationLogQueryReq) {
        return null;
    }

    @Override
    public OperationLogDetailRes getOperationLogDetail(Integer id) {
        return null;
    }

    @Override
    public void deleteOperationLog(Integer id) {

    }

    @Override
    public void deleteOperationLog(Integer[] ids) {

    }
}
