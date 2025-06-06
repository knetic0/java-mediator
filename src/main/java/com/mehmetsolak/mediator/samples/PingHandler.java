package com.mehmetsolak.mediator.samples;

import com.mehmetsolak.mediator.handler.RequestHandler;
import org.springframework.stereotype.Component;

@Component
public final class PingHandler implements RequestHandler<PingRequest, String> {

    @Override
    public String handle(PingRequest pingRequest) {
        return "Pong! " + pingRequest.message();
    }
}
