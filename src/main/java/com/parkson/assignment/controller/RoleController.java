package com.parkson.assignment.controller;

import com.parkson.assignment.service.RoleService;
import com.parkson.assignment.utils.GenericResponse;
import com.parkson.assignment.vo.request.RoleVo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Role controller.
 *  VO are validation objects
 * */
@RestController
@RequestMapping("/role")
public class RoleController {

  @Qualifier("roleServiceImpl")
  private RoleService roleService;

  /**
   * Add new role generic response.
   *
   * @param roleVo the role vo
   * @return the generic response
   */
  @PostMapping("/add")
  public GenericResponse addNewRole(@RequestBody RoleVo roleVo) {
    roleService.addNewRole(roleVo);
    return new GenericResponse("Role added successfully");
  }
}
