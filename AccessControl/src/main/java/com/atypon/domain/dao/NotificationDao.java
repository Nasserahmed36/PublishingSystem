package com.atypon.domain.dao;

import com.atypon.domain.Notification;

import java.util.List;

public interface NotificationDao {
    boolean create(Notification notification);

    List<Notification> getAllAfter(String type, int serialNumber);
}
