package com.service.account;

public interface AccountService {
        void add(String name, Long cash);

        void transfer(Long idFrom, Long idTo, Long cash);

        String getStatus();
}
