package com.wbxnl.blog.infrastructure.persistent.repository;

import com.wbxnl.blog.common.cache.CacheKey;
import com.wbxnl.blog.domain.message.repository.IMessageRepository;
import com.wbxnl.blog.infrastructure.persistent.redis.IRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/9 15:28
 */
@Service
@RequiredArgsConstructor
public class MessageRepository implements IMessageRepository {

    private final IRedisService redisService;

    @Override
    public void storageCaptcha(String email, String code, long expireTime) {
        String captchaKey = CacheKey.getCaptchaKey(email);
        redisService.set(captchaKey, code, expireTime);
    }
}
