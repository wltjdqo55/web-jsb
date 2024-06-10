package com.springboot.www.myProject.eNum;

public enum eNumUserEmploymentType {
  permanent("정규직"),
  contract("계약직"),
  outsourcing("외주"),
  etc("기타");

  private String value;

  eNumUserEmploymentType(String value){
    this.value = value;
  }

  public String name(String value){
    return value;
  }

  public String getValue(){
    return value;
  }

}
