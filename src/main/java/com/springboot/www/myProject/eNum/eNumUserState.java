package com.springboot.www.myProject.eNum;

public enum eNumUserState {
  holdOffice("재직중"),
  resign("퇴직");

  private String value;


  eNumUserState(String value) {
    this.value = value;
  }

  public String name(String value){
    return value;
  }
  public String getValue(){
    return value;
  }

}
