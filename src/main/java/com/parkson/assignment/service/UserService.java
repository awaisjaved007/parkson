package com.parkson.assignment.service;

import com.parkson.assignment.model.User;
import com.parkson.assignment.vo.request.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/** The interface User service. */
public interface UserService extends UserDetailsService {

  /**
   * Save.
   *
   * @param userVO the user vo
   */
  void save(UserVO userVO);

  /**
   * Find by username user.
   *
   * @param username the username
   * @return the user
   */
  User findByUsername(final String username);

  /**
   * Find all users list.
   *
   * @return the list
   */
  List<User> findAllUsers();

  /**
   * Login boolean.
   *
   * @param username the username
   * @param password the password
   * @return the boolean
   */
  boolean login(final String username, final String password);
}
