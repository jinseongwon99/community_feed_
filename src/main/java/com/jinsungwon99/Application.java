package com.jinsungwon99;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class);
        System.out.println("http://localhost:8080/model/auth/login");
        }
    }
