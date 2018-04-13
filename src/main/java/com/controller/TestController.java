package com.controller;

import com.exception.NotEnoughMoneyException;
import com.model.Account;
import com.model.Money;
import com.repository.AccountRepository;
import com.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private AccountRepository accountRepository;
    private TransferService transferService;

    @Autowired
    public TestController(AccountRepository accountRepository, TransferService transferService) {
        this.accountRepository = accountRepository;
        this.transferService = transferService;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String handler(@RequestParam String name, @RequestParam Long cash) {
        accountRepository.save(new Account(name, new Money(cash)));
        return "account added";
    }

    @RequestMapping("/test")
    public String handler(){
        Account account = accountRepository.getAccountById(1L);
        return account.getName();
    }

    @RequestMapping("/transfer")
    @ResponseBody
    public String handler(@RequestParam Long idFrom, @RequestParam Long idTo, @RequestParam Long cash){
        try {
            Account accountFrom = accountRepository.getAccountById(idFrom);
            Account accountTo = accountRepository.getAccountById(idTo);
            transferService.transferMoney(accountFrom, accountTo, cash);
            return "transfer is done";
        }catch (NullPointerException e) {
            return "wring account id";
        } catch (NotEnoughMoneyException e){
            return e.getMessage() + e.getMoney();
        }
    }
}
