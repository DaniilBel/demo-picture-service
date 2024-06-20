package org.project.demo_picture_service.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.project.demo_picture_service.domain.picture.Picture;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }
}
