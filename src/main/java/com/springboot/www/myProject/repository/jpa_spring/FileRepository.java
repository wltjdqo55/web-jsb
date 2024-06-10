package com.springboot.www.myProject.repository.jpa_spring;

import com.springboot.www.myProject.entity.entity.FileTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileTb, Long> {

}
