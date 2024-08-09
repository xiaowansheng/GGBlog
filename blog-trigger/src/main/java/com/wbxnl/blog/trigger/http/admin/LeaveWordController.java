package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.LeaveWordQueryReq;
import com.wbxnl.blog.api.admin.model.res.LeaveWordDetailRes;
import com.wbxnl.blog.api.admin.service.ILeaveWordService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageQueryEntity;
import com.wbxnl.blog.domain.leaveMessage.service.ILeaveMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:07
 */
@Slf4j
@RestController
@RequestMapping("/admin/leaveWord")
@RequiredArgsConstructor
public class LeaveWordController implements ILeaveWordService {

    private final ILeaveMessageService leaveMessageService;

    @Override
    public PageData<LeaveWordDetailRes> getPageOfLeaveWords(PageParams pageParams, LeaveWordQueryReq leaveWordQueryReq) {
        LeaveMessageQueryEntity messageQueryEntity = ObjectConvertUtils.convert(leaveWordQueryReq, LeaveMessageQueryEntity.class);
        PageData<LeaveMessageEntity> pageOfLeaveMessages = leaveMessageService.getPageOfLeaveMessages(pageParams, messageQueryEntity);
        return PageData.<LeaveWordDetailRes>builder()
                .number(pageOfLeaveMessages.getNumber())
                .size(pageOfLeaveMessages.getSize())
                .total(pageOfLeaveMessages.getTotal())
                .data(ObjectConvertUtils.convertList(pageOfLeaveMessages.getData(), LeaveWordDetailRes.class))
                .build();
    }

    @Override
    public void updateLeaveWordShowStatus(Integer id, Integer status) {
        leaveMessageService.updateLeaveWordShowStatus(id, status);
    }

    @Override
    public void updateLeaveWordAuditStatus(Integer id, Integer auditStatus) {
        // TODO
    }

    @Override
    public void deleteLeaveWord(Integer id) {
        leaveMessageService.deleteLeaveMessage(id);
    }

    @Override
    public void deleteLeaveWord(Integer[] ids) {
        leaveMessageService.deleteLeaveMessage(ids);
    }
}
