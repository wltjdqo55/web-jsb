# web-jsb

## 소개
이 프로젝트는 현재 가지고 있던 경험을 기반으로 추가적인 새로운 기술을 연습하고 가다듬기 위한 개인 프로젝트입니다. <br>
다양한 최신 웹 기술을 실제로 적용해보고 이해도를 높이는 것을 목표로 진행하였습니다.

## 기술

* #### 프론트엔드 :  <img src="https://img.shields.io/badge/HTML-E34F26?style=flat-square&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/Vue.js-4FC08D?style=flat-square&logo=Vue.js&logoColor=white"/> <img src="https://img.shields.io/badge/Axios-5A29E4?style=flat-square&logo=Axios&logoColor=white"/>

* #### 백엔드 : <img src="https://img.shields.io/badge/JAVA-1572B6?style=flat-square&logo=JAVA&logoColor=white"/> <img src="https://img.shields.io/badge/JPA-1572B6?style=flat-square&logo=JPA&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat-square&logo=Spring Securit&logoColor=white"/>

* #### 데이터베이스 : <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=PostgreSQL&logoColor=white"/>


## 기능

* 사용자 인증 및 권한 관리 (Spring Security) - Bcryptpasswordencoder 사용
* 데이터베이스 연동 및 ORM (JPA , queryDSL 사용)
* 사용자 인터페이스 및 프론트엔드 개발 (Vue.js, HTML, CSS 사용)
* RESTful API 설계 및 구현
* 회원가입 시 정규식 사용 (비밀번호, 이메일 등)

## 구성 - MVC 패턴
    java
    ├─ com
    |    └─ springboot
    |       └─ www
    |          └─ myproject
    |             ├─ config
    |             |     QuerydslConfig.java
    |             |
    |             ├─ controller
    |             |     CompanyController.java
    |             |     UserController.java
    |             |
    |             ├─ entity
    |             |     ├─ dto
    |             |     |   CompanyDTO.java
    |             |     |   FileDTO.java
    |             |     |   UserCompanyDTO.java
    |             |     |   UserDTO.java
    |             |     |
    |             |     ├─ entity
    |             |     |   CompanyTb.java
    |             |     |   FileTb.java
    |             |     |   UserCompanyTb.java
    |             |     |   UserTb.java
    |             |     |
    |             |     └─ vo
    |             |         CompanyVO.java
    |             |         UserVO.java
    |             |
    |             ├─ eNum
    |             |     eNumBusinessType.java
    |             |     eNumStatus.java
    |             |     eNumUserEmploymentType.java
    |             |     eNumUserGender.java
    |             |     eNumUserState.java
    |             |     
    |             ├─ repository
    |             |     ├─ jpa_spring
    |             |     |   CompanyRepository.java
    |             |     |   FileRepository.java
    |             |     |   UserCompanyRepository.java
    |             |     |   UserRepository.java
    |             |     |
    |             |     └─ querydsl
    |             |         CompanyQueryDSL.java
    |             |         UserQueryDSL.java
    |             └─ service
    |                   CompanyService.java
    |                   ConfigService.java
    |                   UserService.java
    |
    └─ resources
        ├─ static
        |    ├─ dist
        |    |  ├─ jquery
        |    |  |   jquery.js
        |    |  |   jquery.min.js
        |    |  |
        |    |  └─ js
        |    |      toggles-list-filter.js
        |    |      vueCommon.js
        |    |
        |    └─ plugins
        |       ├─ axios
        |       |   axios.min.js
        |       |
        |       ├─ toastr
        |       |   toastr.css
        |       |   toastr.js.map
        |       |   toastr.min.css
        |       |   toastr.min.js
        |       |
        |       └─ vueJs
        |           vue.js
        |
        └─ templates
             ├─ company
             |      companyCorrection.html
             |      companylist.html
             |      companyUserAdd.html
             |      companyView.html
             |      join.html
             |
             └─ user
                    login.html
                    userAdd.html
                    userList.html
                    userView.html
