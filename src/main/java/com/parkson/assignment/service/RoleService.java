package com.parkson.assignment.service;

import com.parkson.assignment.vo.request.RoleVo;

/** The interface Role service. */
public interface RoleService {

  /**
   * Add new role.
   *
   * @param roleVo the role validationObject
   */
  void addNewRole(RoleVo roleVo);
}
