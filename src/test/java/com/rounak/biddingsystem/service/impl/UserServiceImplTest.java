package com.rounak.biddingsystem.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.rounak.biddingsystem.dao.UserDao;
import com.rounak.biddingsystem.entity.User;
import com.rounak.biddingsystem.enums.UserStatus;
import com.rounak.biddingsystem.exception.UserNotFoundException;
import com.rounak.biddingsystem.exception.UserNotLoggedInException;
import com.rounak.biddingsystem.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserDao userDao;

  @Test
  void findById_userLoggedIn() {
    when(userDao.findById(1L)).thenReturn(Optional.of(new User(1L, "USER1", UserStatus.LOGGED_IN
        .name())));
    User user = userService.findById(1L);
    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getName()).isEqualTo("USER1");
    assertThat(user.getStatus()).isEqualTo(UserStatus.LOGGED_IN.name());
  }

  @Test
  void findById_userLoggedOut() {
    when(userDao.findById(1L)).thenReturn(Optional.of(new User(1L, "USER2", UserStatus.LOGGED_OUT
        .name())));
    User user = userService.findById(1L);
    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getName()).isEqualTo("USER2");
    assertThat(user.getStatus()).isEqualTo(UserStatus.LOGGED_OUT.name());
  }

  @Test
  void findById_userNotPresent() {
    when(userDao.findById(1L)).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
  }

  @Test
  void checkIfUserIsLoggedIn_userLoggedIn() {
    when(userDao.findById(1L)).thenReturn(Optional.of(new User(1L, "USER1", UserStatus.LOGGED_IN
        .name())));
    User user = userService.checkIfUserIsLoggedIn(1L);
    assertThat(user.getStatus()).isEqualTo(UserStatus.LOGGED_IN.name());
  }

  @Test
  void checkIfUserIsLoggedIn_userLoggedOut() {
    when(userDao.findById(1L)).thenReturn(Optional.of(new User(1L, "USER1", UserStatus.LOGGED_OUT
        .name())));
    assertThrows(UserNotLoggedInException.class, () -> userService.checkIfUserIsLoggedIn(1L));
  }
}