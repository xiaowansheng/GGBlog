package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.model.dto.ArticleTagDto;
import com.wbxnl.blog.model.entity.ArticleTag;
import com.wbxnl.blog.dao.ArticleTagDao;
import com.wbxnl.blog.model.vo.ArticleTagVo;
import com.wbxnl.blog.service.ArticleTagService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章对应标签 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class ArticleTagServiceImpl extends AbstractServiceImpl<ArticleTagDao, ArticleTag, ArticleTagDto, ArticleTagVo> implements ArticleTagService {

}
