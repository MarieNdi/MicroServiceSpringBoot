package com.isi.school_management.user_service.repository;

import com.isi.school_management.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository  extends JpaRepository<UserEntity,Long>{
}
