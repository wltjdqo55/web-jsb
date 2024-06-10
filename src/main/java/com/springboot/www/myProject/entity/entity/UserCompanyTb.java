package com.springboot.www.myProject.entity.entity;

import com.springboot.www.myProject.entity.dto.UserCompanyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity(name = "userCompanyTable")
@Getter
public class UserCompanyTb {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long id;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "user_id")
      private UserTb user;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "company_id")
      private CompanyTb company;


      public void setUser(UserTb user) {
            this.user = user;
      }

      public void setCompany(CompanyTb company){
            this.company = company;
      }

}
