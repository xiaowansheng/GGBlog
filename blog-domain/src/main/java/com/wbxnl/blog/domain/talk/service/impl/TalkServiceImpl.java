package com.wbxnl.blog.domain.talk.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.common.utils.HttpUtils;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.talk.model.aggregate.TalkAggregate;
import com.wbxnl.blog.domain.talk.model.entity.TalkEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkQueryEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkUpdateEntity;
import com.wbxnl.blog.domain.talk.model.vo.TalkInsertVo;
import com.wbxnl.blog.domain.talk.model.vo.TalkVo;
import com.wbxnl.blog.domain.talk.repository.ITalkRepository;
import com.wbxnl.blog.domain.talk.service.ITalkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 22:19
 */
@Service
@RequiredArgsConstructor
public class TalkServiceImpl implements ITalkService {

    private final ITalkRepository talkRepository;

    private final HttpServletRequest request;

    @Override
    public TalkEntity addTalk(TalkVo talkVo) {
        TalkInsertVo talkInsertVo = ObjectConvertUtils.convert(talkVo, TalkInsertVo.class);
        String ipAddress = HttpUtils.getIpAddress(request);
        talkInsertVo.setIpAddress(ipAddress);
        talkInsertVo.setIpSource(ipAddress);
        talkInsertVo.setDevice(HttpUtils.getRequestDevice(request));
        talkInsertVo.setBrowser(HttpUtils.getRequestBrowser(request));
        return talkRepository.addTalk(talkInsertVo);
    }

    @Override
    public void deleteTalk(Integer id) {
        boolean updated = talkRepository.deleteTalk(id);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteTalk(Integer[] ids) {
        boolean updated = talkRepository.deleteTalk(ids);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void updateTalk(TalkUpdateEntity talkUpdateEntity) {
        boolean updated = talkRepository.updateTalk(talkUpdateEntity);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateTalkStatus(Integer id, String status) {
        boolean updated = talkRepository.updateTalkStatus(id, status);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateTalkTop(Integer id, Integer top) {
        boolean updated = talkRepository.updateTalkTop(id, top);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public TalkEntity getTalk(Integer id) {
        return talkRepository.getTalk(id);
    }

    @Override
    public TalkAggregate getTalkDetail(Integer id, boolean isVisitor) {
        return talkRepository.getTalkDetail(id, isVisitor);
    }

    @Override
    public PageData<TalkAggregate> getPageTalkDetails(PageParams pageParams, TalkQueryEntity talkQueryEntity) {
        return talkRepository.getPageTalkDetails(pageParams, talkQueryEntity);
    }

    @Override
    public PageData<TalkAggregate> getPageTalkDetailsByUser(PageParams pageParams) {
        return talkRepository.getPageTalkDetailsByUser(pageParams);
    }
}
