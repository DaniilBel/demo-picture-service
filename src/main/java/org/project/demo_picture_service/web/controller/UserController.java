package org.project.demo_picture_service.web.controller;

import org.project.demo_picture_service.domain.Author;
import org.project.demo_picture_service.domain.Picture;
import org.project.demo_picture_service.repository.AuthorRepository;
import org.project.demo_picture_service.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {
    private final AuthorRepository authorRepository;
    private final PictureRepository pictureRepository;

    @Autowired
    public UserController(AuthorRepository authorRepository, PictureRepository pictureRepository) {
        this.authorRepository = authorRepository;
        this.pictureRepository = pictureRepository;
    }

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument Long id) {
        return authorRepository.findById(id);
    }

    @MutationMapping
    Picture addPicture(@Argument String title, @Argument Long size, @Argument String url, @Argument Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Picture p = new Picture(title, size, url, author);

        return pictureRepository.save(p);
    }

    @MutationMapping
    Author addAuthor(@Argument String name) {
        return authorRepository.save(new Author(null, name));
    }
}
