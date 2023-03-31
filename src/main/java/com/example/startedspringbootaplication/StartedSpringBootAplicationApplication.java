package com.example.startedspringbootaplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StartedSpringBootAplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartedSpringBootAplicationApplication.class, args);
    }

    @GetMapping
    public String get() {
        return "Hello World";
    }
}
