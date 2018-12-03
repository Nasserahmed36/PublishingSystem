package com.atypon.notification;

import com.atypon.domain.Notification;

import java.util.List;

public interface NotificationsHandler {
    void handle(List<Notification> notifications);
}
