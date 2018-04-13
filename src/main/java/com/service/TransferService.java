package com.service;

import com.model.Account;
import com.model.Money;


public interface TransferService {
    void transferMoney(Account from, Account to, Long money);
}
