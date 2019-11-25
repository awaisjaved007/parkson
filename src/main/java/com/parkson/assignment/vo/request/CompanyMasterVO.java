package com.parkson.assignment.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parkson.assignment.utils.LoggerUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/** The type Company master validation object. */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CompanyMasterVO {
  @NotBlank(message = "compCode must be provided.")
  @Size(max = 3, min = 3, message = "Company code length must be 3.")
  @Digits(integer = 3, fraction = 0, message = "Company code chars must be numeric.")
  private String compCode;

  @NotBlank(message = "Company Code HRIS must be provided.")
  @Pattern(regexp = "^[A-Z]+$", message = "Company Code HRIS invalid pattern provided.")
  @Size(max = 3, min = 3, message = "Company Code HRIS length must be 3.")
  private String compCodeHRIS;

  @Size(max = 70, message = "Company Name length must be less than 70.")
  private String compName;

  @Size(max = 15, message = "compAbbrName length must be less than 15")
  private String compAbbrName;

  @Size(max = 20, message = "compRegNo length must be less than 20")
  @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid pattern provided.")
  private String compRegNo;

  @Pattern(regexp = "([^\\s]+(\\.(?i)(jpeg|png))$)", message = "Image file must be .jpeg|.png")
  private String compLogo;


  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy hh:mm:ss aa")
  private Date compActiveDate;

  @NotNull(message = "isActive must be provided.")
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

  @Override
  public String toString() {
    return LoggerUtils.concat( "CompanyMasterVO{" ,
            "compCode='" + compCode + '\'' ,
            ", compCodeHRIS='" + compCodeHRIS + '\'' ,
            ", compName='" + compName + '\'' ,
            ", compAbbrName='" + compAbbrName + '\'' ,
            ", compRegNo='" + compRegNo + '\'' ,
            ", compLogo='" + compLogo + '\'' ,
            ", compActiveDate=" + compActiveDate ,
            ", isActive=" + isActive ,
            ", createdBy='" + createdBy + '\'' ,
            ", lastModifiedBy='" + lastModifiedBy + '\'' ,
            ", reactivatedBy='" + reactivatedBy + '\'' ,
            ", deactivatedBy='" + deactivatedBy + '\'' ,
            ", createdOn=" + createdOn ,
            ", lastModifiedOn=" + lastModifiedOn ,
            ", reactivatedOn=" + reactivatedOn ,
            ", deactivatedOn=" + deactivatedOn ,"}");
  }
}
