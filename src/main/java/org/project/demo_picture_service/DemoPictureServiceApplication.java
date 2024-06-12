package org.project.demo_picture_service;

import org.project.demo_picture_service.model.Author;
import org.project.demo_picture_service.model.Picture;
import org.project.demo_picture_service.repository.AuthorRepository;
import org.project.demo_picture_service.repository.PictureRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class DemoPictureServiceApplication {

    private static final Logger logger = Logger.getLogger(DemoPictureServiceApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(DemoPictureServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(AuthorRepository authorRepository, PictureRepository pictureRepository) {
        return args -> {
            Author josh = authorRepository.save(new Author(null, "Josh"));
            Author mark = authorRepository.save(new Author(null, "Mark"));
            pictureRepository.saveAll(List.of(
                    new Picture("Reactive Spring", 5000L, "/", josh),
                    new Picture("Spring", 5000L, "/", mark),
                    new Picture("Postgre", 6000L, "/", josh)
            ));
            logger.info("Authors found: " + authorRepository.count());
        };
    }
}
