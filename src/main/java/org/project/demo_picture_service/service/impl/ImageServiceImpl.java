package org.project.demo_picture_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.demo_picture_service.domain.picture.PictureImage;
import org.project.demo_picture_service.service.ImageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public String upload(final PictureImage image) {
        return "";
    }
}
