package com.mehmetsolak.mediator.handler;

import com.mehmetsolak.mediator.exception.MediatorNotificationHandlerException;
import com.mehmetsolak.mediator.notification.Notification;

/**
 * “TNotification”  : The notification type.
 */
public interface NotificationHandler<TNotification extends Notification> {
    void handle(TNotification notification) throws MediatorNotificationHandlerException;
}
