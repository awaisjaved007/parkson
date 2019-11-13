package com.parkson.assignment.controller;

import com.parkson.assignment.service.RoleService;
import com.parkson.assignment.utils.GenericResponse;
import com.parkson.assignment.vo.request.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

  private final RoleService roleService;

  @Autowired
  public RoleController(final RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping("/add")
  public GenericResponse addNewRole(@RequestBody RoleVo roleVo) {
    roleService.addNewRole(roleVo);
    return new GenericResponse("Role added successfully");
  }
}
