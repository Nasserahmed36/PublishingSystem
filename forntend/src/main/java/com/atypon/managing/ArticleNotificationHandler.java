package com.atypon.managing;

import com.atypon.domain.Notification;
import com.atypon.notification.NotificationHandler;

import static com.atypon.domain.Notification.Operation;


public class ArticleNotificationHandler implements NotificationHandler {

    @Override
    public void handle(Notification notification) {
        String articleDoi = notification.getContent();
        Operation operation = notification.getOperation();
        switch (operation) {
            case CREATED:
                System.out.println(articleDoi + " Created");
                break;
            case DELETED:
                System.out.println(articleDoi + " Deleted");
                break;
            case UPDATED:
                System.out.println(articleDoi + " Updated");
        }
    }
}
