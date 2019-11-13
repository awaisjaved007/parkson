package com.parkson.assignment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(
    name = "MF11_M_COMPMAST",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "unq_mf11_compCode",
          columnNames = {"mf11_compCode"}),
      @UniqueConstraint(
          name = "unq_mf11_compCodeHRIS",
          columnNames = {"mf11_compCodeHRIS"})
    },
    indexes = {
      @Index(columnList = "mf11_compCode", name = "index_mf11_compCode"),
      @Index(columnList = "mf11_compCodeHRIS", name = "index_mf11_compCodeHRIS")
    })
public class CompanyMaster {

  @Id
  @NotBlank(message = "compCode must be provided.")
  @Size(max = 3, min = 3, message = "compCode length must be 3.")
  @Digits(integer = 3, fraction = 0, message = "compCode chars must be numeric.")
  @Column(name = "mf11_compCode", length = 3)
  private String compCode;

  @NotBlank(message = "compCodeHRIS must be provided.")
  @Pattern(regexp = "^[A-Z]+$", message = "Invalid pattern provided.")
  @Size(max = 3, min = 3, message = "compCodeHRIS length must be 3.")
  @Column(name = "mf11_compCodeHRIS", length = 3)
  private String compCodeHRIS;

  @Size(max = 70, message = "compName length must be less than 70.")
  @Column(name = "mf11_compName", length = 70)
  private String compName;

  @Size(max = 15, message = "compAbbrName length must be less than 15")
  @Column(name = "mf11_compAbbrName", length = 15)
  private String compAbbrName;

  @Size(max = 20, message = "compRegNo length must be less than 20")
  @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid pattern provided.")
  @Column(name = "mf11_compRegNo")
  private String compRegNo;

  @Column(name = "mf11_compLogo")
  @Pattern(regexp = "([^\\s]+(\\.(?i)(jpeg|png))$)", message = "Image file must be .jpeg|.png")
  private String compLogo;

  @Column(name = "mf11_compActiveDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  private Date compActiveDate;

  @NotBlank(message = "isActive must be provided.")
  @Column(name = "mf11_isActive", nullable = false)
  private boolean isActive;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  @Column(name = "mf11_createdOn", nullable = false)
  private Date createdOn;

  @NotBlank(message = "createdBy must be provided.")
  @Size(max = 255, message = "createdBy size must be less than 255 chars.")
  @Column(name = "mf11_createdBy", nullable = false)
  private String createdBy;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  @Column(name = "mf11_lastModifiedOn")
  private Date lastModifiedOn;

  @Size(max = 255, message = "lastModifiedBy size must be less than 255 chars.")
  @Column(name = "mf11_lastModifiedBy")
  private String lastModifiedBy;

  @Size(max = 255, message = "deactivatedBy size must be less than 255 chars.")
  @Column(name = "mf11_deactivatedBy")
  private String deactivatedBy;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  @Column(name = "deactivatedOn")
  private Date deactivatedOn;

  @Size(max = 255, message = "reactivatedBy size must be less than 255 chars.")
  @Column(name = "mf11_reactivatedBy")
  private String reactivatedBy;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  @Column(name = "mf11_reactivatedOn")
  private Date reactivatedOn;
}
