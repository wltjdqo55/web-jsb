package com.springboot.www.myProject.repository.jpa_spring;

import com.springboot.www.myProject.entity.entity.UserCompanyTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompanyRepository extends JpaRepository<UserCompanyTb, Long> {
}
