package com.jgsu.dao;

import com.jgsu.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
* Mybatis框架
* */
@Repository
public interface AccountDao {

    /*查询账户的所有信息有*/
    //@Select("select * from account")
    public List<Account> findAll();

    /*保存账户信息*/
    //通过注解形式查询
  //  @Insert("insert into account (name,money) values(#{name},#{money})")
    public void saveAccount(Account account);
}
