package org.project.demo_picture_service;

import org.project.demo_picture_service.domain.user.MongoUser;
import org.project.demo_picture_service.repository.MongoUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class DemoPictureServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoPictureServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(final MongoUserRepository repository) {
        return args -> {
            MongoUser mongoUser = new MongoUser(
                    "James",
                    "Mongist"
            );

            repository.insert(mongoUser);
        };
    }

}
