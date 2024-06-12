package com.springboot.www.myProject.service;

import com.springboot.www.myProject.entity.dto.FileDTO;
import com.springboot.www.myProject.entity.dto.UserCompanyDTO;
import com.springboot.www.myProject.entity.dto.UserDTO;
import com.springboot.www.myProject.entity.entity.CompanyTb;
import com.springboot.www.myProject.entity.entity.FileTb;
import com.springboot.www.myProject.entity.entity.UserCompanyTb;
import com.springboot.www.myProject.entity.entity.UserTb;
import com.springboot.www.myProject.entity.vo.UserVO;
import com.springboot.www.myProject.repository.jpa_spring.CompanyRepository;
import com.springboot.www.myProject.repository.jpa_spring.FileRepository;
import com.springboot.www.myProject.repository.jpa_spring.UserCompanyRepository;
import com.springboot.www.myProject.repository.jpa_spring.UserRepository;
import com.springboot.www.myProject.repository.querydsl.UserQueryDSL;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserQueryDSL userQueryDSL;
  private final FileRepository fileRepository;
  private final UserCompanyRepository userCompanyRepository;
  private final CompanyRepository companyRepository;
  private final BCryptPasswordEncoder encoder;

  //사용자추가
  @Transactional
  public void saveUserAdd(long companyId, List<Long> checked){

    UserVO userr = new UserVO();
    userr.setCompanyId(companyId);

    for (Long aLong : checked) {
      System.out.println(aLong);
      System.out.println(companyId);
      UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
      userCompanyDTO.setMyUserId(aLong);
      userCompanyDTO.setMyCompanyId(companyId);

      UserCompanyTb userCompanyTb = new UserCompanyTb();
      userCompanyTb.setUser(userRepository.findById(aLong).orElse(new UserTb()));
      userCompanyTb.setCompany(companyRepository.findById(companyId).orElse(new CompanyTb()));

      userCompanyRepository.save(userCompanyTb);
    }
  }


  @Transactional
  public UserDTO save(UserVO userVO){
    userVO.setUserPassword(encoder.encode(userVO.getUserPassword()));
    UserDTO userDTO = new UserDTO(userRepository.save(new UserTb(userVO)));
//    System.out.println("Id :" + userDTO.getId());
    System.out.println("파일업로드 들어가기 전");
    if(!(userVO.getFiles()==null)){
      System.out.println("파일업로드");
      FileSaveFor(userVO.getFiles(), userDTO.getId());
    }
    return userDTO;
  }
  public void FileSaveFor(List<MultipartFile> list, long userId){

    String path = "C:\\study\\myProject\\File";
//    String path = "./uploadFile";
    File Folder = new File(path);

    if(!Folder.exists()){
      try{
        boolean result = Folder.mkdir();
        if(result){
          System.out.println("폴더 생성완료");
        }else{
          System.out.println("폴더 생성에 실패하였습니다.");
        }
      }catch(Exception e){
        e.getStackTrace();
      }
    }else{
      System.out.println("폴더가 이미 존재합니다.");
    }

    for (MultipartFile multipartFile : list) {

      String fileName = multipartFile.getOriginalFilename();

      File savaFile = new File(path, Objects.requireNonNull(fileName));
      try (FileOutputStream fos = new FileOutputStream(savaFile)) {
        fos.write(multipartFile.getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
      int pathPoint = Objects.requireNonNull(fileName).lastIndexOf('.');
      String filePoint = fileName.substring(pathPoint + 1);
      System.out.println(multipartFile.getOriginalFilename());
      System.out.println(path);
      System.out.println(filePoint);
      System.out.println(userId);
      fileRepository.save(new FileTb(multipartFile.getOriginalFilename(), path, filePoint, userId, fileName));
    }
  }

  public UserDTO userIdCheck(String userId){
    UserDTO userDTO = new UserDTO();
    if(userRepository.findOneByUserId(userId).isEmpty()){
      userDTO.setCheckId(true);
      userDTO.setMessage("사용 가능한 아이디입니다.");
      return userDTO;
    }
    userDTO.setCheckId(false);
    userDTO.setMessage("이미 사용중인 아이디입니다.");
    return userDTO;
  }

  public List<UserDTO> userGetList(){
    return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().map(UserDTO::new).collect(Collectors.toList());
  }

  public List<UserDTO> search(UserVO userVO){
    List<UserTb> list = userQueryDSL.search(userVO);

    return list.stream().map(UserDTO::new).collect(Collectors.toList());
  }

  public UserDTO getUserView(long userId){
    return new UserDTO(userRepository.findById(userId).orElse(new UserTb()));
  }

  public ResponseEntity<Resource> getFile(long fileId) throws IOException {
    FileDTO file = new FileDTO(fileRepository.findById(fileId).orElse(new FileTb()));
    return fileDownload(file.getFilePath(), file.getFileName());
  }

  public ResponseEntity<Resource> fileDownload(String filePath, String fileName) throws IOException {
    try {
      Resource resource = new InputStreamResource(new FileInputStream(filePath + "\\" + fileName));

      return ResponseEntity.ok()
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + fileName + "\";")
          .body(resource);

    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    System.out.println("종료");
    return new ResponseEntity<Resource>(null);
  }

  public boolean loginCheck(UserVO userVO){
//    userVO.setUserPassword(encoder.encode(userVO.getUserPassword()));
    System.out.println(userVO.getUserPassword());
    System.out.println(userVO.getUserId());
    UserDTO userTb = userRepository.findOneByUserId(userVO.getUserId()).orElse(new UserDTO());
    return encoder.matches(userVO.getUserPassword(), userTb.getUserPassword());
//    UserTb userTb = userQueryDSL.loginCheck(userVO);
//    return userQueryDSL.loginCheck(userVO) != null;
  }

}
