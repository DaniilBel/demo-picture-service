package org.project.demo_picture_service.web.mappers;

import org.mapstruct.Mapper;
import org.project.demo_picture_service.domain.picture.Picture;
import org.project.demo_picture_service.web.dto.picture.PictureDto;

@Mapper(componentModel = "spring")
public interface PictureMapper extends Mappable<Picture, PictureDto> {
}
