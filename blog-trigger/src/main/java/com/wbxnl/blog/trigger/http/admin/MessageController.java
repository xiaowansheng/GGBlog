package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.service.IMessageService;
import com.wbxnl.blog.domain.message.service.IEmailService;
import com.wbxnl.blog.domain.message.service.INoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:09
 */
@Slf4j
@RestController
@RequestMapping("/admin/message")
@RequiredArgsConstructor
public class MessageController implements IMessageService {

    private final INoticeService noticeService;

    private final IEmailService emailService;

    @Override
    public void sendVerifyCode(String email) {

    }

    @Override
    public void sendMessage(String email, String title, String content) {

    }
}
