package com.mehmetsolak.mediator.annotation;

import com.mehmetsolak.mediator.config.MediatorAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MediatorAutoConfiguration.class)
public @interface EnableMediator { }
