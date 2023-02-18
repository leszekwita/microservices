package com.radzik.michal.shop.notification.service.impl;

import com.radzik.michal.shop.notification.domain.NotificationHistoryStatus;
import com.radzik.michal.shop.notification.domain.dao.NotificationHistory;
import com.radzik.michal.shop.notification.domain.dao.Template;
import com.radzik.michal.shop.notification.service.EmailService;
import com.radzik.michal.shop.notification.service.NotificationHistoryService;
import com.radzik.michal.shop.notification.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;

    private final ITemplateEngine templateEngine;

    private final TemplateService templateService;

    private final NotificationHistoryService notificationHistoryService;

    @Override
    @Async
    public void sendEmail(String templateName, String receiverEmail, Map<String, Object> variables) {
       log.info("Sending email");
        Template template = templateService.findByName(templateName);
        String body = templateEngine.process(template.getBody(), new Context(new Locale("pl"), variables));
log.info("template body {}",body);
        NotificationHistory notificationHistory = notificationHistoryService.save(NotificationHistory.builder()
                .status(NotificationHistoryStatus.CREATED)
                .body(body)
                .toEmail(receiverEmail)
                .subject(template.getSubject())
                .build());
        try {
            sendEmail(notificationHistory);
        } catch (Exception e) {
            log.error("Error during sending email", e);
            notificationHistory.setStatus(NotificationHistoryStatus.ERROR);
        }
        notificationHistoryService.update(notificationHistory);
    }

    @Async
    public void resentEmail(NotificationHistory notificationHistory){
        try {
            sendEmail(notificationHistory);

        } catch (Exception e) {
            log.error("Error during sending email", e);
            notificationHistory.setStatus(NotificationHistoryStatus.ERROR);
            int counter = notificationHistory.getCounter();
            notificationHistory.setCounter(counter+1);
        }
        notificationHistoryService.update(notificationHistory);
    }

    private void sendEmail(NotificationHistory notificationHistory) {
        sender.send(mimeMessage -> {

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(notificationHistory.getToEmail());
            helper.setFrom("michal.radzik@wp.pl");
            helper.setSubject(notificationHistory.getSubject());
            helper.setText(notificationHistory.getBody(), true);

        });
        notificationHistory.setStatus(NotificationHistoryStatus.SUCCESS);
    }
}

