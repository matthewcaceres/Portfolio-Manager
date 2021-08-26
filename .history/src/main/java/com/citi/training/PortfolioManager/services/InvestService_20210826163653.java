package com.citi.training.PortfolioManager.services;


import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import com.citi.training.PortfolioManager.entities.Security;


import java.util.List;

public interface InvestService {

    InvestmentAccount getInvestAccountById(int id);

    InvestmentAccount SaveInvestAccount(InvestmentAccount account);

    String deleteInvestAccount (InvestmentAccount account);

    InvestmentAccount updateInvestAccount(InvestmentAccount account);

    double accountTotal(InvestmentAccount account);



}
