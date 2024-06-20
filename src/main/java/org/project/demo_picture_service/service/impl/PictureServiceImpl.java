package org.project.demo_picture_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.demo_picture_service.domain.exception.ResourceNotFoundException;
import org.project.demo_picture_service.domain.picture.Picture;
import org.project.demo_picture_service.domain.picture.PictureImage;
import org.project.demo_picture_service.repository.PictureRepository;
import org.project.demo_picture_service.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Override
    public Picture getById(final Long id) {
        return pictureRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Picture not found with id " + id));
    }

    @Override
    public List<Picture> getAllByUserId(final Long id) {
        return pictureRepository.findAllByUserId(id);
    }

    @Override
    @Transactional
    public Picture update(final Picture picture) {
        Picture existing = getById(picture.getId());
        existing.setTitle(picture.getTitle());
        existing.setDescription(picture.getDescription());
        pictureRepository.save(picture);
        return picture;
    }

    @Override
    public Picture create(
            final Picture picture,
            final Long userId
    ) {
        pictureRepository.save(picture);
        pictureRepository.assignPicture(userId, picture.getId());
        return picture;
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void uploadImage(
            final Long id,
            final PictureImage image
    ) {

    }
}
