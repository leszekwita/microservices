package com.radzik.michal.shop.notification.service.impl;

import com.radzik.michal.shop.notification.domain.NotificationHistoryStatus;
import com.radzik.michal.shop.notification.repository.NotificationRepository;
import com.radzik.michal.shop.notification.domain.dao.NotificationHistory;
import com.radzik.michal.shop.notification.service.NotificationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationHistoryServiceImpl implements NotificationHistoryService {

    private final NotificationRepository notificationRepository;

    @Override
    public NotificationHistory save(NotificationHistory notificationHistory) {
        return notificationRepository.save(notificationHistory);
    }

    @Override
    public void update(NotificationHistory notificationHistory) {

        notificationRepository.save(notificationHistory);
    }

    @Override
    public List<NotificationHistory> getAllNotificationHistory() {

        return notificationRepository.findAll();
    }

    @Override
    public List<NotificationHistory> findByStatusAndCounterLessThan(NotificationHistoryStatus status, int counter) {
        return notificationRepository.findByStatusAndCounterLessThan(status,counter);
    }
}
