package com.mehmetsolak.mediator.pipeline;

import com.mehmetsolak.mediator.exception.MediatorHandlerException;
import com.mehmetsolak.mediator.exception.MediatorPipelineException;
import com.mehmetsolak.mediator.request.Request;

@FunctionalInterface
public interface PipelineHandler<TRequest extends Request<TResponse>, TResponse> {
    TResponse handle(TRequest request) throws MediatorPipelineException, MediatorHandlerException;
}
