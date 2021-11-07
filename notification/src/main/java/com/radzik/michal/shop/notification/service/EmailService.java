package com.radzik.michal.shop.notification.service;

import com.radzik.michal.shop.notification.domain.dao.NotificationHistory;

import java.util.Map;

public interface EmailService {
    void sendEmail(String templateName, String receiverEmail, Map<String, Object> variables);
    void resentEmail(NotificationHistory notificationHistory);

}
