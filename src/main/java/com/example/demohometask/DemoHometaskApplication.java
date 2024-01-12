package com.example.demohometask;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class DemoHometaskApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoHometaskApplication.class, args);
    }


}
