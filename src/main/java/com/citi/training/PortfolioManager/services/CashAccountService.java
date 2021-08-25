package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.entities.Transaction;

import java.util.List;

public interface CashAccountService {

    List<CashAccount> getAllCashAccounts();

    CashAccount getCashAccountById(int id);

    CashAccount addCashAccount(CashAccount acct);

    CashAccount updateCashAccount(CashAccount ca);

    void deleteCashAccount (CashAccount ca);

    List<Transaction> getTransactionList(int id);

}
