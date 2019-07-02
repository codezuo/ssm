package com.jgsu.controller;

import com.jgsu.domain.Account;
import com.jgsu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
*springMVC框架
*  */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
/*
* 查询数据
* */
    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("表现层：查询所有账户。。。");
        //调用service层的方法，完成整合springmvc
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return "list";
    }
    /*
    * 插入数据
    * */
    @RequestMapping("/save")
        public void save(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
            System.out.println("表现层：插入信息到账户。。。");
            //调用service层的方法，完成整合springmvc
            accountService.saveAccount(account);
            response.sendRedirect(request.getContextPath()+"/account/findAll");
            return;
        }

}
