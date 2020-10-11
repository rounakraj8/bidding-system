package com.rounak.biddingsystem.service.impl;

import com.rounak.biddingsystem.dao.UserDao;
import com.rounak.biddingsystem.entity.User;
import com.rounak.biddingsystem.exception.UserNotFoundException;
import com.rounak.biddingsystem.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  @Override
  public User findById(Long userId) {
    Optional<User> user = userDao.findById(userId);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new UserNotFoundException(userId);
    }
  }
}
