package com.uncodigo.vwallet.services;

import com.uncodigo.vwallet.models.bankaccounts.Transaction;

public interface ITransactionService {
    /**
     * Get transaction by bank account id
     * @param bankAccountId bank account id
     * @return transaction
     */
    Transaction getTransactionByBankAccountId(int bankAccountId);

    /**
     * Get transaction by transaction id
     * @param transactionId transaction id
     * @return transaction
     */
    Transaction getTransactionById(int transactionId);

    /**
     * Create transaction
     * @param transaction transaction
     * @return transaction
     */
    Transaction createTransaction(Transaction transaction);

    /**
     * Deposit money to bank account
     * @param transaction transaction
     * @return transaction
     */
    Transaction deposit(Transaction transaction);

    /**
     * Withdraw money from bank account
     * @param transaction transaction
     * @return transaction
     */
    Transaction withdraw(Transaction transaction);

    /**
     * Transfer money to another bank account
     * @param transaction transaction
     * @return transaction
     */
    Transaction transfer(Transaction transaction);
}
