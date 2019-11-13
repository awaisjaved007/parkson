package com.parkson.assignment.vo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class RoleVo {
  private String name;
  private String description;
}
