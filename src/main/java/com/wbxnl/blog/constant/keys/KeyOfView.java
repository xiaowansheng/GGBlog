package com.wbxnl.blog.constant.keys;

/**
 *
 * @Author xiaowansheng
 * @Date 2023/8/19 3:01
 */
public interface KeyOfView {

    /**
     * page view key ，页面访问量查询key
     */
    String PAGE_VIEW ="page:view";
    /**
     * new page view key ，页面访问量查询key，存放更新过的访问量数据
     */
    String NEW_PAGE_VIEW ="page:view:new";
    /**
     * visitor key，存放所有的已持久化的访客信息
     */
    String PAGE_VISITOR ="page:visitor";
    /**
     * new visitor key ，存放还没有持久化的访客信息
     */
    String NEW_VISITOR ="page:visitor:new";
}
