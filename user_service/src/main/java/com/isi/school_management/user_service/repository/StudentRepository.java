package com.isi.school_management.user_service.repository;

import com.isi.school_management.user_service.entity.StudentEntity;
import com.isi.school_management.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    boolean existsByUser(UserEntity user);
    Optional<StudentEntity> findByUser(UserEntity user);
}