package com.mehmetsolak.mediator.handler;

import com.mehmetsolak.mediator.exception.MediatorHandlerException;
import com.mehmetsolak.mediator.request.Request;

/**
 * “TRequest”  : The request type of handler.
 * "TResponse" : The return type of handler.
 */
public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {
    TResponse handle(TRequest request) throws MediatorHandlerException;
}
