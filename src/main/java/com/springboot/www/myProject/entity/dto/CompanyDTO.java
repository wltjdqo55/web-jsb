package com.springboot.www.myProject.entity.dto;


import com.springboot.www.myProject.entity.entity.CompanyTb;
import com.springboot.www.myProject.entity.entity.UserTb;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class CompanyDTO {

  private long id;

  private String businessnumber;

  private String company;

  private String companyperson;

  private String companypersonemail;

  private String representativetelephone;

  private String companypersonphone;

  private String status;

  private String postaddress;

  private String detailaddress;

  private String note;

  private String businesstype;

  private String searchoption;

  private String searchkeyword;

  private String startDay;

  private String lastDay;

  private String writeTime;



  public CompanyDTO(CompanyTb companyTb){
    this.id = companyTb.getId();
    this.company = companyTb.getCompany();
    this.companyperson = companyTb.getCompanyperson();
    this.companypersonemail = companyTb.getCompanypersonemail();
    this.companypersonphone = companyTb.getCompanypersonphone();
    this.businessnumber = companyTb.getBusinessnumber();
    this.detailaddress = companyTb.getDetailaddress();
    this.postaddress = companyTb.getPostaddress();
    this.note = companyTb.getNote();
    this.status = companyTb.getStatus().getValue().toString();
    this.representativetelephone = companyTb.getRepresentativetelephone();
    this.businesstype = companyTb.getBusinesstype().getValue().toString();
    this.writeTime = companyTb.getWriteTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

}
