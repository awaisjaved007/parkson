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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/company")
public class CompanyMasterController {

  @Autowired private CompanyMasterService companyMasterService;

  @Autowired private MessageSource messageSource;

  @InitBinder
  public final void initBinderUsuariosFormValidator(
      final WebDataBinder binder, final Locale locale) {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }

  @PostMapping("/add")
  public String addCompanyMaster(
      @Valid @ModelAttribute("companyMasterVO") CompanyMasterVO companyMasterVO,
      BindingResult result,
      Model model,
      HttpServletRequest request,
      RedirectAttributes redirectAttrs) {
    if (result.hasErrors()) {
      model.addAttribute("error", true);
      return "dashboard";
    } else {
      companyMasterVO.setCreatedBy(request.getUserPrincipal().getName());
      this.companyMasterService.addCompanyMaster(companyMasterVO);
      model.addAttribute("companyMasterVO", companyMasterVO);
      redirectAttrs
          .addAttribute("id", companyMasterVO.getCompName())
          .addFlashAttribute("message", "Company created!");
      model.addAttribute("data", this.companyMasterService.fetchAllByPageNumber(1, 5));
      return "dashboard";
    }
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
  public String fetchAllCompanyMasters(
      @RequestParam int from, @RequestParam int size, Model model) {
    Page data = this.companyMasterService.fetchAllByPageNumber(from, size);
    model.addAttribute("data", data);
    return "dashboard";
  }
}
