package com.radzik.michal.shop.notification.domain.dao;

import com.radzik.michal.shop.notification.domain.NotificationHistoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime lastProbe;
    private String toEmail;
    private String subject;
    @Lob
    private String body;

    @Enumerated(EnumType.STRING)
    private NotificationHistoryStatus status;

    private int counter;


}
