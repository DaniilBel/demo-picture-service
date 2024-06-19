package org.project.demo_picture_service.repository;

import org.project.demo_picture_service.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<User, Long> {
}
