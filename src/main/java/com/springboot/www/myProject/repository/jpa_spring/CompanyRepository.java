package com.springboot.www.myProject.repository.jpa_spring;

import com.springboot.www.myProject.entity.entity.CompanyTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyTb, Long> {

}
