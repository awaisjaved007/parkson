package com.parkson.assignment.service;

import com.parkson.assignment.model.User;
import com.parkson.assignment.vo.request.UserVO;

import java.util.List;

public interface UserService {

  void save(UserVO userVO);

  User findByUsername(String username);

  List<User> findAllUsers();
}
