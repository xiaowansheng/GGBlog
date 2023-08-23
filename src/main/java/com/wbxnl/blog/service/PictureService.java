package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.PictureDto;
import com.wbxnl.blog.model.entity.Picture;
import com.wbxnl.blog.model.vo.PictureVo;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.base.BaseService;

import java.util.Map;

/**
 * <p>
 * 图片 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface PictureService extends BaseService< Picture, PictureDto, PictureVo> {

}
