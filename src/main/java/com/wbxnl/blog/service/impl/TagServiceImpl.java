package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.TagDto;
import com.wbxnl.blog.model.entity.Tag;
import com.wbxnl.blog.dao.TagDao;
import com.wbxnl.blog.model.vo.TagVo;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.model.vo.params.TagParams;
import com.wbxnl.blog.service.TagService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章标签 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class TagServiceImpl extends AbstractServiceImpl<TagDao, Tag, TagDto, TagVo> implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public List<TagDto> getAllDetailByUser() {
        return tagDao.getAllDetailByUser();
    }


    @Override
    public Long getCountByUser() {
        return lambdaQuery().eq(Tag::getHidden,0).count();
    }

    @Override
    public PageData<TagDto> getPage(PageParams pageParams, QueryParams queryParams) {
        TagParams tagParams = (TagParams) queryParams;
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        List<TagDto> details = tagDao.getDetailPage((page - 1) * limit, limit, tagParams);
        long count = count();
        return new PageData<>(details,count);
    }
}
