package org.project.demo_picture_service.web.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.project.demo_picture_service.web.dto.validation.OnCreate;
import org.project.demo_picture_service.web.dto.validation.OnUpdate;

@Data
public class UserDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null",
            groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255,
            message = "Name length must be smaller than 255 sym",
            groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Username must be not null",
            groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255,
            message = "Username length must be smaller than 255 sym",
            groups = {OnCreate.class, OnUpdate.class})
    private String username;
}