package com.springboot.www.myProject.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserVO {

  private List<MultipartFile> files;

  private long id;

  private String userId;

  private String userPassword;

  private String userName;

  private String userPhone;

  private String userBirth;

  private String userPostAddress;

  private String gender;

  private String userDetailAddress;

  private long companyId;

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

  private LocalDateTime writeTime;

  private String searchKeyword;

  private String searchOption;


}
