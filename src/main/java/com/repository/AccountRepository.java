package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountById(Long account_id);
}
