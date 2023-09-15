package com.wbxnl.blog.timer;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wbxnl.blog.constant.keys.KeyOfView;
import com.wbxnl.blog.model.entity.PageView;
import com.wbxnl.blog.model.entity.Visitor;
import com.wbxnl.blog.service.PageViewService;
import com.wbxnl.blog.service.VisitorService;
import com.wbxnl.blog.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

/**
 * 定时器，定期执行计划
 *
 * @Author xiaowansheng
 * @Date 2023/8/19 2:35
 */
@Component
@Slf4j
public class ScheduleTasks {
    @Autowired
    private PageViewService pageViewService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 每天凌晨4点执行一次
     * 定时把redis的新访客，新访问量存储到数据库
     */
//    @Scheduled(fixedRate = 12 * 60 * 60 * 1000) // 每12小时执行一次
    @Scheduled(cron = "0 0 4 * * *")
    @Transactional(rollbackFor = Exception.class)
    public void executeSaveBatchVisitor() {
        Map<String, Visitor> visitorMap = null;
        Map<String, PageView> pageViewMap = null;
        try {
            // 在这里执行您的定时任务逻辑
            System.out.println("定时任务每12小时执行一次");
            // 保存访客信息
            visitorMap = redisUtils.getCacheMap(KeyOfView.NEW_VISITOR);
            ArrayList<Visitor> visitors=null;
            if(CollectionUtils.isNotEmpty(visitorMap)){
                // 获取以后，清除待更新的 VISITOR 列表
                redisUtils.deleteObject(KeyOfView.NEW_VISITOR);
                visitors = new ArrayList<>(visitorMap.values());
                visitorService.saveOrUpdateBatch(visitors, visitors.size());
                // 更新redis的访客信息缓存
                for (Visitor visitor : visitors) {
                    redisUtils.setCacheObject(KeyOfView.PAGE_VISITOR, visitor.getUuid());
                }
            }
            // 保存浏览量信息
            pageViewMap = redisUtils.getCacheMap(KeyOfView.NEW_PAGE_VIEW);
            ArrayList<PageView> pageViews=null;
            if(CollectionUtils.isNotEmpty(pageViewMap)){
                // 获取以后，清除待更新的 pageView 列表
                redisUtils.deleteObject(KeyOfView.NEW_PAGE_VIEW);
                pageViews = new ArrayList<>(pageViewMap.values());
                pageViewService.saveOrUpdateBatch(pageViews, pageViews.size());
                // 更新redis的访客最新值
                for (PageView pageView : pageViews) {
                    String uuid = pageView.getViewType() + pageView.getViewId();
                    redisUtils.setCacheMapValue(KeyOfView.PAGE_VIEW, uuid, pageView);
                }
            }
        } catch (Exception e) {
            log.error("redis数据持久化到数据库操作失败。");
            e.printStackTrace();
            // 恢复没有没有存储成功的数据
            if (CollectionUtils.isNotEmpty(visitorMap)) {
                redisUtils.setCacheMap(KeyOfView.NEW_VISITOR, visitorMap);
            }
            if (CollectionUtils.isNotEmpty(pageViewMap)) {
                redisUtils.setCacheMap(KeyOfView.NEW_PAGE_VIEW, pageViewMap);
            }
        }
    }
}
