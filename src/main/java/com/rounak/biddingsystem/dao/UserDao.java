package com.rounak.biddingsystem.dao;

import com.rounak.biddingsystem.entity.User;
import java.util.Optional;

public interface UserDao {

  Optional<User> findById(Long userId);

}
