package com.atypon.managing;

import com.atypon.notification.NotificationService;
import com.atypon.notification.NotificationsChecker;
import com.atypon.notification.NotificationsHandler;

public class ArticleNotificationsChecker implements NotificationsChecker {

    private static final String TYPE = "Article";

    private final SavePoint savePoint;

    public ArticleNotificationsChecker(SavePoint savePoint) {
        this.savePoint = savePoint;
    }

    @Override
    public void check(NotificationService notificationService, NotificationsHandler handler) {
        int lastHandledNotification = 0;
        if (savePoint.exists()) {
            lastHandledNotification = Integer.parseInt(savePoint.read());
        }
        handler.handle(notificationService.getAllAfter(TYPE,lastHandledNotification));
    }

}
