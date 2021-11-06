package com.innovaitagence.contactmanagement.repository;

import com.innovaitagence.contactmanagement.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByVatIgnoreCase(String vat);
}
