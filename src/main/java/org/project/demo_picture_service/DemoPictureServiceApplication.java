package org.project.demo_picture_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoPictureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoPictureServiceApplication.class, args);
    }
}
