package com.parkson.assignment.repository;

import com.parkson.assignment.model.CompanyMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyMasterRepository
    extends JpaRepository<CompanyMaster, String>{
}
