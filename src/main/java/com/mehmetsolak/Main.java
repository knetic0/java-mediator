package com.mehmetsolak;

import com.mehmetsolak.mediator.Mediator;
import com.mehmetsolak.mediator.PipelineMediator;
import com.mehmetsolak.mediator.samples.PingRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner testMediator(Mediator mediator) {
        return args -> {
            System.out.println("=== Mediator Otomatik Test Başladı ===");

            PingRequest ping = new PingRequest("HelloSpring");
            String result = mediator.send(ping);

            System.out.println("PingRequest gönderildi: mesaj='HelloSpring'");
            System.out.println("Mediator yanıtı: " + result);
            System.out.println("=== Mediator Otomatik Test Bitti ===");
        };
    }

    @Bean
    public CommandLineRunner testPipeline(PipelineMediator pipelineMediator) {
        return args -> {
            System.out.println("=== PipelineMediator Otomatik Test Başladı ===");
            PingRequest ping = new PingRequest("PipelineHello");
            String res2 = pipelineMediator.send(ping);
            System.out.println("PipelineMediator yanıtı: " + res2);
            System.out.println("=== PipelineMediator Test Bitti ===");
        };
    }
}