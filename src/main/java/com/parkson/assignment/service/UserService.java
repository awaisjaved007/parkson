package com.parkson.assignment.service;

import com.parkson.assignment.model.User;
import com.parkson.assignment.vo.request.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  void save(UserVO userVO);

  User findByUsername(final String username);

  List<User> findAllUsers();

  boolean login(final String username, final String password);
}
