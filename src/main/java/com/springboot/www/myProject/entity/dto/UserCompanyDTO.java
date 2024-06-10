package com.springboot.www.myProject.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCompanyDTO {

    private long id;

    private long myUserId;

    private long myCompanyId;

}
