package org.project.demo_picture_service.web.controller;

import lombok.RequiredArgsConstructor;
import org.project.demo_picture_service.domain.picture.Picture;
import org.project.demo_picture_service.domain.user.User;
import org.project.demo_picture_service.service.PictureService;
import org.project.demo_picture_service.service.UserService;
import org.project.demo_picture_service.web.dto.picture.PictureDto;
import org.project.demo_picture_service.web.dto.user.UserDto;
import org.project.demo_picture_service.web.dto.validation.OnCreate;
import org.project.demo_picture_service.web.mappers.PictureMapper;
import org.project.demo_picture_service.web.mappers.UserMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final PictureService pictureService;

    private final UserMapper userMapper;
    private final PictureMapper pictureMapper;

    @PutMapping
    @MutationMapping(name = "updateUser")
    public UserDto update(
            @Validated(OnCreate.class)
            @RequestBody @Argument final UserDto dto
    ) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @QueryMapping(name = "userById")
    public UserDto getById(@PathVariable @Argument final Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @MutationMapping(name = "deleteUserById")
    public void deleteById(@PathVariable @Argument final Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/pictures")
    @QueryMapping(name = "picturesByUserId")
    public List<PictureDto> getPicturesByUserId(@PathVariable @Argument final Long id) {
        List<Picture> pictures = pictureService.getAllByUserId(id);
        return pictureMapper.toDto(pictures);
    }

    @PostMapping("/{id}/pictures")
    @MutationMapping(name = "createPicture")
    public PictureDto createPicture(
            @PathVariable @Argument final Long id,
            @Validated(OnCreate.class)
            @RequestBody @Argument final PictureDto dto
    ) {
        Picture picture = pictureMapper.toEntity(dto);
        Picture createdPicture = pictureService.create(picture, id);
        return pictureMapper.toDto(createdPicture);
    }
}
