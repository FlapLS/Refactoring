package com.example.sprint3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BlLab1Application {

    public static void main(String[] args) {
        SpringApplication.run(BlLab1Application.class, args);
        System.out.println("SWAGGER: http://localhost:8080/swagger-ui/#/");
    
    }

}
