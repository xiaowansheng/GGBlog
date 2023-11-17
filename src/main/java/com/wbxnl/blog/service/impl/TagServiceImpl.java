package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.enums.ArticleTypeEums;
import com.wbxnl.blog.enums.ContentStateEums;
import com.wbxnl.blog.model.dto.TagDto;
import com.wbxnl.blog.model.entity.Tag;
import com.wbxnl.blog.dao.TagDao;
import com.wbxnl.blog.model.vo.TagVo;
import com.wbxnl.blog.model.vo.params.ArticleParams;
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
        ArticleParams articleParams = new ArticleParams();
        articleParams.setType(ArticleTypeEums.DRAFT.getName());
        articleParams.setStatus(ContentStateEums.PUBLIC.getName());
        return tagDao.getAllDetailByUser(articleParams);
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
        LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tagLambdaQueryWrapper
                .eq(ObjectUtils.isNotNull(tagParams.getId()),Tag::getId,tagParams.getId())
                .like(StringUtils.isNotBlank(tagParams.getName()),Tag::getName,tagParams.getName())
                .eq(ObjectUtils.isNotNull(tagParams.getHidden()),Tag::getHidden,tagParams.getHidden());
        long count = count(tagLambdaQueryWrapper);
        return new PageData<>(details,count);
    }
}
