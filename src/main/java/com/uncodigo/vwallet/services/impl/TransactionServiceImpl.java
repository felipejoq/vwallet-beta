package com.uncodigo.vwallet.services.impl;

import com.uncodigo.vwallet.dao.ITransactionDao;
import com.uncodigo.vwallet.models.bankaccounts.Transaction;
import com.uncodigo.vwallet.services.ITransactionService;

public class TransactionServiceImpl implements ITransactionService {

    ITransactionDao transactionDao;

    public TransactionServiceImpl(ITransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public Transaction getTransactionByBankAccountId(int bankAccountId) {
        return null;
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction deposit(Transaction transaction) {
        return this.transactionDao.deposit(transaction);
    }

    @Override
    public Transaction withdraw(Transaction transaction) {
        return this.transactionDao.withdraw(transaction);
    }

    @Override
    public Transaction transfer(Transaction transaction) {
        return null;
    }
}
