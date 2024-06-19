package org.project.demo_picture_service.repository;

import org.project.demo_picture_service.domain.picture.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query(value = """
        SELECT * FROM pictures p
        JOIN users_pictures up ON up.picture_id = p.id
        WHERE up.user_id = :userId
    """, nativeQuery = true)
    List<Picture> findAllByUserId(
            @Param("userId") Long userId
    );

    @Modifying
    @Query(value = """
            INSERT INTO users_pictures (user_id, picture_id)
            VALUES (:userId, :pictureId)
            """, nativeQuery = true)
    void assignTask(
            @Param("userId") Long userId,
            @Param("pictureId") Long pictureId
    );
}
