package org.project.demo_picture_service.domain.picture;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PictureImage {

    private MultipartFile file;
}
