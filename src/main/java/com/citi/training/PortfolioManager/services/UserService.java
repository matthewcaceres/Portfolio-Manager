package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import com.citi.training.PortfolioManager.entities.Security;
import com.citi.training.PortfolioManager.entities.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    int getNetWorth(int id);

    List<CashAccount> getCashAccounts(int id);

    List<InvestmentAccount> getInvestmentAccounts(int id);
}
