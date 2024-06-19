package org.project.demo_picture_service.web.mappers;

import org.mapstruct.Mapper;
import org.project.demo_picture_service.domain.picture.PictureImage;
import org.project.demo_picture_service.web.dto.picture.PictureImageDto;

@Mapper(componentModel = "spring")
public interface PictureImageMapper extends Mappable<PictureImage, PictureImageDto> {
}
