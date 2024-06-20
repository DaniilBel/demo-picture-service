package org.project.demo_picture_service.web.controller;

import lombok.RequiredArgsConstructor;
import org.project.demo_picture_service.domain.picture.Picture;
import org.project.demo_picture_service.domain.picture.PictureImage;
import org.project.demo_picture_service.service.PictureService;
import org.project.demo_picture_service.web.dto.picture.PictureDto;
import org.project.demo_picture_service.web.dto.picture.PictureImageDto;
import org.project.demo_picture_service.web.dto.validation.OnUpdate;
import org.project.demo_picture_service.web.mappers.PictureImageMapper;
import org.project.demo_picture_service.web.mappers.PictureMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pictures")
@RequiredArgsConstructor
@Validated
public class PictureController {

    private final PictureService pictureService;

    private final PictureMapper pictureMapper;
    private final PictureImageMapper pictureImageMapper;

    @PutMapping
    public PictureDto update(
            @Validated(OnUpdate.class)
            @RequestBody @Argument final PictureDto pictureDto
    ) {
        Picture picture = pictureMapper.toEntity(pictureDto);
        Picture updattedPicture = pictureService.update(picture);
        return pictureMapper.toDto(updattedPicture);
    }

    @GetMapping("/{id}")
    public PictureDto getById(
            @PathVariable final Long id
    ) {
        Picture picture = pictureService.getById(id);
        return pictureMapper.toDto(picture);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long id) {
        pictureService.delete(id);
    }

//    @PostMapping("/{id}/image")
//    public void uploadImage(
//            @PathVariable final Long id,
//            @Validated @ModelAttribute final PictureImageDto imageDto
//    ) {
//        PictureImage image = pictureImageMapper.toEntity(imageDto);
//        PictureService.uploadImage(id, image);
//    }
}
