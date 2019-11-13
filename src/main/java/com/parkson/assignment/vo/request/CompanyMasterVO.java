package com.parkson.assignment.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CompanyMasterVO {
  @NotBlank(message = "compCode must be provided.")
  @Size(max = 3, min = 3, message = "compCode length must be 3.")
  @Digits(integer = 3, fraction = 0, message = "compCode chars must be numeric.")
  private String compCode;

  @NotBlank(message = "compCodeHRIS must be provided.")
  @Pattern(regexp = "^[A-Z]+$", message = "Invalid pattern provided.")
  @Size(max = 3, min = 3, message = "compCodeHRIS length must be 3.")
  private String compCodeHRIS;

  @Size(max = 70, message = "compName length must be less than 70.")
  private String compName;

  @Size(max = 15, message = "compAbbrName length must be less than 15")
  private String compAbbrName;

  @Size(max = 20, message = "compRegNo length must be less than 20")
  @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid pattern provided.")
  private String compRegNo;

  @Pattern(regexp = "([^\\s]+(\\.(?i)(jpeg|png))$)", message = "Image file must be .jpeg|.png")
  private String compLogo;

  @Column(name = "mf11_compActiveDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  private Date compActiveDate;

  @NotBlank(message = "isActive must be provided.")
  @Column(name = "mf11_isActive", nullable = false)
  private boolean isActive;

  private String createdBy;

  private String lastModifiedBy;

  private String reactivatedBy;

  private String deactivatedBy;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  private Date createdOn;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  private Date lastModifiedOn;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  private Date reactivatedOn;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  private Date deactivatedOn;
}
