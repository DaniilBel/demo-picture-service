package org.project.demo_picture_service.domain.picture;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pictures")
@Getter
@Setter
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @Column(name = "image")
    @CollectionTable(name = "pictures_images")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> images;
}
