package com.mehmetsolak.mediator.config;

import com.mehmetsolak.mediator.Mediator;
import com.mehmetsolak.mediator.PipelineMediator;
import com.mehmetsolak.mediator.handler.RequestHandler;
import com.mehmetsolak.mediator.pipeline.PipelineBehavior;
import com.mehmetsolak.mediator.registry.HandleRegistry;
import com.mehmetsolak.mediator.request.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;

import java.util.List;

@Configuration
public class MediatorAutoConfiguration {

    @Bean
    @SuppressWarnings("unchecked")
    public HandleRegistry handleRegistry(List<RequestHandler<?, ?>> requestHandlers) {
        HandleRegistry handleRegistry = new HandleRegistry();

        for(RequestHandler<?, ?> requestHandler : requestHandlers) {
            Class<?> handlerClass = requestHandler.getClass();

            ResolvableType resolvableType = ResolvableType.forClass(handlerClass)
                    .as(RequestHandler.class);
            Class<? extends Request<?>> requestType = (Class<? extends Request<?>>) resolvableType.getGeneric(0).resolve();
            if (requestType == null) {
                throw new IllegalStateException(
                        handlerClass.getName() +
                                " must be implemented as RequestHandler<TRequest, TResponse>!"
                );
            }

            handleRegistry.registerRequestHandler(requestType, requestHandler);
        }

        return handleRegistry;
    }

    @Bean
    public Mediator mediator(HandleRegistry registry) {
        return new Mediator(registry);
    }

    @Bean
    @SuppressWarnings("unchecked")
    public PipelineMediator pipelineMediator(
            HandleRegistry registry,
            List<PipelineBehavior<?, ?>> allBehaviors
    ) {
        PipelineMediator pm = new PipelineMediator(registry);

        for (PipelineBehavior<?, ?> behaviorBean : allBehaviors) {
            Class<?> behaviorClass = behaviorBean.getClass();
            ResolvableType resolvableType = ResolvableType.forClass(behaviorClass)
                    .as(PipelineBehavior.class);
            Class<? extends Request<?>> requestType = (Class<? extends Request<?>>) resolvableType.getGeneric(0).resolve();
            if (requestType == null) {
                throw new IllegalStateException(
                        behaviorClass.getName() +
                                " must be implemented as PipelineBehavior<TRequest, TResponse>!"
                );
            }

            pm.registerPipeline(requestType, behaviorBean);
        }
        return pm;
    }
}
