package com.springboot.www.myProject.entity.entity;

import com.springboot.www.myProject.eNum.eNumUserEmploymentType;
import com.springboot.www.myProject.eNum.eNumUserGender;
import com.springboot.www.myProject.eNum.eNumUserState;
import com.springboot.www.myProject.entity.vo.UserVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name="userTable")
public class UserTb {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  private String userId;

  private String userPassword;

  private String userName;

  private String userPhone;

  private String userBirth;

  private String userPostAddress;

  @Enumerated(EnumType.STRING)
  private eNumUserGender gender;

  private String userDetailAddress;

  private String position;

  private String departmentName;

  private String userEmail;

  private String officePhone;

  private String fax;

  private String joiningCompany;

  private String resignationCompany;

  @Enumerated(EnumType.STRING)
  private eNumUserEmploymentType employmentType;

  @Enumerated(EnumType.STRING)
  private eNumUserState state;

  private String note;


  private LocalDateTime writeTime;


  @OneToMany(fetch = FetchType.LAZY)
  private List<UserCompanyTb> userCompany = new ArrayList<>();


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private CompanyTb companyTb;
//
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<FileTb> files = new ArrayList<>();

//  public UserTb(long id){
//
//    this.companyTb = new CompanyTb(id);
//  }
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_company_table")
    private List<UserCompanyTb> list = new ArrayList<>();


  public UserTb(UserVO userVO) {
    this.id = userVO.getId();
    this.userId = userVO.getUserId();
    this.userPassword = userVO.getUserPassword();
    this.userName = userVO.getUserName();
    this.userPhone = userVO.getUserPhone();
    this.userBirth = userVO.getUserBirth();
    this.userPostAddress = userVO.getUserPostAddress();
    this.userDetailAddress = userVO.getUserDetailAddress();
    this.position = userVO.getPosition();
    this.departmentName = userVO.getDepartmentName();
    this.userEmail = userVO.getUserEmail();
    this.officePhone = userVO.getOfficePhone();
    this.fax = userVO.getFax();
    this.joiningCompany = userVO.getJoiningCompany();
    this.resignationCompany = userVO.getResignationCompany();

    if(eNumUserGender.man.name().equals(userVO.getGender())){
      this.gender = eNumUserGender.man;
    }else if(eNumUserGender.woman.name().equals(userVO.getGender())){
      this.gender = eNumUserGender.woman;
    }

    if(eNumUserEmploymentType.permanent.name().equals(userVO.getEmploymentType())){
      this.employmentType = eNumUserEmploymentType.permanent;
    }else if(eNumUserEmploymentType.contract.name().equals(userVO.getEmploymentType())){
      this.employmentType = eNumUserEmploymentType.contract;
    }else if(eNumUserEmploymentType.outsourcing.name().equals(userVO.getEmploymentType())){
      this.employmentType = eNumUserEmploymentType.outsourcing;
    }else if(eNumUserEmploymentType.etc.name().equals(userVO.getEmploymentType())){
      this.employmentType = eNumUserEmploymentType.etc;
    }

    if(eNumUserState.holdOffice.name().equals(userVO.getState())){
      this.state = eNumUserState.holdOffice;
    }else if(eNumUserState.resign.name().equals(userVO.getState())){
      this.state = eNumUserState.resign;
    }
    this.note = userVO.getNote();
    this.writeTime = LocalDateTime.now();
    this.companyTb = new CompanyTb(userVO.getCompanyId());
  }

  public UserTb(long userId){
    this.id = userId;
  }
}
