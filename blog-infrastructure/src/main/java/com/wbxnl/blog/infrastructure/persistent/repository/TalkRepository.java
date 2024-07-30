package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.talk.model.aggregate.TalkAggregate;
import com.wbxnl.blog.domain.talk.model.entity.TalkEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkQueryEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkUpdateEntity;
import com.wbxnl.blog.domain.talk.model.vo.TalkInsertVo;
import com.wbxnl.blog.domain.talk.repository.ITalkRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.TalkDao;
import com.wbxnl.blog.infrastructure.persistent.po.Talk;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/30 10:47
 */
@Service
@RequiredArgsConstructor
public class TalkRepository implements ITalkRepository {

    private final TalkDao talkDao;

    @Override
    public TalkEntity addTalk(TalkInsertVo talkInsertVo) {
        Talk talk = ObjectConvertUtils.convert(talkInsertVo, Talk.class);
        talkDao.insert(talk);
        return ObjectConvertUtils.convert(talk, TalkEntity.class);
    }

    @Override
    public boolean deleteTalk(Integer id) {
        return talkDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteTalk(Integer[] ids) {
        return talkDao.deleteByIds(List.of(ids)) > 0;
    }

    @Override
    public boolean updateTalk(TalkUpdateEntity talkUpdateEntity) {
        Talk talk = ObjectConvertUtils.convert(talkUpdateEntity, Talk.class);
        return talkDao.updateById(talk) > 0;
    }

    @Override
    public boolean updateTalkStatus(Integer id, String status) {
        LambdaUpdateWrapper<Talk> talkLambdaQueryWrapper = new LambdaUpdateWrapper<>();
        talkLambdaQueryWrapper.eq(Talk::getId, id);
        talkLambdaQueryWrapper.set(Talk::getStatus, status);
        return talkDao.update(null, talkLambdaQueryWrapper) > 0;
    }

    @Override
    public boolean updateTalkTop(Integer id, Integer top) {
        LambdaUpdateWrapper<Talk> talkLambdaQueryWrapper = new LambdaUpdateWrapper<>();
        talkLambdaQueryWrapper.eq(Talk::getId, id);
        talkLambdaQueryWrapper.set(Talk::getTop, top);
        return talkDao.update(null, talkLambdaQueryWrapper) > 0;
    }

    @Override
    public PageData<TalkAggregate> getPageTalkDetails(PageParams pageParams, TalkQueryEntity talkQueryEntity) {
        Page<Talk> page = new Page<>();
        LambdaQueryWrapper<Talk> talkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        talkLambdaQueryWrapper
                .eq(!ObjectUtils.isEmpty(talkQueryEntity.getId()), Talk::getId, talkQueryEntity.getId())
                .eq(StringUtils.isNotBlank(talkQueryEntity.getTalkKey()), Talk::getTalkKey, talkQueryEntity.getTalkKey())
                .like(StringUtils.isNotBlank(talkQueryEntity.getUsername()), Talk::getUsername, talkQueryEntity.getUsername())
                .eq(StringUtils.isNotBlank(talkQueryEntity.getStatus()), Talk::getStatus, talkQueryEntity.getStatus())
                .like(StringUtils.isNotBlank(talkQueryEntity.getDevice()), Talk::getDevice, talkQueryEntity.getDevice())
                .like(StringUtils.isNotBlank(talkQueryEntity.getBrowser()), Talk::getBrowser, talkQueryEntity.getBrowser())
                .between(talkQueryEntity.getBeginCreateTime() != null&&talkQueryEntity.getEndCreateTime() != null, Talk::getCreateTime, talkQueryEntity.getBeginCreateTime(), talkQueryEntity.getEndCreateTime())
                .orderByDesc(Talk::getCreateTime);
        Page<Talk> talkPage = talkDao.selectPage(page, talkLambdaQueryWrapper);
        List<TalkAggregate> talkAggregates = ObjectConvertUtils.convertList(talkPage.getRecords(), TalkAggregate.class);
        // TODO 之后可能会加上这部分功能
        for (TalkAggregate talkAggregate : talkAggregates) {
            talkAggregate.setWordCount(talkAggregate.getContent().length());
            talkAggregate.setReadCount(0);
            talkAggregate.setCommentCount(0);
            talkAggregate.setLikeCount(0);
        }
        return PageUtils.convertPageData(pageParams.getNumber(), pageParams.getSize(), talkPage.getTotal(), talkAggregates);
    }

    @Override
    public PageData<TalkAggregate> getPageTalkDetailsByUser(PageParams pageParams) {
        Page<Talk> page = new Page<>();
        LambdaQueryWrapper<Talk> talkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        talkLambdaQueryWrapper
                .orderByDesc(Talk::getTop)
                .orderByDesc(Talk::getCreateTime);
        Page<Talk> talkPage = talkDao.selectPage(page, talkLambdaQueryWrapper);
        List<TalkAggregate> talkAggregates = ObjectConvertUtils.convertList(talkPage.getRecords(), TalkAggregate.class);
        // TODO 之后可能会加上这部分功能
        for (TalkAggregate talkAggregate : talkAggregates) {
            talkAggregate.setWordCount(talkAggregate.getContent().length());
            talkAggregate.setReadCount(0);
            talkAggregate.setCommentCount(0);
            talkAggregate.setLikeCount(0);
        }
        return PageUtils.convertPageData(pageParams.getNumber(), pageParams.getSize(), talkPage.getTotal(), talkAggregates);
    }

    @Override
    public TalkEntity getTalk(Integer id) {
        Talk talk = talkDao.selectById(id);
        return ObjectConvertUtils.convert(talk, TalkEntity.class);
    }

    @Override
    public TalkAggregate getTalkDetail(Integer id, boolean isVisitor) {
        Talk talk = talkDao.selectById(id);
        TalkAggregate talkAggregate = ObjectConvertUtils.convert(talk, TalkAggregate.class);
        // TODO
        talkAggregate.setWordCount(talkAggregate.getContent().length());
        talkAggregate.setReadCount(0);
        talkAggregate.setCommentCount(0);
        talkAggregate.setLikeCount(0);
        return talkAggregate;
    }
}
