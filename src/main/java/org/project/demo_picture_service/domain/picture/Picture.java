package org.project.demo_picture_service.domain.picture;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pictures")
@Data
public class Picture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "image")
    @CollectionTable(name = "pictures_images")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> images;
}
