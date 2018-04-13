package com.service.account;


import com.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccuntServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
}
