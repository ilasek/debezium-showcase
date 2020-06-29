package com.student.service.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DebeziumEventConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(DebeziumEventConsumerApp.class, args);
    }

}
