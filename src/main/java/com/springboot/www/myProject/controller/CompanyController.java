package com.springboot.www.myProject.controller;

import com.springboot.www.myProject.entity.dto.CompanyDTO;
import com.springboot.www.myProject.entity.vo.CompanyVO;
import com.springboot.www.myProject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class CompanyController {

  private final CompanyService companyService;


  //회사 등록 페이지 이동

  @GetMapping("/company/join")
  public String join(){
    System.out.println("===============");
    return "company/join.html";
  }

  //사업자번호 중복체크
  @GetMapping("/company/BusinessNumberChk")
  @ResponseBody
  public Boolean businessNumberChk(CompanyVO companyVO){
    System.out.println(companyVO.getBusinessnumber());
    try{
        if(companyService.businessNumberChk(companyVO)){
          return true;
        }else{
          return false;
        }
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  //회사 등록
  @PostMapping("/company/joinOK")
  @ResponseBody
  public boolean joinOK(CompanyVO companyVO){
    try {
      if(companyVO.getBusinesstype().equals("selectNo")){
        return false;
      }

      companyService.saveCompany(companyVO);
      return true;
    }catch(Exception e){
      return false;
    }
  }
  //회사 목록 페이지 이동
  @GetMapping("/company/companylist")
  public String companylist(){
    System.out.println("===============");
    return "/company/companylist.html";
  }

  //회사 목록 데이터 가져오기
  @GetMapping("/company/getCompanylist")
  @ResponseBody
  public List<CompanyDTO> getCompanylist(){

    return companyService.getCompanylist();
  }

  //회사 상세 정보 조회 페이지 이동
  @GetMapping("/company/companyView/{companyId}")
  public String companyView(@PathVariable long companyId, Model model){
    model.addAttribute("companyId", companyId);
    return "company/companyView.html";
  }

  //회사 상세 조회 데이터 가져오기
  @GetMapping("/company/getCompanyView/{companyId}")
  @ResponseBody
  public CompanyDTO getCompanyView(@PathVariable long companyId){
    return companyService.getCompanyView(companyId);
  }

  //수정 페이지 이동하기
  @GetMapping("/company/companyCorrection/{companyId}")
  public String companyCorrection(@PathVariable long companyId, Model model){
    model.addAttribute("companyId", companyId);
    return "company/companyCorrection.html";
  }

  //수정 페이지 데이터 가져오기
  @GetMapping("/company/getCompanyCorrection/{companyId}")
  @ResponseBody
  public CompanyDTO getCompanyCorrection(@PathVariable long companyId){
    return companyService.getCompanyView(companyId);
  }

  //수정하기
  @PostMapping("/company/companyCorrectionOK/{companyId}")
  @ResponseBody
  public boolean companyCorrectionOK(@PathVariable long companyId, CompanyVO companyVO){
   try{
     System.out.println("====");
     System.out.println(companyVO.getStatus());
     System.out.println(companyVO.getBusinesstype());
      companyService.companyCorrctionOK(companyId, companyVO);
     System.out.println("========");
      return true;
    }
   catch(Exception e){
      return false;
    }
  }


  //검색기능
  @GetMapping("/company/searchCompanyList")
  @ResponseBody
  public List<CompanyDTO> searchCompanyList(CompanyVO companyVO){
    System.out.println(companyVO.getSearchkeyword());
    System.out.println(companyVO.getSearchoption());
    System.out.println(companyVO.getStartDay());
    System.out.println(companyVO.getLastDay());

    return companyService.searchList(companyVO);
  }

  @GetMapping("/company/companyUserAdd/{companyId}")
  public String companyUser(@PathVariable long companyId, Model model){
    CompanyDTO companyDTO = companyService.getCompanyView(companyId);
    String company = companyDTO.getCompany();
    model.addAttribute("company", company);
    model.addAttribute("companyId", companyId);
    return "/company/companyUserAdd.html";
  }



}
