package com.wbxnl.blog.domain.message.repository;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/9 15:17
 */
public interface IMessageRepository {

    void storageCaptcha(String email, String code, long expireTime);
}
