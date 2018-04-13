package com.exception;

public class NotEnoughMoneyException extends Exception {
    private Long currentMoney;
    private Long neededMoney;

    public Long getCurrentMoney() {
        return currentMoney;
    }

    public Long getNeededMoney(){
        return neededMoney;
    }

    public NotEnoughMoneyException(String message, Long currentMoney, Long neededMoney) {
        super(message);
        this.currentMoney = currentMoney;
        this.neededMoney = neededMoney;
    }
}
