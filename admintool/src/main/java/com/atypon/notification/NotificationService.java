package com.atypon.notification;

import com.atypon.domain.Notification;

import java.util.List;

public interface NotificationService {

    boolean create(Notification notification);

    List<Notification> getAllAfter(String type, int serialNumber);
}
