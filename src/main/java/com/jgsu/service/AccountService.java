package com.jgsu.service;

import com.jgsu.domain.Account;

import java.util.List;
/*
* spring框架
* */
public interface AccountService {
    /*查询账户的所有信息有*/
    public List<Account> findAll();

    /*保存账户信息*/
    public void saveAccount(Account account);
}
