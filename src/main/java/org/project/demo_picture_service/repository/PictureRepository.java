package org.project.demo_picture_service.repository;

import org.project.demo_picture_service.domain.Picture;
import org.springframework.data.repository.CrudRepository;

public interface PictureRepository extends CrudRepository<Picture, Long> {
}
