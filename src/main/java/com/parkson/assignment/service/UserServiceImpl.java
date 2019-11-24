package com.parkson.assignment.service;

import com.parkson.assignment.model.User;
import com.parkson.assignment.repository.RoleRepository;
import com.parkson.assignment.repository.UserRepository;
import com.parkson.assignment.vo.request.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/** The UserService implementation. */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  /** The Authentication manager. */
  @Autowired AuthenticationManager authenticationManager;

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
    return this.userRepository.findByUsername(username);
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

  @Override
  public boolean login(String username, String password) {

    UserDetails userDetails = this.loadUserByUsername(username);

    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(
            userDetails, password, userDetails.getAuthorities());

    authenticationManager.authenticate(token);
    boolean authenticated = token.isAuthenticated();
    if (authenticated) {
      SecurityContextHolder.getContext().setAuthentication(token);
    }

    return authenticated;
  }
}
