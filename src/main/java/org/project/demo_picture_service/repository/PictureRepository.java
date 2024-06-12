package org.project.demo_picture_service.repository;

import org.project.demo_picture_service.model.Picture;
import org.springframework.data.repository.CrudRepository;

public interface PictureRepository extends CrudRepository<Picture, Long> {
}
