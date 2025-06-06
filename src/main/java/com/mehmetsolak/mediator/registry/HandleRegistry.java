package com.mehmetsolak.mediator.registry;

import com.mehmetsolak.mediator.handler.NotificationHandler;
import com.mehmetsolak.mediator.handler.RequestHandler;
import com.mehmetsolak.mediator.notification.Notification;
import com.mehmetsolak.mediator.request.Request;

import java.util.*;

public final class HandleRegistry {

    private final Map<Class<? extends Request<?>>, RequestHandler<? extends Request<?>, ?>> requestHandlers;
    private final Map<Class<? extends Notification>, List<NotificationHandler<? extends Notification>>> notificationHandlers;

    public HandleRegistry() {
        this.requestHandlers = new HashMap<>();
        this.notificationHandlers = new HashMap<>();
    }

    public void registerRequestHandler(Class<? extends Request<?>> request, RequestHandler<? extends Request<?>, ?> handler) {
        this.requestHandlers.put(request, handler);
    }

    public <TNotification extends Notification> void registerNotificationHandler(Class<TNotification> notification, NotificationHandler<TNotification> handlers) {
        this.notificationHandlers.computeIfAbsent(notification, _ -> new ArrayList<>()).add(handlers);
    }

    @SuppressWarnings("unchecked")
    public <TRequest extends Request<TResponse>, TResponse> RequestHandler<TRequest, TResponse> getRequestHandler(Class<TRequest> request) {
        return (RequestHandler<TRequest, TResponse>) this.requestHandlers.get(request);
    }

    @SuppressWarnings("unchecked")
    public <TNotification extends Notification> List<NotificationHandler<TNotification>> getNotificationHandlers(Class<? extends Notification> notification) {
        return (List<NotificationHandler<TNotification>>) (List<?>)
                notificationHandlers.getOrDefault(notification, Collections.emptyList());
    }
}
