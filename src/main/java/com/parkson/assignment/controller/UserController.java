package com.parkson.assignment.controller;

import com.parkson.assignment.service.UserService;
import com.parkson.assignment.utils.GenericResponse;
import com.parkson.assignment.vo.request.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/add")
  public GenericResponse createUser(@RequestBody UserVO userVO) {
    userService.save(userVO);
    return new GenericResponse("User created successfully");
  }

  @GetMapping("/fetch-all")
  public GenericResponse getAllUsers() {

    return new GenericResponse(userService.findAllUsers(), "Users fetched successfully.");
  }
}
