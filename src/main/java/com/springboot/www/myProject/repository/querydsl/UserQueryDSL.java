package com.springboot.www.myProject.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.www.myProject.entity.entity.UserTb;
import com.springboot.www.myProject.entity.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.springboot.www.myProject.entity.entity.QUserTb.userTb;

@Repository
@RequiredArgsConstructor
public class UserQueryDSL {

  private final JPAQueryFactory jpaQueryFactory;
  private final BCryptPasswordEncoder encoder;

  public List<UserTb> search(UserVO userVO){
    return jpaQueryFactory
        .selectFrom(userTb)
        .where(
            WhereSearch(userVO)
        )
        .fetch();
  }

  private BooleanExpression WhereSearch(UserVO userVO) {
    if (userVO.getSearchOption().equals("all")) {
      return userTb.userId.contains(userVO.getSearchKeyword()).or(userTb.userName.contains(userVO.getSearchKeyword()));
    } else if (userVO.getSearchOption().equals("userId")) {
      return userTb.userId.contains(userVO.getSearchKeyword());
    } else if (userVO.getSearchOption().equals("userName")) {
      return userTb.userName.contains(userVO.getSearchKeyword());
    }
    return null;
  }
}