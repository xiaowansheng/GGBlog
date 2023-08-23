package com.wbxnl.blog.constant.keys;

/**
 * @Author xiaowansheng
 * @Date 2023/8/20 3:50
 */
public interface KeyOfStatistics {

    /**
     * 存储访问的所有ip地址，需要使用set去重
     */
    String IP_ADDRESS="website:ip";

    /**
     * 存储访问的所有ip地址所在地，需要使用set去重
     */
    String IP_SOURCE="website:ip:source";
}
