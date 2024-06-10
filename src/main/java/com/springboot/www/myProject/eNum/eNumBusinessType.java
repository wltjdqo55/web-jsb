package com.springboot.www.myProject.eNum;

public enum eNumBusinessType {
  CorporateBusiness("법인사업자"),
  SoleProprietorship("개인사업자");

  private String value;

  eNumBusinessType(String value) {
    this.value = value;
  }

  public String getValue(){
    return value;
  }
}
