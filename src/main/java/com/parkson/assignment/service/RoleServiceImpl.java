package com.parkson.assignment.service;

import com.parkson.assignment.model.Role;
import com.parkson.assignment.repository.RoleRepository;
import com.parkson.assignment.vo.request.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

  @Autowired
  private  RoleRepository roleRepository;

 /* @Autowired
  public RoleServiceImpl(final RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }
*/
  @Override
  @Transactional
  public void addNewRole(RoleVo roleVo) {
    Role role = new Role();
    role.setRoleName(roleVo.getName());
    role.setDescription(roleVo.getDescription());
    roleRepository.save(role);
  }
}
