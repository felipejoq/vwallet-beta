package com.uncodigo.vwallet.dao;

import com.uncodigo.vwallet.models.bankaccounts.BankAccount;

public interface IBankAccountDao {
    BankAccount getBankAccountByUserId(int userId);
    boolean addBalance(int bankAccountId, double amount);
    boolean deductBalance(int bankAccountId, double amount);
}
