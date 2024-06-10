package com.springboot.www.myProject.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
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

//  public void updateUserAdd(long companyId , long userId){
//      jpaQueryFactory
//          .update(userTb)
//          .set(userTb.companyTb, new CompanyTb(companyId))
//          .where(userTb.id.eq(userId))
//          .execute();
//  }


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

  public UserTb loginCheck(UserVO userVO){
    return jpaQueryFactory
        .selectFrom(userTb)
        .where(
            WhereUserId(userVO).and(WhereUserPassword(userVO))
        )
        .fetchOne();

  }

  private BooleanExpression WhereUserId(UserVO userVO){
        return userTb.userId.eq(userVO.getUserId());
  }

  private BooleanExpression WhereUserPassword(UserVO userVO){
    StringPath userPasswordPath = userTb.userPassword;
    String actualPassword = jpaQueryFactory.select(userPasswordPath)
        .from(userTb)
        .where()
        .fetchOne();
    System.out.println(actualPassword);
    System.out.println(userTb.userPassword.eq(userVO.getUserPassword()));
    return userTb.userPassword.eq(userVO.getUserPassword());
//    if(encoder.matches(userVO.getUserPassword(), actualPassword)){
//      return Expressions.asBoolean(true);
//    }
//    else{
//      return Expressions.asBoolean(false);
//    }
  }
}