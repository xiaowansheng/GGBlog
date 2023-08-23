package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.AlbumDto;
import com.wbxnl.blog.model.entity.Album;
import com.wbxnl.blog.model.vo.AlbumVo;
import com.wbxnl.blog.model.vo.params.AlbumParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 相册 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface AlbumService extends BaseService<Album, AlbumDto, AlbumVo> {
    /**
     * 用户根据id查询相册信息
     *  并且添加说说的访客信息
     * @param id
     * @return
     */
    AlbumDto getAlbumByUser(Integer id);
}
