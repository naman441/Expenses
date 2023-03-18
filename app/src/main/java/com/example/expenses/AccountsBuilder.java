package com.example.expenses;

import java.util.Date;

public class AccountsBuilder {

    private Accounts accounts;

    public AccountsBuilder(){
        accounts = new Accounts();
    }

    public AccountsBuilder withName(String name){
        this.accounts.setAccountName(name);
        return this;
    }

    public AccountsBuilder withOwner(String owner){
        this.accounts.setOwnerName(owner);
        return this;
    }

    public AccountsBuilder withCreatedOn(Date date){
        this.accounts.setCreatedOn(date);
        return this;
    }

    public AccountsBuilder isActive(boolean active){
        this.accounts.setActive(active);
        return this;
    }

    public AccountsBuilder withBalance(double balance){
        this.accounts.setCurrentBalance(balance);
        return this;
    }

    public Accounts build(){
        return this.accounts;
    }
}
