package com.mehmetsolak.mediator.pipeline;

import com.mehmetsolak.mediator.exception.MediatorHandlerException;
import com.mehmetsolak.mediator.exception.MediatorPipelineException;
import com.mehmetsolak.mediator.request.Request;

public interface PipelineBehavior<TRequest extends Request<TResponse>, TResponse> {
    TResponse handle(TRequest request, PipelineHandler<TRequest, TResponse> next) throws MediatorPipelineException, MediatorHandlerException;
}
