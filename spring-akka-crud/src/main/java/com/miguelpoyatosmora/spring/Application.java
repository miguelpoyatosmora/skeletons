package com.miguelpoyatosmora.spring;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.miguelpoyatosmora.actors.coordinator.CoordinatorActor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Application {

    @Bean
    ActorSystem actorSystem() {
        ActorSystem as = ActorSystem.create();
        as.actorOf(Props.create(CoordinatorActor.class), "coordinator");
        return as;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}