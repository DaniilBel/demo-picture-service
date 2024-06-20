package org.project.demo_picture_service.domain.picture;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PictureImage {

    private MultipartFile file;
}
