package com.wbxnl.blog.domain.message.model.aggregate;

import com.wbxnl.blog.common.enums.TopicTypeEnum;
import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/23 14:21
 */
@Data
public class CommentNoticeAggregate {
    private String email;
    private TopicTypeEnum topicType;
    private String topicKey;
    private String replyCommentKey;
    private String userType;
    private String username;
    private String nickname;
    private String content;
    private String ipAddress;
    private String ipSource;
    private byte[] coordinate;
    private String location;
    private String device;
    private String browser;

}
