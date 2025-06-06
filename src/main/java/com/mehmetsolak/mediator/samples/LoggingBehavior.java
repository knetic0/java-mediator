package com.mehmetsolak.mediator.samples;

import com.mehmetsolak.mediator.exception.MediatorHandlerException;
import com.mehmetsolak.mediator.exception.MediatorPipelineException;
import com.mehmetsolak.mediator.pipeline.PipelineBehavior;
import com.mehmetsolak.mediator.pipeline.PipelineHandler;
import com.mehmetsolak.mediator.request.Request;

public final class LoggingBehavior<TRequest extends Request<TResponse>, TResponse> implements PipelineBehavior<TRequest, TResponse> {

    @Override
    public TResponse handle(TRequest request, PipelineHandler<TRequest, TResponse> next) throws MediatorPipelineException, MediatorHandlerException {
        System.out.println("[LOG] Request starting: " + request.getClass().getSimpleName());
        TResponse response = next.handle(request);
        System.out.println("[LOG] Request completed: " + request.getClass().getSimpleName());
        return response;
    }
}
