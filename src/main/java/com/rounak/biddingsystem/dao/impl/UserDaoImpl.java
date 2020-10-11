package com.rounak.biddingsystem.dao.impl;

import com.rounak.biddingsystem.dao.UserDao;
import com.rounak.biddingsystem.entity.User;
import com.rounak.biddingsystem.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

  private final UserRepository userRepository;

  @Override
  public Optional<User> findById(Long userId) {
    return userRepository.findById(userId);
  }
}
