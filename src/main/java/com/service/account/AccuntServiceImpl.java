package com.service.account;

import com.exception.NotEnoughMoneyException;
import com.model.Account;
import com.model.Money;
import com.model.TransactionLog;
import com.repository.AccountRepository;
import com.repository.TransactionLogRepository;
import com.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccuntServiceImpl implements AccountService {
    private String status;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransferService transferService;
    @Autowired
    private TransactionLogRepository transactionLogRepository;

    public String getStatus() {
        return status;
    }

    @Transactional
    @Override
    public void add(String name, Long cash) {
        Account account = new Account(name, new Money(cash));
        accountRepository.save(account);
        transactionLogRepository.save(new TransactionLog(0L, account.getId(), cash));
    }

    @Transactional
    @Override
    public void transfer(Long idFrom, Long idTo, Long cash) {
        try {
            Account accountFrom = accountRepository.getAccountById(idFrom);
            Account accountTo = accountRepository.getAccountById(idTo);
            transferService.transferMoney(accountFrom, accountTo, cash);
            status = "transfer is done";
        } catch (NullPointerException e) {
            status = "wrong account id";
        } catch (NotEnoughMoneyException e) {
            status = e.getMessage() + e.getCurrentMoney() +
                    "money have " + e.getNeededMoney();
        } catch (IllegalStateException e) {
            status = "database is unavaliable, try later";
        }
    }
}
