package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.enums.ContentStateEums;
import com.wbxnl.blog.model.dto.PictureDto;
import com.wbxnl.blog.model.entity.Picture;
import com.wbxnl.blog.dao.PictureDao;
import com.wbxnl.blog.model.vo.PictureVo;
import com.wbxnl.blog.model.vo.params.PictureParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.service.PictureService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.ConvertUtils;
import com.wbxnl.blog.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 图片 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class PictureServiceImpl extends AbstractServiceImpl<PictureDao, Picture, PictureDto, PictureVo> implements PictureService {

    @Override
    public void setQueryParamsByUser(QueryParams queryParams) {
        PictureParams pictureParams = (PictureParams) queryParams;
        pictureParams.setStatus(ContentStateEums.PUBLIC.getName());
    }

    @Override
    public LambdaQueryWrapper<Picture> getQueryWrapper(QueryParams queryParams) {
        PictureParams pictureParams = (PictureParams) queryParams;
        LambdaQueryWrapper<Picture> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtils.isNotNull(pictureParams.getId())){
            queryWrapper.eq(Picture::getId,pictureParams.getId());
        }
        if(ObjectUtils.isNotNull(pictureParams.getUserAuthId())){
            queryWrapper.eq(Picture::getUserAuthId,pictureParams.getUserAuthId());
        }
        if(ObjectUtils.isNotNull(pictureParams.getAlbumId())){
            queryWrapper.eq(Picture::getAlbumId,pictureParams.getAlbumId());
        }
        if(StringUtils.isNotBlank(pictureParams.getName())){
            queryWrapper.like(Picture::getName,pictureParams.getName());
        }
        if(StringUtils.isNotBlank(pictureParams.getStatus())){
            queryWrapper.eq(Picture::getStatus,pictureParams.getStatus());
        }
        if(StringUtils.isNotBlank(pictureParams.getDescription())){
            queryWrapper.like(Picture::getDescription,pictureParams.getDescription());
        }
        if(StringUtils.isNotBlank(pictureParams.getSource())){
            queryWrapper.like(Picture::getSource,pictureParams.getSource());
        }
        if(ObjectUtils.isNotNull(pictureParams.getBeginDate(),pictureParams.getEndDate())){
            queryWrapper.between(Picture::getCreateTime,pictureParams.getBeginDate(),pictureParams.getEndDate());
        }
        queryWrapper.orderByDesc(Picture::getCreateTime);
        return queryWrapper;
    }

    @Override
    @Transactional
    public void saveVoBatch(List<PictureVo> vos) {
        // 填充每张照片的作者信息
        List<Picture> pictures = ConvertUtils.sourceToTarget(vos, Picture.class);
        for (Picture picture : pictures) {
            picture.setUserAuthId(SecurityUtils.getUserAuthId());
            picture.setStatus(ContentStateEums.PUBLIC.getName());
            save(picture);
        }
        // TODO 批量上传bug
//        saveBatch(pictures, pictures.size());

    }
}
