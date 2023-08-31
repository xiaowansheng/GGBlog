package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.enums.ContentStateEums;
import com.wbxnl.blog.enums.TopicTypeEums;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.model.dto.AlbumDto;
import com.wbxnl.blog.model.entity.Album;
import com.wbxnl.blog.dao.AlbumDao;
import com.wbxnl.blog.model.vo.AlbumVo;
import com.wbxnl.blog.model.vo.params.AlbumParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.service.AlbumService;
import com.wbxnl.blog.service.CommentService;
import com.wbxnl.blog.service.PageViewService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 相册 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class AlbumServiceImpl extends AbstractServiceImpl<AlbumDao, Album, AlbumDto, AlbumVo> implements AlbumService {

    @Autowired
    private PageViewService pageViewService;
    @Autowired
    private CommentService commentService;

    @Override
    public AlbumDto getAlbumByUser(Integer id) {
        Album album = lambdaQuery().eq(Album::getStatus, ContentStateEums.PUBLIC).eq(Album::getId, id).one();
        AlbumDto albumDto = ConvertUtils.sourceToTarget(album, AlbumDto.class);
        // 添加相册的用户浏览信息
        if (ObjectUtils.isNotNull(albumDto)) {
            Long pageView = pageViewService.increasePageView(ViewTypeEums.ALBUM.getName(), id);
            // 响应结果中添加访问量
            albumDto.setPageView(pageView);
            // 添加评论数量
            Long count = commentService.getCountByUser(TopicTypeEums.Album.getName(),id);
            albumDto.setCommentCount(count);
        }
        return albumDto;
    }

    @Override
    public void setQueryParamsByUser(QueryParams queryParams) {
        AlbumParams albumParams = (AlbumParams) queryParams;
        albumParams.setStatus(ContentStateEums.PUBLIC.getName());
    }

    @Override
    public LambdaQueryWrapper<Album> getQueryWrapper(QueryParams queryParams) {
        AlbumParams albumParams = (AlbumParams) queryParams;
        LambdaQueryWrapper<Album> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotNull(albumParams.getId())) {
            queryWrapper.eq(Album::getId, albumParams.getId());
        }
        if (ObjectUtils.isNotNull(albumParams.getUserAuthId())) {
            queryWrapper.eq(Album::getUserAuthId, albumParams.getUserAuthId());
        }
        if (StringUtils.isNotBlank(albumParams.getName())) {
            queryWrapper.like(Album::getName, albumParams.getName());
        }
        if (StringUtils.isNotBlank(albumParams.getDescription())) {
            queryWrapper.like(Album::getDescription, albumParams.getDescription());
        }
        if (StringUtils.isNotBlank(albumParams.getStatus())) {
            queryWrapper.eq(Album::getStatus, albumParams.getStatus());
        }
        if (ObjectUtils.isNotNull(albumParams.getBeginDate(), albumParams.getEndDate())) {
            queryWrapper.between(Album::getCreateTime, albumParams.getBeginDate(), albumParams.getEndDate());
        }
        queryWrapper.orderByDesc(Album::getCreateTime);
        return queryWrapper;
    }

}
