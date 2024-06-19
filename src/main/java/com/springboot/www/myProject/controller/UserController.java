package com.springboot.www.myProject.controller;

import com.springboot.www.myProject.entity.dto.UserDTO;
import com.springboot.www.myProject.entity.vo.UserVO;
import com.springboot.www.myProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/user/userAdd")
  public String userAdd(){
    return "/user/userAdd.html";
  }

  @PostMapping("/user/userAddOK")
  @ResponseBody
  public UserDTO userAddOK(UserVO userVO){
    System.out.println(userVO);

    return userService.save(userVO);
  }

  @GetMapping("/user/userIdCheck")
  @ResponseBody
  public UserDTO userIdCheck(UserVO userVO){
    return userService.userIdCheck(userVO.getUserId());
  }

  @GetMapping("/user/userList")
  public String userList(){
    return "/user/userList.html";
  }

  @GetMapping("/user/userGetList")
  @ResponseBody
  public List<UserDTO> userGetList(){
    return userService.userGetList();
  }

  @GetMapping("/user/search")
  @ResponseBody
  public List<UserDTO> search(UserVO userVO){
    System.out.println(userVO.getSearchKeyword());
    System.out.println(userVO.getSearchOption());
    return userService.search(userVO);
  }

  @PostMapping("/user/companyUserAddOK/{companyId}")
  @ResponseBody
  public Boolean companyUserAddOK(@PathVariable long companyId, @RequestBody List<Long> checkArray){
    System.out.println(checkArray);
    System.out.println(companyId);
    System.out.println("사용자추가 컨트롤러");
    userService.saveUserAdd(companyId, checkArray);
    return true;
  }

  @GetMapping("/user/userView/{userId}")
  public String userView(@PathVariable long userId, Model model){
    model.addAttribute("id", userId);
    return "/user/userView.html";
  }

  @GetMapping("/user/getUserView/{userId}")
  @ResponseBody
  public UserDTO getCompanyView(@PathVariable long userId){
    return userService.getUserView(userId);
  }

  @GetMapping("/user/fileDownload{fileId}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable long fileId) throws IOException {
    return userService.getFile(fileId);
  }

  @GetMapping("/user/login")
  public String login(){
    return "/user/login.html";
  }

  @GetMapping("/user/loginCheck")
  @ResponseBody
  public boolean loginCheck(UserVO userVO){

    return userService.loginCheck((userVO));
  }



  
}


