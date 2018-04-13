package com.controller;

import com.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private AccountService accountService;

    @Autowired
    public TestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String handler(@RequestParam String name, @RequestParam Long cash) {
        accountService.add(name, cash);
        return "account " + name + " added";
    }

    @RequestMapping("/transfer")
    @ResponseBody
    public String handler(@RequestParam Long idFrom, @RequestParam Long idTo, @RequestParam Long cash) {
        accountService.transfer(idFrom, idTo, cash);
        return accountService.getStatus();
    }
}