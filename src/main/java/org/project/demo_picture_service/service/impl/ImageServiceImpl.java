package org.project.demo_picture_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.demo_picture_service.domain.picture.PictureImage;
import org.project.demo_picture_service.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public String upload(PictureImage image) {
        return "";
    }
}
