package com.springboot.www.myProject.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.www.myProject.eNum.eNumBusinessType;
import com.springboot.www.myProject.entity.dto.CompanyDTO;
import com.springboot.www.myProject.entity.entity.CompanyTb;
import com.springboot.www.myProject.entity.vo.CompanyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static com.springboot.www.myProject.entity.entity.QCompanyTb.companyTb;


@Repository
@RequiredArgsConstructor
public class CompanyQueryDSL {

  private final JPAQueryFactory jpaQueryFactory;

  public Boolean businessNumberChk(CompanyVO companyVO){
    List<CompanyTb> list = jpaQueryFactory
        .selectFrom(companyTb)
        .where(
            companyTb.businessnumber.eq(companyVO.getBusinessnumber())
        )
        .fetch();

    if(list.isEmpty()){
      return false;
    }
    return true;
  }


  @Modifying
  public void update(CompanyDTO companyDTO){
    jpaQueryFactory.update(companyTb)
        .set(companyTb.businessnumber, companyDTO.getBusinessnumber())
        .set(companyTb.company, companyDTO.getCompany())
        .set(Collections.singletonList(companyTb.businesstype), Collections.singletonList(companyDTO.getBusinesstype()))
        .set(companyTb.companyperson, companyDTO.getCompanyperson())
        .set(companyTb.companypersonemail, companyDTO.getCompanypersonemail())
        .set(companyTb.companypersonphone, companyDTO.getCompanypersonphone())
        .set(companyTb.representativetelephone, companyDTO.getRepresentativetelephone())
        .set(companyTb.note, companyDTO.getNote())
        .set(companyTb.postaddress, companyDTO.getPostaddress())
        .set(companyTb.detailaddress, companyDTO.getDetailaddress())
        .set(Collections.singletonList(companyTb.status), Collections.singletonList(companyDTO.getStatus()))
        .where(companyTb.id.eq(companyDTO.getId()))
        .execute();
  }


  public List<CompanyTb> searchList(CompanyVO companyVO) {
    List<CompanyTb> list = jpaQueryFactory
            .selectFrom(companyTb)
            .where(
                getWhereSearchOption(companyVO),
                getWhereSearchKeyword(companyVO),
                getWhereDateFrom(companyVO),
                getWhereDateTo(companyVO)
            )
            .fetch();
    for(int i=0;i<list.size();i++){
      System.out.println("리스트" + list.get(i));
    }
    return list;
  }


  private BooleanExpression getWhereSearchOption(CompanyVO companyVO) {
    String searchOption = companyVO.getSearchoption();

    if (searchOption.equals("all")) {
      return null;
    } else if (searchOption.equals("soleProprietorship")) {
      return companyTb.businesstype.eq(eNumBusinessType.SoleProprietorship);
    } else if (searchOption.equals("corporateBusiness")) {
      return companyTb.businesstype.eq(eNumBusinessType.CorporateBusiness);
    }

    return null;
  }

  private BooleanExpression getWhereSearchKeyword(CompanyVO companyVO){
      if(companyVO.getSearchkeyword().isEmpty())
        return null;
      return companyTb.company.contains(companyVO.getSearchkeyword());
  }

  private BooleanExpression getWhereDateFrom(CompanyVO companyVO) {
    if (companyVO.getStartDay() != null) {
      LocalDate date = LocalDate.parse(companyVO.getStartDay(), DateTimeFormatter.ISO_DATE);
      return companyTb.writeTime.goe(date.atStartOfDay());
    }
    return null;
  }

  private BooleanExpression getWhereDateTo(CompanyVO companyVO) {
    if (companyVO.getLastDay() != null) {
      LocalDate date = LocalDate.parse(companyVO.getLastDay(), DateTimeFormatter.ISO_DATE);
      return companyTb.writeTime.lt(date.atTime(23, 59, 59, 999999999));
    }
    return null;
  }



}

