package org.project.demo_picture_service.service;

import org.project.demo_picture_service.domain.user.User;

public interface UserService {

    User getById(
            Long id
    );

    User getByUsername(
            String username
    );

    User update(
            User user
    );

    User create(
            User user
    );

    boolean isPictureOwner(
            Long userId,
            Long pictureId
    );

    void delete(
            Long id
    );
}
