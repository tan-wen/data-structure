package com.went.play.ground;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class PlayGroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayGroundApplication.class, args);
    }

}
