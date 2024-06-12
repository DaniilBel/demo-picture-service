package org.project.demo_picture_service;

import org.project.demo_picture_service.model.Author;
import org.project.demo_picture_service.model.Picture;
import org.project.demo_picture_service.repository.AuthorRepository;
import org.project.demo_picture_service.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final PictureRepository pictureRepository;

    private static final Logger logger = Logger.getLogger(AuthorController.class.getName());

    @Autowired
    public AuthorController(AuthorRepository authorRepository, PictureRepository pictureRepository) {
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

//    @MutationMapping
//    Picture addPicture(@Argument PictureInput picture) {
//        logger.info(String.valueOf(picture.authorId()));
//        Author author = authorRepository.findById(picture.authorId()).orElseThrow(() -> new IllegalArgumentException("Author not found"));
//        Picture p = new Picture(picture.title(), picture.publisher(), author);
//
//        return pictureRepository.save(p);
//    }

    @MutationMapping
    Picture addPicture(@Argument String title, @Argument Long size, @Argument String url, @Argument Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Picture p = new Picture(title, size, url, author);
//        p.setTitle(title);
//        p.setSize(size);
//        p.setUrl(url);
//        p.setAuthor(new Author(authorId));

        return pictureRepository.save(p);
    }

    @MutationMapping
    Author addAuthor(@Argument String name) {
        return authorRepository.save(new Author(null, name));
    }
}
