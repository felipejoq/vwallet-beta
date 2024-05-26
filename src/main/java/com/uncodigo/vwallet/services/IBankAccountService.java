package com.uncodigo.vwallet.services;

import com.uncodigo.vwallet.models.bankaccounts.BankAccount;

public interface IBankAccountService {
    BankAccount getBankAccountByUserId(int userId);
}
