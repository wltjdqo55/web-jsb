package com.springboot.www.myProject.repository.jpa_spring;

import com.springboot.www.myProject.entity.dto.UserDTO;
import com.springboot.www.myProject.entity.entity.UserTb;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTb, Long> {
    Optional<UserDTO> findOneByUserId(String userId);
}
