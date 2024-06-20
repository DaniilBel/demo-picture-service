package org.project.demo_picture_service.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class MongoUser {
    @Id
    private String id;

    private String name;
    private String username;

    public MongoUser(String name, String username) {
        this.name = name;
        this.username = username;
    }
}
