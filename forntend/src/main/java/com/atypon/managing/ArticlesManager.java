package com.atypon.managing;

import com.atypon.backstage.AsynchronousService;
import com.atypon.context.ApplicationContext;
import com.atypon.domain.Notification;
import com.atypon.notification.NotificationHandler;
import com.atypon.notification.NotificationService;
import com.atypon.notification.NotificationsChecker;
import com.atypon.notification.NotificationsHandler;

import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ArticlesManager implements AsynchronousService, NotificationsHandler {

    private static final String savePointName = ArticlesManager.class.getName() + "." + "LastReadNotification";

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    private final NotificationService notificationService;
    private final NotificationHandler articleNotificationHandler;
    private final NotificationsChecker articleNotificationsChecker;
    private final SavePoint savePoint;

    public ArticlesManager() {
        DataSource dataSource = (DataSource) ApplicationContext.getAttribute("dataSource");
        articleNotificationHandler = new ArticleNotificationHandler();
        this.notificationService = (NotificationService) ApplicationContext
                .getAttribute("notificationService");
        this.savePoint = new SavePointImpl(dataSource, savePointName);
        this.articleNotificationsChecker = new ArticleNotificationsChecker(savePoint);
    }

    @Override
    public void start() {
        scheduler.scheduleAtFixedRate(this::check, 0,
                5, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        scheduler.shutdownNow();
    }

    private void check() {
        articleNotificationsChecker.check(notificationService, this);
    }

    @Override
    public void handle(List<Notification> notifications) {
        Notification lastNotification = null;
        for (Notification notification : notifications) {
            articleNotificationHandler.handle(notification);
            lastNotification = notification;
        }
        if (lastNotification != null) {
            savePoint.write(String.valueOf(lastNotification.getId()));
        }
    }
}
