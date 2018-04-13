package com.exception;

public class NotEnoughMoneyException extends Exception {
    private Long money;

    public Long getMoney(){
        return money;
    }

    public NotEnoughMoneyException(String message, Long money){
        super(message);
        this.money = money;
    }
}
