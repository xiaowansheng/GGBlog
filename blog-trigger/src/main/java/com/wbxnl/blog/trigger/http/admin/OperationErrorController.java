package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.OperationErrorLogQueryReq;
import com.wbxnl.blog.api.admin.model.res.OperationErrorDetailRes;
import com.wbxnl.blog.api.admin.model.res.OperationErrorLogSimpleRes;
import com.wbxnl.blog.api.admin.service.IOperationErrorService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationExceptionLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationExceptionLogQueryVo;
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
        OperationExceptionLogQueryVo exceptionLogQueryVo = ObjectConvertUtils.convert(operationErrorLogQueryReq, OperationExceptionLogQueryVo.class);
        PageData<OperationExceptionLogEntity> pageOfOperationExceptionLogs = operationExceptionLogService.getPageOfOperationExceptionLogs(pageParams, exceptionLogQueryVo);
        return PageData.<OperationErrorLogSimpleRes>builder()
                .data(ObjectConvertUtils.convertList(pageOfOperationExceptionLogs.getData(), OperationErrorLogSimpleRes.class))
                .total(pageOfOperationExceptionLogs.getTotal())
                .number(pageOfOperationExceptionLogs.getNumber())
                .size(pageOfOperationExceptionLogs.getSize())
                .build();
    }

    @Override
    public OperationErrorDetailRes getOperationErrorLogDetail(Integer id) {
        OperationExceptionLogEntity operationExceptionLog = operationExceptionLogService.getOperationExceptionLog(id);
        return ObjectConvertUtils.convert(operationExceptionLog, OperationErrorDetailRes.class);
    }

    @Override
    public void deleteOperationErrorLog(Integer id) {
        operationExceptionLogService.deleteOperationExceptionLog(id);
    }

    @Override
    public void deleteOperationErrorLog(Integer[] ids) {
        operationExceptionLogService.deleteOperationExceptionLog(ids);
    }
}
