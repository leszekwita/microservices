package com.radzik.michal.shop.notification.scheduler;

import com.radzik.michal.shop.notification.domain.NotificationHistoryStatus;
import com.radzik.michal.shop.notification.domain.dao.NotificationHistory;
import com.radzik.michal.shop.notification.service.EmailService;
import com.radzik.michal.shop.notification.service.NotificationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RetrySendEmailScheduler {

    private final NotificationHistoryService notificationHistoryService;

    private final EmailService emailService;

    @Scheduled(fixedDelay = 60*1000)
    public void retrySendEmail(){
        List<NotificationHistory> histories = notificationHistoryService.findByStatusAndCounterLessThan(NotificationHistoryStatus.ERROR, 5);
        histories.forEach(emailService::resentEmail);
    }

}
