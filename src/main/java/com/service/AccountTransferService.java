package com.service;

import com.model.Account;
import com.model.Money;
import com.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@Service
public class AccountTransferService implements TransferService {
    @Autowired
    private AccountRepository accountRepository;
    private ReadWriteLock reentrantLock = new ReentrantReadWriteLock();
    private Lock lock = reentrantLock.writeLock();

    public AccountTransferService() {
        System.out.println(1);
    }

    @Override
    public void transferMoney(Account from, Account to, Long money) {
        //lock.lock();
        if (from.getMoney().getCash() >= money) {
            from.getMoney().setCash(from.getMoney().getCash() - money);
            to.getMoney().setCash(to.getMoney().getCash() + money);
            accountRepository.save(from);
            accountRepository.save(to);
        }
        //lock.unlock();
    }
}
