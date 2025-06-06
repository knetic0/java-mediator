package com.mehmetsolak.mediator;

import com.mehmetsolak.mediator.exception.MediatorHandlerException;
import com.mehmetsolak.mediator.exception.MediatorPipelineException;
import com.mehmetsolak.mediator.handler.RequestHandler;
import com.mehmetsolak.mediator.pipeline.PipelineBehavior;
import com.mehmetsolak.mediator.pipeline.PipelineHandler;
import com.mehmetsolak.mediator.registry.HandleRegistry;
import com.mehmetsolak.mediator.request.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PipelineMediator {
    private final HandleRegistry registry;

    private final Map<Class<?>, List<PipelineBehavior<?, ?>>> pipelineBehaviors;

    public PipelineMediator(HandleRegistry registry) {
        this.registry = registry;
        this.pipelineBehaviors = new HashMap<>();
    }

    public void registerPipeline(
            Class<? extends Request<?>> requestType, PipelineBehavior<? extends Request<?>, ?> behavior) {
        pipelineBehaviors.computeIfAbsent(requestType, k -> new ArrayList<>())
                .add(behavior);
    }

    @SuppressWarnings("unchecked")
    public <TResponse, TRequest extends Request<TResponse>> TResponse send(TRequest request) throws MediatorPipelineException, MediatorHandlerException {
        RequestHandler<TRequest, TResponse> handler = this.registry.getRequestHandler(request.getClass());
        if (handler == null) throw new MediatorPipelineException("Handler not found: " + request.getClass().getName());

        PipelineHandler<TRequest, TResponse> handlerInvoker = handler::handle;

        List<PipelineBehavior<TRequest, TResponse>> behaviors = (List<PipelineBehavior<TRequest, TResponse>>)
                (List<?>) pipelineBehaviors.getOrDefault(request.getClass(), new ArrayList<>());

        PipelineHandler<TRequest, TResponse> chain = handlerInvoker;
        for (int i = behaviors.size() - 1; i >= 0; i--) {
            PipelineBehavior<TRequest, TResponse> behavior = behaviors.get(i);
            PipelineHandler<TRequest, TResponse> nextInChain = chain;
            chain = req -> behavior.handle(req, nextInChain);
        }

        return chain.handle(request);
    }
}
