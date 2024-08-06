package com.wbxnl.blog.domain.leaveMessage.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.common.utils.HttpUtils;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageQueryEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageSimpleEntity;
import com.wbxnl.blog.domain.leaveMessage.model.vo.LeaveMessageInsertVo;
import com.wbxnl.blog.domain.leaveMessage.model.vo.LeaveMessageVo;
import com.wbxnl.blog.domain.leaveMessage.repository.ILeaveMessageRepository;
import com.wbxnl.blog.domain.leaveMessage.service.ILeaveMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 0:13
 */
@Service
@RequiredArgsConstructor
public class LeaveMessageImpl implements ILeaveMessage {

    private final ILeaveMessageRepository leaveMessageRepository;

    private final HttpServletRequest request;

    @Override
    public LeaveMessageEntity addLeaveMessage(LeaveMessageVo leaveMessageVo) {
        LeaveMessageInsertVo messageInsertVo = ObjectConvertUtils.convert(leaveMessageVo, LeaveMessageInsertVo.class);
        String ipAddress = HttpUtils.getIpAddress(request);
        messageInsertVo.setIpAddress(ipAddress);
        messageInsertVo.setIpSource(HttpUtils.getIpSource(ipAddress));
        messageInsertVo.setBrowser(HttpUtils.getRequestBrowser(request));
        messageInsertVo.setDevice(HttpUtils.getRequestDevice(request));
        return leaveMessageRepository.addLeaveMessage(messageInsertVo);
    }

    @Override
    public void deleteLeaveMessage(Integer id) {
        boolean updated = leaveMessageRepository.deleteLeaveMessage(id);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteLeaveMessage(Integer[] ids) {
        boolean updated = leaveMessageRepository.deleteLeaveMessage(ids);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void auditLeaveMessage(Integer id, Integer review) {
        boolean updated = leaveMessageRepository.auditLeaveMessage(id, review);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateLeaveMessageHidden(Integer id, Integer hidden) {
        boolean updated = leaveMessageRepository.updateLeaveMessageHidden(id, hidden);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public PageData<LeaveMessageEntity> getPageLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity) {
        return leaveMessageRepository.getPageLeaveMessages(pageParams, leaveMessageQueryEntity);
    }

    @Override
    public PageData<LeaveMessageEntity> getPageOfNoAuditLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity) {
        return leaveMessageRepository.getPageOfNoAuditLeaveMessages(pageParams, leaveMessageQueryEntity);
    }

    @Override
    public PageData<LeaveMessageSimpleEntity> getPageLeaveMessagesByUser(PageParams pageParams) {
        return leaveMessageRepository.getPageLeaveMessagesByUser(pageParams);
    }
}
