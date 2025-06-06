package com.mehmetsolak.mediator.samples;

import com.mehmetsolak.mediator.annotation.MediatorHandler;
import com.mehmetsolak.mediator.handler.RequestHandler;

@MediatorHandler
public final class PingHandler implements RequestHandler<PingRequest, String> {

    @Override
    public String handle(PingRequest pingRequest) {
        return "Pong! " + pingRequest.message();
    }
}
