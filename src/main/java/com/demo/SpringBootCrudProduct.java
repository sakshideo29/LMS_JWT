package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot CRUD Product Application.
 * 
 * This class is the starting point for the Spring Boot application that initializes
 * the context and triggers the application to run.
 */
@SpringBootApplication
public class SpringBootCrudProduct {

    /**
     * Main method that runs the Spring Boot application.
     * 
     * @param args Command line arguments (not used in this case).
     */
    public static void main(String[] args) {
        // It returns ApplicationContext and starts the Spring Boot application
        SpringApplication.run(SpringBootCrudProduct.class, args);
    }
}
