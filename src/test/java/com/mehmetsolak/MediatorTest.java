package com.mehmetsolak;

import org.junit.jupiter.api.Test;

import com.mehmetsolak.mediator.Mediator;
import com.mehmetsolak.mediator.exception.MediatorHandlerException;
import com.mehmetsolak.mediator.registry.HandleRegistry;
import com.mehmetsolak.mediator.samples.PingHandler;
import com.mehmetsolak.mediator.samples.PingRequest;

public class MediatorTest {

    @Test
    public void testMediatorSendService() throws MediatorHandlerException {
        HandleRegistry registry = new HandleRegistry();

        PingRequest request = new PingRequest("Hello World!");

        registry.registerRequestHandler(PingRequest.class, new PingHandler());

        Mediator mediator = new Mediator(registry);

        String response = mediator.send(request);

        assert response.equals("Pong! Hello World!");
    }
}
