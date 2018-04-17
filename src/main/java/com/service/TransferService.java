package com.service;

import com.exception.NotEnoughMoneyException;
import com.model.Account;

public interface TransferService {
    void transferMoney(Account from, Account to, Long money) throws NotEnoughMoneyException;
}
