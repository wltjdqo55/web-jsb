package com.springboot.www.myProject.entity.dto;

import com.springboot.www.myProject.entity.entity.UserTb;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

  long id;

  private String userId;

  private String userPassword;

  private String userName;

  private String userPhone;

  private String userBirth;

  private String userPostAddress;

  private String gender;

  private String userDetailAddress;

  private Long companyId;

  private String companyName;

  private String position;

  private String departmentName;

  private String userEmail;

  private String officePhone;

  private String fax;

  private String joiningCompany;

  private String resignationCompany;

  private String employmentType;

  private String state;

  private String note;

  private String writeTime;

  private boolean checkId;

  private String message;

  private List<FileDTO> files = new ArrayList<>();



  public UserDTO(UserTb userTb){
      this.id = userTb.getId();
      this.userId = userTb.getUserId();
      this.userName = userTb.getUserName();
      this.userPhone = userTb.getUserPhone();
      this.userBirth = userTb.getUserBirth();
      this.userPostAddress = userTb.getUserPostAddress();
      this.userDetailAddress = userTb.getUserDetailAddress();
    this.position = userTb.getPosition();
    this.departmentName = userTb.getDepartmentName();
    this.userEmail = userTb.getUserEmail();
    this.officePhone = userTb.getOfficePhone();
    this.fax = userTb.getFax();
    this.joiningCompany = userTb.getJoiningCompany();
    this.resignationCompany = userTb.getResignationCompany();
    this.note = userTb.getNote();
    this.writeTime = userTb.getWriteTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    this.userPassword = userTb.getUserPassword();
    this.companyName = userTb.getCompanyTb().getCompany();
//    this.companyId = userTb.getCompanyTb().getId();

    userTb.getFiles().forEach(t -> {
      files.add(new FileDTO(t));
    });

    this.state = userTb.getState().getValue().toString(); // 상태

    this.gender = userTb.getGender().getValue().toString(); // 성별
    this.employmentType = userTb.getEmploymentType().getValue().toString();   // 고용형태
  }
}
