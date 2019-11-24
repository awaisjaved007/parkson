package com.parkson.assignment.service;

import com.parkson.assignment.exception.UnProcessAbleEntity;
import com.parkson.assignment.model.CompanyMaster;
import com.parkson.assignment.repository.CompanyMasterRepository;
import com.parkson.assignment.utils.LoggerUtils;
import com.parkson.assignment.vo.request.CompanyMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class CompanyMasterServiceImpl implements CompanyMasterService {

  @Autowired private CompanyMasterRepository companyMasterRepository;

  @Override
  @Transactional
  public void addCompanyMaster(final CompanyMasterVO companyMasterVO) {
    LoggerUtils.debug(log, "###Add: companyMasterVO### [", companyMasterVO.toString(), "]");

    if (companyMasterRepository.findById(companyMasterVO.getCompCode()).isPresent()) {
      throw new UnProcessAbleEntity("company.master.already.exists");
    }

    CompanyMaster companyMaster = new CompanyMaster();

    try {
      BeanUtils.copyProperties(companyMaster, companyMasterVO);
      companyMaster.setCreatedOn(new Date());
      companyMaster = companyMasterRepository.save(companyMaster);
    } catch (Exception e) {
      throw new UnProcessAbleEntity(e.getMessage());
    }
    LoggerUtils.debug(
        log, "###Added Successfully: companyMaster### [", companyMaster.toString(), "]");
  }

  @Override
  public CompanyMaster updateCompanyMaster(final CompanyMasterVO companyMasterVO) {

    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<CompanyMaster> fetchAllByPageNumber(Integer from, Integer size) {
    return this.companyMasterRepository.findAll(PageRequest.of(from, size));
  }
}
