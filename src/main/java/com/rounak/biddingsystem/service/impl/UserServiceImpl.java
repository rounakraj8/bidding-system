package com.rounak.biddingsystem.service.impl;

import com.rounak.biddingsystem.dao.UserDao;
import com.rounak.biddingsystem.entity.User;
import com.rounak.biddingsystem.enums.UserStatus;
import com.rounak.biddingsystem.exception.UserNotFoundException;
import com.rounak.biddingsystem.exception.UserNotLoggedInException;
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

  @Override
  public User checkIfUserIsLoggedIn(Long userId) {
    User user = findById(userId);
    if (UserStatus.getUserStatus(user.getStatus()) == UserStatus.LOGGED_OUT) {
      throw new UserNotLoggedInException();
    }
    return user;
  }
}
