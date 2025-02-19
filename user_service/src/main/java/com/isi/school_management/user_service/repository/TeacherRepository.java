package com.isi.school_management.user_service.repository;


import com.isi.school_management.user_service.entity.TeacherEntity;
import com.isi.school_management.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    boolean existsByUser(UserEntity user);
    Optional<TeacherEntity> findByUser(UserEntity user);
}