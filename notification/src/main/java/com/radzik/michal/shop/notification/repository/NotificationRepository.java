package com.radzik.michal.shop.notification.repository;

import com.radzik.michal.shop.notification.domain.NotificationHistoryStatus;
import com.radzik.michal.shop.notification.domain.dao.NotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationHistory,Long> {

    List<NotificationHistory> findByStatusAndCounterLessThan(NotificationHistoryStatus status, int counter);
}
