package com.atypon.notification;

import com.atypon.domain.Notification;
import com.atypon.domain.dao.NotificationDao;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private final NotificationDao dao;

    public NotificationServiceImpl(NotificationDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(Notification notification) {
        return dao.create(notification);
    }

    @Override
    public List<Notification> getAllAfter(String type, int serialNumber) {
        return dao.getAllAfter(type,serialNumber);
    }
}
