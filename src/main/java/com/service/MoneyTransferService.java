package com.service;

import com.exception.NotEnoughMoneyException;
import com.model.Account;
import com.model.TransactionLog;
import com.repository.AccountRepository;
import com.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class MoneyTransferService implements TransferService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Transactional
    @Override
    public void transferMoney(Account from, Account to, Long money) throws NotEnoughMoneyException {
        if (from.getMoney().getCash() >= money) {
            from.getMoney().setCash(from.getMoney().getCash() - money);
            to.getMoney().setCash(to.getMoney().getCash() + money);
            accountRepository.save(from);
            accountRepository.save(to);
            transactionLogRepository.save(new TransactionLog(from.getId(), to.getId(), -money));
            transactionLogRepository.save(new TransactionLog(to.getId(), from.getId(), money));
        }else {
            throw new NotEnoughMoneyException("not enough money, need " , from.getMoney().getCash(), money);
        }
    }
}
