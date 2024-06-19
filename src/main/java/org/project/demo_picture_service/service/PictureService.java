package org.project.demo_picture_service.service;

import org.project.demo_picture_service.domain.picture.Picture;
import org.project.demo_picture_service.domain.picture.PictureImage;

import java.util.List;

public interface PictureService {

    Picture getById(
            Long id
    );

    List<Picture> getAllByUserId(
            Long id
    );

    Picture update(
            Picture picture
    );

    Picture create(
            Picture picture,
            Long userId
    );

    void delete(
            Long id
    );

    void uploadImage(
            Long id,
            PictureImage image
    );
}
