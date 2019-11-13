package com.parkson.assignment.controller;

import com.parkson.assignment.model.CompanyMaster;
import com.parkson.assignment.service.CompanyMasterService;
import com.parkson.assignment.utils.GenericResponse;
import com.parkson.assignment.vo.request.CompanyMasterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyMasterController {

  private final CompanyMasterService companyMasterService;
  private final MessageSource messageSource;

  @Autowired
  public CompanyMasterController(
          final CompanyMasterService companyMasterService, final MessageSource messageSource) {
    this.companyMasterService = companyMasterService;
    this.messageSource = messageSource;
  }

  @PostMapping("/add")
  public GenericResponse addCompanyMaster(
      @Valid @RequestBody final CompanyMasterVO companyMasterVO, final HttpServletRequest request) {
    this.companyMasterService.addCompanyMaster(companyMasterVO);
    return new GenericResponse(
        messageSource.getMessage("message.company.master.add.success", null, request.getLocale()));
  }

  @PutMapping("/update")
  public GenericResponse updateCompanyMaster(
      @Valid @RequestBody final CompanyMasterVO companyMasterVO, final HttpServletRequest request) {
    CompanyMaster companyMaster = this.companyMasterService.updateCompanyMaster(companyMasterVO);
    return new GenericResponse(
        companyMaster,
        messageSource.getMessage(
            "message.company.master.update.success", null, request.getLocale()));
  }

  @GetMapping("/fetch-all")
  public GenericResponse fetchAllCompanyMasters(
      final HttpServletRequest request, @RequestParam int from, @RequestParam int to) {
    request.getParameter("to");
    Page data = this.companyMasterService.fetchAllByPageNumber(from, to);
    return new GenericResponse(
        data,
        messageSource.getMessage(
            "message.company.master.data.fetch.success", null, request.getLocale()));
  }
}
