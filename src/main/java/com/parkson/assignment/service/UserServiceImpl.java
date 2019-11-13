package com.parkson.assignment.service;

import com.parkson.assignment.model.User;
import com.parkson.assignment.repository.RoleRepository;
import com.parkson.assignment.repository.UserRepository;
import com.parkson.assignment.vo.request.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Transactional
  @Override
  public void save(UserVO user) {
    User newUser = new User();
    newUser.setUsername(user.getUserName());
    newUser.setFirstName(user.getFirstName());
    newUser.setLastName(user.getLastName());
    newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    newUser.setRoles(roleRepository.findAllByRoleNameIn(user.getRoles()));
    userRepository.save(newUser);
  }

  @Override
  public User findByUsername(String username) {
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> findAllUsers() {
    return this.userRepository.findAll();
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(s);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
    }

    List<GrantedAuthority> authorities = new ArrayList<>();
    user.getRoles()
        .forEach(
            role -> {
              authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            });

    UserDetails userDetails =
        new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(), authorities);

    return userDetails;
  }
}
