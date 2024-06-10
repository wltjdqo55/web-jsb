package com.springboot.www.myProject.entity.dto;

import com.springboot.www.myProject.entity.entity.FileTb;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDTO {

  private long id;

  private String fileName;

  private String filePath;

  private String fileType;

  private String userId;

  private String originalFileName;

  public FileDTO(FileTb file){
    this.id = file.getId();
    this.fileName = file.getFileName();
    this.filePath = file.getFilePath();
    this.fileType = file.getFileType();
    this.originalFileName = file.getOriginalFileName();
//    userId = file.getUser().getUserId();
  }

}
