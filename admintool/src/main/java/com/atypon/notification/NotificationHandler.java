package com.atypon.notification;

import com.atypon.domain.Notification;

public interface NotificationHandler {
    void handle(Notification notification);
}
