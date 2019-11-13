package com.parkson.assignment.vo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties
@Data
public class UserVO {
  private String userName;
  private String password;
  private String firstName;
  private String lastName;
  private List<String> roles;
}
