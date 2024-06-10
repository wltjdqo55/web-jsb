package com.springboot.www.myProject.eNum;

public enum eNumStatus {
  using("사용중"),
  standby("대기중"),
  stop("사용중지");

  private String value;

  eNumStatus(String value) {
    this.value = value;
  }

  public String name(String value){
    return value;
  }
  public String getValue(){
    return value;
  }
}
