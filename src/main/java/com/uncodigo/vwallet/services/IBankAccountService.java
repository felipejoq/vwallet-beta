package com.uncodigo.vwallet.services;

import com.uncodigo.vwallet.models.bankaccounts.BankAccount;

public interface IBankAccountService {
    /**
     * Get bank account by user id
     * @param userId user id
     * @return bank account
     */
    BankAccount getBankAccountByUserId(int userId);

    /**
     * Get bank account by account number
     * @param bankAccountId bank account id
     * @param amount amount to add
     * @return true if success, false otherwise
     */
    boolean addBalance(int bankAccountId, double amount);

    /**
     * Deduct balance from bank account
     * @param bankAccountId bank account id
     * @param amount amount to deduct
     * @return true if success, false otherwise
     */
    boolean deductBalance(int bankAccountId, double amount);
}
