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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public UserDto update(
            @Validated(OnCreate.class)
            @RequestBody final UserDto userDto
    ) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable final Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/pictures")
    public List<PictureDto> getPicturesByUserId(@PathVariable final Long id) {
        List<Picture> pictures = pictureService.getAllByUserId(id);
        return pictureMapper.toDto(pictures);
    }

    @PostMapping("/{id}/pictures")
    public PictureDto createPicture(
            @PathVariable final Long id,
            @Validated(OnCreate.class)
            @RequestBody final PictureDto dto
    ) {
        Picture picture = pictureMapper.toEntity(dto);
        Picture createdPicture = pictureService.create(picture, id);
        return pictureMapper.toDto(createdPicture);
    }
}
