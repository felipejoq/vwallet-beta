package com.uncodigo.vwallet.models.bankaccounts;

import com.uncodigo.vwallet.models.users.User;

import java.util.List;

public class BankAccount {

    private int id;

    private String accountNumber;
    private Float balance;

    // One-To-One relationship with User
    private User user;

    // One-To-One relationship with Currency
    private Currency currency;

    // One-To-Many relationship with Transaction
    private List<Transaction> transactions;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, Float balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
