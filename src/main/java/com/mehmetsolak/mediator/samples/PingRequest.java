package com.mehmetsolak.mediator.samples;

import com.mehmetsolak.mediator.request.Request;

public record PingRequest(String message) implements Request<String> { }
