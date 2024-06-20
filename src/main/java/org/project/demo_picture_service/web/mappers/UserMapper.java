package org.project.demo_picture_service.web.mappers;

import org.mapstruct.Mapper;
import org.project.demo_picture_service.domain.user.User;
import org.project.demo_picture_service.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
