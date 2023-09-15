package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.houbb.heaven.util.secrect.Md5Util;
import com.wbxnl.blog.constant.keys.KeyOfView;
import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.exception.BlogException;
import com.wbxnl.blog.model.dto.PageViewDto;
import com.wbxnl.blog.model.entity.PageView;
import com.wbxnl.blog.dao.PageViewDao;
import com.wbxnl.blog.model.entity.Visitor;
import com.wbxnl.blog.model.vo.params.PageViewParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.service.PageViewService;
import com.wbxnl.blog.service.VisitorService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.IPUtils;
import com.wbxnl.blog.utils.RedisUtils;
import com.wbxnl.blog.utils.SecurityUtils;
import com.wbxnl.blog.utils.UserAgentUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 访问量 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
@Slf4j
public class PageViewServiceImpl extends AbstractServiceImpl<PageViewDao, PageView, PageViewDto, PageView> implements PageViewService {
    /**
     * 默认的视图类型
     */
    private static final ViewTypeEums DEFAULT_VIEW_TYPE = ViewTypeEums.WEBSITE;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long increasePageView(String viewType, Integer viewId) {
        /**
         * 保持数据库数据和redis数据双向同步
         */
        /**
         * 流程：
         *      获取uuid
         *      判断是否新的访客
         *        从redis或数据库查看是否是唯一ID
         *          是：
         *              页面添加新的访客信息
         *              页面访问量+1
         *          否：
         *              退出
         */


        ViewTypeEums viewTypeEums = null;
        if (StringUtils.isBlank(viewType)) {
            viewTypeEums = DEFAULT_VIEW_TYPE;
            viewType = viewTypeEums.getName();
            viewId = null;
        } else {
            viewTypeEums = ViewTypeEums.getViewType(viewType);
            // 查看 viewType 是否是存在的类型
            if (viewTypeEums == null) {
                // 请求的枚举类型不存在
                throw new BlogException(OperationStateCode.ENUM_TYPE_NO_EXIST);
            }
        }
        // 查看当前页面的最新的访问量
        String keyOfPageView = KeyOfView.PAGE_VIEW;
        String keyOfNewPageView = KeyOfView.NEW_PAGE_VIEW;
        String keyOfVisitor = KeyOfView.PAGE_VISITOR;
        String keyOfNewVisitor = KeyOfView.NEW_VISITOR;

        // 标识是否是新的访客
        boolean flag=false;
        // 唯一标识，用于判断是否是唯一的访客
        String uuid = null;
        try {
            // 看是否是登录用户，是则用用户名或者用户编号作为标识
            uuid = SecurityUtils.getUsername();
            if (uuid == null) {
                uuid = SecurityUtils.getUserAuthId().toString();
            }
        } catch (Exception e) {
            log.info("当前访客不是登录用户。");
        }
        String ipAddress = IPUtils.getIpAddress(request);
        String ipSource=null;
        String osName=null;
        String browserName=null;
        // 不是登录用户，则使用访客的方式生成 uuid
        if (ObjectUtils.isNull(uuid)) {
            // 不是登录用户，则使用请求信息判断是否是第一次访问
            ipSource = IPUtils.getIpSource(ipAddress);
            osName = UserAgentUtils.getOsName(request);
            browserName = UserAgentUtils.getBrowserName(request);
            // 使用ip、操作系统名称、浏览器名称 ，再加上页面类型和编号 作为唯一标识
            String tempStr = ipAddress + osName + browserName+viewType+viewId;
            uuid = Md5Util.md5(tempStr);
        }
        // 查看是否存在 redis
        // (已解决) xTODO bug 不能根据 viewType 和 ViewId 查询redis中是否存在数据
        // 查看最新的访客待持久化列表是否存在
        Visitor visitor = redisUtils.getCacheMapValue(keyOfNewVisitor, uuid);
        if(ObjectUtils.isNull(visitor)){
            // 查看已经持久化的访客列表是否存在
            visitor=redisUtils.getCacheMapValue(keyOfVisitor,uuid);
        }
        // 如果redis没有，则查查数据库中有没有
        if (ObjectUtils.isNull(visitor)) {
            visitor = visitorService.lambdaQuery()
                    .eq(Visitor::getUuid, uuid)
                    // uuid 已经包含了下面这两个字段唯一
//                        .eq(Visitor::getViewType, viewType)
//                        .eq(Visitor::getViewId, viewId)
                    .one();
            // 如果数据库中有，缓存没有，则添加到redis中的已持久化的访客列表里
            if(ObjectUtils.isNotNull(visitor)){
                redisUtils.setCacheMapValue(keyOfVisitor, uuid, visitor);
            }
        }
        // 判断是否第一次访问
        if (ObjectUtils.isNull(visitor)) {
            flag=true;
            // redis 和 数据库 都没有，说明是第一次访问对应页面
            visitor = new Visitor()
                    .setUuid(uuid)
                    .setViewType(viewType)
                    .setViewId(viewId)
                    .setIpAddress(ipAddress)
                    .setIpSource(ipSource)
                    .setDevice(osName)
                    .setBrowser(browserName);
            // 第一次访问则添加新的记录
            //  定时器，定期从redis同步到数据库，不用手动实时添加到数据库
//                visitorService.save(visitor);
            // 将新的访客记录添加到redis中的未持久化访客列表
            redisUtils.setCacheMapValue(keyOfNewVisitor, uuid, visitor);
        } else {
            // 不是第一次访问，则不重复记录访问信息
            flag=false;
        }
        // 第一次访问，则对应页面访问量+1
        // 先从缓存中获取最新数据
        // 默认页面访问量的 唯一标识使用 viewType+viewId
        String uuidOfPageView=viewType+viewId;
        // 查询最新缓存数据
        PageView pageView=redisUtils.getCacheMapValue(keyOfNewPageView,uuidOfPageView);
        if(ObjectUtils.isNull(pageView)){
            // 查查看是否已经是在持久化过的缓存列表
            pageView = redisUtils.getCacheMapValue(keyOfPageView,uuidOfPageView);
        }
        // 检查是否有缓存
        if (ObjectUtils.isNull(pageView)) {
            // 没有缓存，则查询数据库，看有没有数据
            LambdaQueryChainWrapper<PageView> chainWrapper = lambdaQuery();
            chainWrapper.eq(PageView::getViewType, viewType);
            if (ObjectUtils.isNotNull(viewId)) {
                chainWrapper.eq(PageView::getViewId, viewId);
            }
            // 根据条件从数据库中获取最新的记录
            pageView = chainWrapper.orderByDesc(PageView::getCreateTime).one();
            if (ObjectUtils.isNull(pageView)) {
                // 如果 缓存没有，数据库没有，则创建一个新的
                pageView = new PageView()
                        .setCount(1L)
                        .setViewType(viewType)
                        .setViewId(viewId);
                // 缓存没有，数据库没有，则直接保存一个新的
                save(pageView);
                // 将当前的访问数据放到访问量待持久化的缓存中
                redisUtils.setCacheMapValue(keyOfPageView,uuidOfPageView, pageView);
            } else {
                // 如果缓存没有，数据库中有
                if(flag){
                    // 是新的访客访问，则访问量+1
                    pageView.setCount(pageView.getCount() + 1);
                    // 将当前的访问数据放到访问量待持久化的缓存中
                    redisUtils.setCacheMapValue(keyOfNewPageView,uuidOfPageView, pageView);
                }else {
                    // 将当前的访问数据放到访问量缓存中
                    redisUtils.setCacheMapValue(keyOfPageView,uuidOfPageView, pageView);
                }
            }
        } else {
            // 如果有缓存
            if(flag){
                // 新的访客访问，则访问量+1
                pageView.setCount(pageView.getCount() + 1);
                // 将新的访问量信息保存到待持久化缓存
                redisUtils.setCacheMapValue(keyOfNewPageView,uuidOfPageView, pageView);
            }
        }
        // 定时器，定期从redis同步到数据库，不用手动实时存储
//        if(flag){
//            // 如果是新的访客
//            // 修改数据库中最新的访问量数据
//            saveOrUpdate(pageView);
//        }
        return pageView.getCount();
    }


    @Override
    public LambdaQueryWrapper<PageView> getQueryWrapper(QueryParams queryParams) {
        PageViewParams viewParams = (PageViewParams) queryParams;
        LambdaQueryWrapper<PageView> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotNull(viewParams.getId())) {
            queryWrapper.eq(PageView::getId, viewParams.getId());
        }
        if (ObjectUtils.isNotNull(viewParams.getViewType())) {
            queryWrapper.eq(PageView::getViewType, viewParams.getViewType());
            if (ObjectUtils.isNotNull(viewParams.getViewId())) {
                queryWrapper.eq(PageView::getViewId, viewParams.getViewId());
            }
        }
        if (ObjectUtils.isNotNull(viewParams.getMin(), viewParams.getMax())) {
            queryWrapper.between(PageView::getCount, viewParams.getMin(), viewParams.getMax());
        }
        queryWrapper.orderByDesc(PageView::getCount);
        return queryWrapper;
    }

}
