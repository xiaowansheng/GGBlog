package com.wbxnl.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wansheng
 * @createDate 2023/2/27 4:08
 */
@AllArgsConstructor
@Getter
public enum SystemConfigEmus {

//    配置集合
    Covers("cover:"),
    Menu("menu:"),
    AuthorInfo("author:info:"),
    AuthorContact("author:contact:"),
    WebsiteConfig("website:info:"),
    Login("login:"),
    Modules("module:"),
    Privacy("privacy:"),
    Review("review:"),
    About("about:Author"),
    Notice("notice:"),
    Avatar("avatar:"),
    Reward("reward:"),
//单一配置
    /**
     * 多层评论显示
     */
    MultipleLayerComment("MultipleLayerComment"),
    /**
     * 是否开启评论审核
     */
    CommentReview("Comment"),


    //有任何新评论通知
    HaveComment("HaveComment"),
    //通知与我相关
    RelateToMe("RelateToMe"),
    //通知被回复人
    Reply("Reply"),
    //登录通知
    HaveLogin("Login"),
    //注册通知
    AddUser("AddUser"),
    //访客总数
    WebsiteVisitorCount("visitor:website:count"),
    //访客ip
    VisitorIp("visitor:ip"),
    //访客的来源信息
    VisitorHash("visitor:visitors"),
    //唯一访客
    UniqueVisitor("visitor:unique"),
    //文章访问量key
    ArticleVisitorCount("visitor:article"),
    //说说访问量key
    TalkVisitorCount("visitor:talk");
//    private final String key;

    private final String value;
}
