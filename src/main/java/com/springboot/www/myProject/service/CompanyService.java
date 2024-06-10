package com.springboot.www.myProject.service;


import com.springboot.www.myProject.entity.dto.CompanyDTO;
import com.springboot.www.myProject.entity.entity.CompanyTb;
import com.springboot.www.myProject.entity.vo.CompanyVO;
import com.springboot.www.myProject.repository.jpa_spring.CompanyRepository;
import com.springboot.www.myProject.repository.querydsl.CompanyQueryDSL;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyRepository companyRepository;
  private final CompanyQueryDSL companyQueryDSL;




  //회사 가입
  public void saveCompany(CompanyVO companyVO){
    CompanyTb companyTb = new CompanyTb(companyVO);
    System.out.println(companyTb);
    System.out.println(companyTb.getWriteTime());
    companyRepository.save(companyTb);
  }

  //사업자 번호 체크하기
  public Boolean businessNumberChk(CompanyVO companyVO){

    return companyQueryDSL.businessNumberChk(companyVO);
  }

  //회사 목록 가져오기
  public List<CompanyDTO> getCompanylist(){
    List<CompanyDTO> list = companyRepository.findAll().stream().map(CompanyDTO::new)
                              .collect(Collectors.toList());
    return list;
  }

  //회사 상세 정보 , 수정 정보 가져오기
  public CompanyDTO getCompanyView(long companyId){
    return new CompanyDTO(companyRepository.findById(companyId).orElse(new CompanyTb()));
  }

  @Transactional
  //회사 정보 수정
  public void companyCorrctionOK(long companyId, CompanyVO companyVO) {
    CompanyTb companyTb = new CompanyTb(companyVO);
    companyRepository.save(companyTb);

  }


  //검색 필터 목록 조회
  public List<CompanyDTO> searchList(CompanyVO companyVO){
    List<CompanyDTO> list = companyQueryDSL.searchList(companyVO).stream().map(CompanyDTO::new)
                            .collect(Collectors.toList());

    return list;

  }
}
