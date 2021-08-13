package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.CashAccount;

public interface CashAccountService {

    CashAccount getCashAccountById(int id);

    CashAccount addCashAccount(CashAccount acct);



}
