package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.LeaveWordQueryReq;
import com.wbxnl.blog.api.admin.model.res.LeaveWordDetailRes;
import com.wbxnl.blog.api.admin.service.ILeaveWordService;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.leaveMessage.service.ILeaveMessage;
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

    private final ILeaveMessage leaveMessage;

    @Override
    public PageData<LeaveWordDetailRes> getPageOfLeaveWords(PageParams pageParams, LeaveWordQueryReq leaveWordQueryReq) {
        return null;
    }

    @Override
    public void updateLeaveWordShowStatus(Integer id, Integer status) {

    }

    @Override
    public void updateLeaveWordAuditStatus(Integer id, Integer auditStatus) {

    }

    @Override
    public void deleteLeaveWord(Integer id) {

    }

    @Override
    public void deleteLeaveWord(Integer[] ids) {

    }
}
