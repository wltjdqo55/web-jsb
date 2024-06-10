package com.springboot.www.myProject.eNum;

public enum eNumUserGender {
  man("남자"),
  woman("여자");

  private String value;

  eNumUserGender(String value){
    this.value = value;
  }

  public String name(String value){
    return value;
  }

  public String getValue(){
    return value;
  }


}
