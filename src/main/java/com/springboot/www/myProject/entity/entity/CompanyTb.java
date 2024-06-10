package com.springboot.www.myProject.entity.entity;


import com.springboot.www.myProject.eNum.eNumBusinessType;
import com.springboot.www.myProject.eNum.eNumStatus;
import com.springboot.www.myProject.entity.dto.CompanyDTO;
import com.springboot.www.myProject.entity.vo.CompanyVO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@NoArgsConstructor
@Table(name="companytable")
public class CompanyTb {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String businessnumber;

  private String company;

  private String companyperson;

  private String companypersonemail;

  private String representativetelephone;

  private String companypersonphone;

  @Enumerated(STRING)
  private eNumStatus status;

  private String postaddress;

  private String detailaddress;

  private String note;

  @Enumerated(STRING)
  private eNumBusinessType businesstype;

  private LocalDateTime writeTime;

  @OneToMany(fetch = FetchType.LAZY)
  private List<UserTb> userList = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY)
  private List<UserCompanyTb> userCompany = new ArrayList<>();


  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "companyTb")
  private List<UserTb> user = new ArrayList<>();

  public CompanyTb(CompanyDTO companyDTO){
    this.id = companyDTO.getId();
    this.company = companyDTO.getCompany();
    this.companyperson = companyDTO.getCompanyperson();
    this.companypersonemail = companyDTO.getCompanypersonemail();
    this.companypersonphone = companyDTO.getCompanypersonphone();
    this.businessnumber = companyDTO.getBusinessnumber();
    this.detailaddress = companyDTO.getDetailaddress();
    this.postaddress = companyDTO.getPostaddress();
    this.note = companyDTO.getNote();

    if(eNumStatus.using.name().equals(companyDTO.getStatus())){
      this.status = eNumStatus.using;
    } else if(eNumStatus.standby.name().equals(companyDTO.getStatus())){
      this.status = eNumStatus.standby;
    } else if(eNumStatus.stop.name().equals(companyDTO.getStatus())){
      this.status = eNumStatus.stop;
    }

    this.representativetelephone = companyDTO.getRepresentativetelephone();

    if(eNumBusinessType.CorporateBusiness.name().equals(companyDTO.getBusinesstype())){
      this.businesstype = eNumBusinessType.CorporateBusiness;
    } else if(eNumBusinessType.SoleProprietorship.name().equals(companyDTO.getBusinesstype())){
      this.businesstype = eNumBusinessType.SoleProprietorship;
    }

    this.writeTime = LocalDateTime.now();
  }

  public CompanyTb(CompanyVO companyVO) {
  }

  public CompanyTb(long companyId) {
    this.id = companyId;
  }


  public void update(CompanyVO companyVO){
    this.company = companyVO.getCompany();
    this.companyperson = companyVO.getCompanyperson();
    this.companypersonemail = companyVO.getCompanypersonemail();
    this.companypersonphone = companyVO.getCompanypersonphone();
    this.businessnumber = companyVO.getBusinessnumber();
    this.detailaddress = companyVO.getDetailaddress();
    this.postaddress = companyVO.getPostaddress();
    this.note = companyVO.getNote();

    if(eNumStatus.using.name().equals(companyVO.getStatus())){
      this.status = eNumStatus.using;
    } else if(eNumStatus.standby.name().equals(companyVO.getStatus())){
      this.status = eNumStatus.standby;
    } else if(eNumStatus.stop.name().equals(companyVO.getStatus())){
      this.status = eNumStatus.stop;
    }

    this.representativetelephone = companyVO.getRepresentativetelephone();

    if(eNumBusinessType.CorporateBusiness.name().equals(companyVO.getBusinesstype())){
      this.businesstype = eNumBusinessType.CorporateBusiness;
    } else if(eNumBusinessType.SoleProprietorship.name().equals(companyVO.getBusinesstype())){
      this.businesstype = eNumBusinessType.SoleProprietorship;
    }
  }

  public void update(CompanyDTO companyDTO){
    this.company = companyDTO.getCompany();
    this.companyperson = companyDTO.getCompanyperson();
    this.companypersonemail = companyDTO.getCompanypersonemail();
    this.companypersonphone = companyDTO.getCompanypersonphone();
    this.businessnumber = companyDTO.getBusinessnumber();
    this.detailaddress = companyDTO.getDetailaddress();
    this.postaddress = companyDTO.getPostaddress();
    this.note = companyDTO.getNote();

    if(eNumStatus.using.name().equals(companyDTO.getStatus())){
      this.status = eNumStatus.using;
    } else if(eNumStatus.standby.name().equals(companyDTO.getStatus())){
      this.status = eNumStatus.standby;
    } else if(eNumStatus.stop.name().equals(companyDTO.getStatus())){
      this.status = eNumStatus.stop;
    }

    this.representativetelephone = companyDTO.getRepresentativetelephone();

    if(eNumBusinessType.CorporateBusiness.name().equals(companyDTO.getBusinesstype())){
      this.businesstype = eNumBusinessType.CorporateBusiness;
    } else if(eNumBusinessType.SoleProprietorship.name().equals(companyDTO.getBusinesstype())){
      this.businesstype = eNumBusinessType.SoleProprietorship;
    }
  }


}