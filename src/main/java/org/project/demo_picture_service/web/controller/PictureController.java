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
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequestMapping("/api/v1/pictures")
@RequiredArgsConstructor
@Validated
public class PictureController {

    private final PictureService pictureService;

    private final PictureMapper pictureMapper;
    private final PictureImageMapper pictureImageMapper;

    @PutMapping
    @MutationMapping(name = "updatePicture")
    @PreAuthorize("canAccessPicture(#dto.id)")
    public PictureDto update(
            @Validated(OnUpdate.class)
            @RequestBody @Argument final PictureDto dto
    ) {
        Picture picture = pictureMapper.toEntity(dto);
        Picture updattedPicture = pictureService.update(picture);
        return pictureMapper.toDto(updattedPicture);
    }

    @GetMapping("/{id}")
    @QueryMapping(name = "pictureById")
//    @PreAuthorize("canAccessPicture(#id)")
    public PictureDto getById(
            @PathVariable @Argument final Long id
    ) {
        Picture picture = pictureService.getById(id);
        return pictureMapper.toDto(picture);
    }

    @DeleteMapping("/{id}")
    @MutationMapping(name = "deletePicture")
//    @PreAuthorize("canAccessPicture(#id)")
    public void deleteById(@PathVariable @Argument final Long id) {
        pictureService.delete(id);
    }

    @PostMapping("/{id}/image")
//    @PreAuthorize("canAccessPicture(#id)")
    public void uploadImage(
            @PathVariable final Long id,
            @Validated @ModelAttribute final PictureImageDto imageDto
    ) {
        PictureImage image = pictureImageMapper.toEntity(imageDto);
        pictureService.uploadImage(id, image);
    }
}
