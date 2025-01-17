package com.parkson.assignment.controller;

import com.parkson.assignment.model.CompanyMaster;
import com.parkson.assignment.service.CompanyMasterService;
import com.parkson.assignment.utils.GenericResponse;
import com.parkson.assignment.vo.request.CompanyMasterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** The type Company master controller. */
@Controller
@RequestMapping("/company")
public class CompanyMasterController {

  @Autowired private CompanyMasterService companyMasterService;

  @Autowired private MessageSource messageSource;

  /**
   * Init binder usuarios form validator.
   *
   * @param binder the binder
   * @param locale the locale
   */
  @InitBinder
  public final void initBinderUsuariosFormValidator(
      final WebDataBinder binder, final Locale locale) {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }

  /**
   * Add company master string.
   *
   * @param companyMasterVO the company master vo
   * @param result the result
   * @param model the model
   * @param request the request
   * @return the string
   */
  @PostMapping("/add")
  public String addCompanyMaster(
      @Valid @ModelAttribute("companyMasterVO") CompanyMasterVO companyMasterVO,
      BindingResult result,
      Model model,
      HttpServletRequest request) {
    if (result.hasErrors()) {
      model.addAttribute("error", true);
    } else {
      companyMasterVO.setCreatedBy(request.getUserPrincipal().getName());
      this.companyMasterService.addCompanyMaster(companyMasterVO);
      prepareModel(model, 0, 5);
    }
    return "dashboard";
  }

  /**
   * Update company master generic response.
   *
   * @param companyMasterVO the company master vo
   * @param request the request
   * @return the generic response
   */
  @PutMapping("/update")
  public GenericResponse updateCompanyMaster(
      @Valid @RequestBody final CompanyMasterVO companyMasterVO, final HttpServletRequest request) {
    CompanyMaster companyMaster = this.companyMasterService.updateCompanyMaster(companyMasterVO);
    return new GenericResponse(
        companyMaster,
        messageSource.getMessage(
            "message.company.master.update.success", null, request.getLocale()));
  }

  /**
   * Fetch all company masters string.
   *
   * @param from the from
   * @param pageSize the page size
   * @param model the model
   * @return the string
   */
  @GetMapping("/fetch/{pageNumber}/{pageSize}")
  public String fetchAllCompanyMasters(
      @PathVariable("pageNumber") int from, @PathVariable("pageSize") int pageSize, Model model) {
    prepareModel(model, from, pageSize);
    return "dashboard";
  }

  /**
   * Refresh form string.
   *
   * @param model the model
   * @return the string
   */
  @GetMapping("/refresh-form")
  public String refreshForm(Model model) {
    model.addAttribute("companyMasterVO", new CompanyMasterVO());
    model.addAttribute("error", true);
    return "dashboard";
  }

  /**
   * Back refresh string.
   *
   * @param model the model
   * @return the string
   */
  @GetMapping("/back-refresh")
  public String backRefresh(Model model) {
    prepareModel(model, 0, 5);
    return "dashboard";
  }

  private Model prepareModel(Model model, int number, int size) {
    model.addAttribute("companyMasterVO", new CompanyMasterVO());
    model.addAttribute("error", false);
    Page data = this.companyMasterService.fetchAllByPageNumber(number, size);
    model.addAttribute("data", data);
    return model;
  }
}
