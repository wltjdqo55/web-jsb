package com.springboot.www.myProject.entity.entity;

import com.springboot.www.myProject.entity.vo.UserVO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name="fileTable")
public class FileTb {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String fileName;

  private String originalFileName;

  private String fileType;

  private String filePath;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserTb user;


  public FileTb(String name, String path, String filePoint, long userId, String uniqueFileName){
    this.fileName = uniqueFileName;
    this.originalFileName = name;
    this.fileType = filePoint;
    this.filePath = path;
    this.user = new UserTb(userId);
  }

}
