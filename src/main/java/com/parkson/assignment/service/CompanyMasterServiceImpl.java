package com.parkson.assignment.service;

import com.parkson.assignment.exception.UnProcessAbleEntity;
import com.parkson.assignment.model.CompanyMaster;
import com.parkson.assignment.repository.CompanyMasterRepository;
import com.parkson.assignment.vo.request.CompanyMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class CompanyMasterServiceImpl implements CompanyMasterService {

  private final CompanyMasterRepository companyMasterRepository;

  @Autowired
  public CompanyMasterServiceImpl(final CompanyMasterRepository companyMasterRepository) {
    this.companyMasterRepository = companyMasterRepository;
  }

  @Override
  public void addCompanyMaster(final CompanyMasterVO companyMasterVO) {
    CompanyMaster companyMaster = new CompanyMaster();

    try {
      BeanUtils.copyProperties(companyMaster, companyMasterVO);
      companyMaster.setCreatedOn(new Date());
      companyMasterRepository.save(companyMaster);
    } catch (Exception e) {
      throw new UnProcessAbleEntity(e.getMessage());
    }
  }

  @Override
  public CompanyMaster updateCompanyMaster(final CompanyMasterVO companyMasterVO) {
    
    return null;
  }

  @Override
  public Page<CompanyMaster> fetchAllByPageNumber(Integer from, Integer to) {
    return this.companyMasterRepository.findAll(PageRequest.of(from, to));
  }
}
