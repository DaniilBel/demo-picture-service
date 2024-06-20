package org.project.demo_picture_service.web.dto.picture;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PictureImageDto {

    @NotNull(message = "Image must be not null")
    private MultipartFile file;
}
