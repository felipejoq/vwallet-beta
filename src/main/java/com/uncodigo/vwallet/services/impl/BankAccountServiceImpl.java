package com.uncodigo.vwallet.services.impl;

import com.uncodigo.vwallet.dao.IBankAccountDao;
import com.uncodigo.vwallet.models.bankaccounts.BankAccount;
import com.uncodigo.vwallet.services.IBankAccountService;

public class BankAccountServiceImpl implements IBankAccountService {

    private final IBankAccountDao bankAccountDao;

    public BankAccountServiceImpl(IBankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public BankAccount getBankAccountByUserId(int userId) {
        return bankAccountDao.getBankAccountByUserId(userId);
    }

    @Override
    public boolean addBalance(int bankAccountId, double amount) {
        return bankAccountDao.addBalance(bankAccountId, amount);
    }

    @Override
    public boolean deductBalance(int bankAccountId, double amount) {
        return bankAccountDao.deductBalance(bankAccountId, amount);
    }
}
