package com.parkson.assignment.service;

import com.parkson.assignment.model.CompanyMaster;
import com.parkson.assignment.vo.request.CompanyMasterVO;
import org.springframework.data.domain.Page;

/** The interface Company master service. */
public interface CompanyMasterService {

  /**
   * Add company master.
   *
   * @param companyMasterVO the company master vo
   */
  void addCompanyMaster(final CompanyMasterVO companyMasterVO);

  /**
   * Update company master company master.
   *
   * @param companyMasterVO the company master vo
   * @return the company master
   */
  CompanyMaster updateCompanyMaster(final CompanyMasterVO companyMasterVO);

  /**
   * Fetch all by page number page.
   *
   * @param from the from
   * @param size the size
   * @return the page
   */
  Page<CompanyMaster> fetchAllByPageNumber(Integer from, Integer size);
}
