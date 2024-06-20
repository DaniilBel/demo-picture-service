package org.project.demo_picture_service.repository;

import org.project.demo_picture_service.domain.user.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<MongoUser, String> {
}
