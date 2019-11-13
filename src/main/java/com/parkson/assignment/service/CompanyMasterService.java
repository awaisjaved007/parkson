package com.parkson.assignment.service;

import com.parkson.assignment.model.CompanyMaster;
import com.parkson.assignment.vo.request.CompanyMasterVO;
import org.springframework.data.domain.Page;

public interface CompanyMasterService {

  void addCompanyMaster(final CompanyMasterVO companyMasterVO);

  CompanyMaster updateCompanyMaster(final CompanyMasterVO companyMasterVO);

  Page<CompanyMaster> fetchAllByPageNumber(Integer from,Integer to);
}
