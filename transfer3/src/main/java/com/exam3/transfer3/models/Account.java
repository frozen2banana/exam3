package com.exam3.transfer3.models;

import java.math.BigDecimal;

public class Account {
    private String accountId;
    private String name;
    private BigDecimal balance;

    
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public Account() {
    }
    public Account(String accountId, String name, BigDecimal balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    
}
