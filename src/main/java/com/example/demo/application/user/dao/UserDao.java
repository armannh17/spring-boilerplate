package com.example.demo.application.user.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.application.user.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, UUID> {
	Optional<UserEntity> findByPhone(String phone);
}
