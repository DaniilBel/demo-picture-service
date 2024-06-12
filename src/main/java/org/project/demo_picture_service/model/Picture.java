package org.project.demo_picture_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Picture {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long size;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    public Picture(String title, Long size, String url, Author author) {
        this.title = title;
        this.size = size;
        this.url = url;
        this.author = author;
    }
}
