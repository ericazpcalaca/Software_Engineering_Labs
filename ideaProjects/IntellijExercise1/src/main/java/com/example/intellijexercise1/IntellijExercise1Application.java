package com.example.intellijexercise1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IntellijExercise1Application {

    public static void main(String[] args) {

        SpringApplication.run(IntellijExercise1Application.class, args);
    }

}
