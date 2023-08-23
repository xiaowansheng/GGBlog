package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.TagDto;
import com.wbxnl.blog.model.entity.Tag;
import com.wbxnl.blog.model.vo.TagVo;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.TagParams;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 文章标签 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface TagService extends BaseService<Tag, TagDto, TagVo> {
    /**
     * 用户查询所有标签信息
     * @return
     */
    public List<TagDto> getAllDetailByUser();

    /**
     * 获取标签数量
     * 主页博客文章概览时需要使用
     * @return
     */
    public Long getCountByUser();
}
