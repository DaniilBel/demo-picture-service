package org.project.demo_picture_service.service;

import org.project.demo_picture_service.domain.picture.PictureImage;

public interface ImageService {

    String upload(
            PictureImage image
    );
}
