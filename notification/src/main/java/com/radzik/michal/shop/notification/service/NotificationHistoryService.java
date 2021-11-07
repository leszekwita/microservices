package com.radzik.michal.shop.notification.service;

import com.radzik.michal.shop.notification.domain.NotificationHistoryStatus;
import com.radzik.michal.shop.notification.domain.dao.NotificationHistory;

import java.util.List;

public interface NotificationHistoryService {
    NotificationHistory save(NotificationHistory notificationHistory);
    void update(NotificationHistory notificationHistory);
    List<NotificationHistory> getAllNotificationHistory();
    List<NotificationHistory> findByStatusAndCounterLessThan(NotificationHistoryStatus status, int counter);

}
