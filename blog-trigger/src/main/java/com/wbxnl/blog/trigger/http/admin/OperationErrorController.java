package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.OperationErrorLogQueryReq;
import com.wbxnl.blog.api.admin.model.res.OperationErrorDetailRes;
import com.wbxnl.blog.api.admin.model.res.OperationErrorLogSimpleRes;
import com.wbxnl.blog.api.admin.service.IOperationErrorService;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.service.IOperationExceptionLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:12
 */
@Slf4j
@RestController
@RequestMapping("/admin/log/operation/exception")
@RequiredArgsConstructor
public class OperationErrorController implements IOperationErrorService {

    private final IOperationExceptionLogService operationExceptionLogService;

    @Override
    public PageData<OperationErrorLogSimpleRes> getPageOfOperationErrorLog(PageParams pageParams, OperationErrorLogQueryReq operationErrorLogQueryReq) {
        return null;
    }

    @Override
    public OperationErrorDetailRes getOperationErrorLogDetail(Integer id) {
        return null;
    }

    @Override
    public void deleteOperationErrorLog(Integer id) {

    }

    @Override
    public void deleteOperationErrorLog(Integer[] ids) {

    }
}
