package com.parkson.assignment.service;

import com.parkson.assignment.vo.request.RoleVo;
import com.parkson.assignment.vo.request.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** The type Init model service. Purpose: To create default User and Role */
@Component
public class InitModelService implements ApplicationListener<ApplicationReadyEvent> {

  private final UserService userService;

  private final RoleService roleService;

  /**
   * Instantiates a new Init model service.
   *
   * @param userService the user service
   * @param roleService the role service
   */
  @Autowired
  public InitModelService(final UserService userService, final RoleService roleService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    seedData();
  }

  /*
  *  adding objects for Role and User*/
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
    if (userService.findByUsername("javedm") == null) {
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
}
