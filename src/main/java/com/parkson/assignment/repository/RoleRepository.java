package com.parkson.assignment.repository;

import com.parkson.assignment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
  List<Role> findAllByRoleNameIn(final List<String> name);
}
