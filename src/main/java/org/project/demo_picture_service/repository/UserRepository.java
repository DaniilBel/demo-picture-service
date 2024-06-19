package org.project.demo_picture_service.repository;

import org.project.demo_picture_service.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(
            String username
    );

}
