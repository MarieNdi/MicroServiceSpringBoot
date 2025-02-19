package com.isi.school_management.user_service.repository;

import com.isi.school_management.user_service.entity.AdministrativeAgentEntity;
import com.isi.school_management.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrativeAgentRepository extends JpaRepository<AdministrativeAgentEntity, Long> {
    boolean existsByUser(UserEntity user);
    Optional<AdministrativeAgentEntity> findByUser(UserEntity user);
}