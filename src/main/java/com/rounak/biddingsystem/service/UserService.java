package com.rounak.biddingsystem.service;

import com.rounak.biddingsystem.entity.User;

public interface UserService {

  User findById(Long userId);

  User checkIfUserIsLoggedIn(Long userId);
}
