package com.uncodigo.vwallet.dao;

import com.uncodigo.vwallet.models.bankaccounts.Transaction;

public interface ITransactionDao {
    Transaction getTransactionByBankAccountId(int bankAccountId);
    Transaction getTransactionById(int transactionId);
    Transaction createTransaction(Transaction transaction);
    Transaction deposit(Transaction transaction);
    Transaction withdraw(Transaction transaction);
    Transaction transfer(Transaction transaction);
}
