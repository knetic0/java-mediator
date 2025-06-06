package com.mehmetsolak.mediator;

import com.mehmetsolak.mediator.exception.MediatorHandlerException;
import com.mehmetsolak.mediator.exception.MediatorNotificationHandlerException;
import com.mehmetsolak.mediator.handler.NotificationHandler;
import com.mehmetsolak.mediator.handler.RequestHandler;
import com.mehmetsolak.mediator.notification.Notification;
import com.mehmetsolak.mediator.registry.HandleRegistry;
import com.mehmetsolak.mediator.request.Request;

import java.util.List;

public final class Mediator {

    private final HandleRegistry handleRegistry;

    public Mediator(HandleRegistry handleRegistry) {
        this.handleRegistry = handleRegistry;
    }

    public <TResponse, TRequest extends Request<TResponse>> TResponse send(TRequest request) throws MediatorHandlerException {
        @SuppressWarnings("unchecked")
        RequestHandler<TRequest, TResponse> handler = this.handleRegistry.getRequestHandler(request.getClass());
        if (handler == null) throw new MediatorHandlerException("No handler found for request class " + request.getClass());
        return handler.handle(request);
    }

    public <TNotification extends Notification> void publish(TNotification notification) throws MediatorNotificationHandlerException {
        List<NotificationHandler<TNotification>> handlers = this.handleRegistry.getNotificationHandlers(notification.getClass());
        for (NotificationHandler<TNotification> handler : handlers) {
            handler.handle(notification);
        }
    }
}
