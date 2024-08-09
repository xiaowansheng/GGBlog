package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.OperationLogQueryReq;
import com.wbxnl.blog.api.admin.model.res.OperationLogDetailRes;
import com.wbxnl.blog.api.admin.model.res.OperationLogSimpleRes;
import com.wbxnl.blog.api.admin.service.IOperationLogService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.Log.model.entity.OperationLogEntity;
import com.wbxnl.blog.domain.Log.model.vo.OperationLogQueryVo;
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
        OperationLogQueryVo operationLogQueryVo = ObjectConvertUtils.convert(operationLogQueryReq, OperationLogQueryVo.class);
        PageData<OperationLogEntity> pageOfOperationLogs = operationLogService.getPageOfOperationLogs(pageParams, operationLogQueryVo);
        return PageData.<OperationLogSimpleRes>builder()
                .data(ObjectConvertUtils.convertList(pageOfOperationLogs.getData(), OperationLogSimpleRes.class))
                .total(pageOfOperationLogs.getTotal())
                .number(pageOfOperationLogs.getNumber())
                .size(pageOfOperationLogs.getSize())
                .build();
    }

    @Override
    public OperationLogDetailRes getOperationLogDetail(Integer id) {
        OperationLogEntity operationLog = operationLogService.getOperationLog(id);
        return ObjectConvertUtils.convert(operationLog, OperationLogDetailRes.class);
    }

    @Override
    public void deleteOperationLog(Integer id) {
        operationLogService.deleteOperationLog(id);
    }

    @Override
    public void deleteOperationLog(Integer[] ids) {
        operationLogService.deleteOperationLog(ids);
    }
}
