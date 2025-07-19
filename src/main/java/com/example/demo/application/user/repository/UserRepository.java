package com.example.demo.application.user.repository;

import com.example.demo.application.user.dao.UserDao;
import com.example.demo.application.user.entity.UserEntity;
import com.example.demo.application.user.mapper.UserMapper;
import com.example.demo.application.user.model.UserModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  private final UserDao userDao;
  private final UserMapper userMapper;

  public UserRepository(UserDao userDao, UserMapper userMapper) {
    this.userDao = userDao;
    this.userMapper = userMapper;
  }

  public Optional<UserModel> findById(UUID id) {
    Optional<UserEntity> user = userDao.findById(id);

    if (user.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(userMapper.mapToUserModel(user.get()));
  }

  public Optional<UserModel> findByPhone(String email) {
    Optional<UserEntity> user = userDao.findByPhone(email);

    if (user.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(userMapper.mapToUserModel(user.get()));
  }

  public void save(UserModel user) {
    UserEntity entity = userMapper.mapToUserEntity(user);

    userDao.save(entity);
  }
}
