package com.parkson.assignment.controller;

import com.parkson.assignment.service.CompanyMasterService;
import com.parkson.assignment.service.UserService;
import com.parkson.assignment.utils.GenericResponse;
import com.parkson.assignment.vo.request.CompanyMasterVO;
import com.parkson.assignment.vo.request.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

  @Qualifier("userServiceImpl")
  private UserService userService;

  @Autowired private CompanyMasterService companyMasterService;

  @PostMapping("/add")
  public GenericResponse createUser(@RequestBody UserVO userVO) {
    userService.save(userVO);
    return new GenericResponse("User created successfully");
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String registerUser(@ModelAttribute("user") UserVO user) {
    userService.save(user);
    return "/login";
  }

  @GetMapping("/fetch-all")
  public GenericResponse getAllUsers() {

    return new GenericResponse(this.userService.findAllUsers(), "Users fetched successfully.");
  }

  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
  public String dashboard(Model model) {
    model.addAttribute("companyMasterVO", new CompanyMasterVO());
    model.addAttribute("error", false);
    model.addAttribute("data", companyMasterService.fetchAllByPageNumber(0, 5));
    return "dashboard";
  }
}
