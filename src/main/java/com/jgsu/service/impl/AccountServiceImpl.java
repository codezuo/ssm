package com.jgsu.service.impl;

import com.jgsu.dao.AccountDao;
import com.jgsu.domain.Account;
import com.jgsu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("accountService")//将该类交给springIOC容器保管
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("(service)业务层：查询所有账户信息。。。");
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("(service)业务层：保存账户信息。。。");
        accountDao.saveAccount(account);
    }
}
