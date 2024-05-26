package com.uncodigo.vwallet.models.bankaccounts;

import java.util.Date;

public class Transaction {

    private Long id;
    private Double total;
    private Date date;

    // Many-To-One relationship with BankAccount
    private BankAccount senderBankAccount;

    // Many-To-One relationship with BankAccount
    private BankAccount receiverBankAccount;

    // One-To-One relationship with TransactionType
    private TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(Double total, Date date) {
        this.total = total;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BankAccount getSenderBankAccount() {
        return senderBankAccount;
    }

    public void setSenderBankAccount(BankAccount senderBankAccount) {
        this.senderBankAccount = senderBankAccount;
    }

    public BankAccount getReceiverBankAccount() {
        return receiverBankAccount;
    }

    public void setReceiverBankAccount(BankAccount receiverBankAccount) {
        this.receiverBankAccount = receiverBankAccount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
