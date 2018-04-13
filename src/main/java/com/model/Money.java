package com.model;

import javax.persistence.*;

@Entity
@Table(name="money")
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_id")
    private Long id;
    @OneToOne(mappedBy = "money")
    private Account account;
    private Long cash;


    public Money() {
    }

    public Money(Long cash) {
        this.cash = cash;
    }

    public Money(Account account, Long cash) {
        this.account = account;
        this.cash = cash;
        this.id = account.getId();
    }

    public Account getAccount() {
        return account;
    }


    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCash() {
        return cash;
    }

    public void setCash(Long cash) {
        this.cash = cash;
    }
}
