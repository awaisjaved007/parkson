package com.parkson.assignment.service;

import com.parkson.assignment.vo.request.RoleVo;
import com.parkson.assignment.vo.request.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class InitModelService implements ApplicationListener<ApplicationReadyEvent> {

  private final UserService userService;

  private final RoleService roleService;

  @Autowired
  public InitModelService(final UserService userService, final RoleService roleService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    seedData();
  }

  private void seedData() {

    RoleVo admin = new RoleVo();
    admin.setName("ADMIN");
    admin.setDescription("This is admin.");

    RoleVo manager = new RoleVo();
    manager.setName("MANAGER");
    manager.setDescription("This is manager.");

    roleService.addNewRole(admin);
    roleService.addNewRole(manager);

    UserVO userVO = new UserVO();
    userVO.setFirstName("Awais");
    userVO.setLastName("Javed");
    userVO.setUserName("javedm");
    userVO.setPassword("password");
    List<String> roles = new ArrayList<>();
    roles.add("ADMIN");
    userVO.setRoles(roles);

    userService.save(userVO);
  }
}
