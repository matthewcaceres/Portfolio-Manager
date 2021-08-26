package com.citi.training.PortfolioManager.services;


import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import com.citi.training.PortfolioManager.entities.Security;
import com.citi.training.PortfolioManager.repos.InvestmentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional

public class InvestServiceImpl implements InvestService{

    @Autowired
    private InvestmentAccountRepository repository;


    public InvestmentAccount getInvestAccountById(int id) {
       InvestmentAccount account = repository.findById(id).get();
       return account;
    }

    public InvestmentAccount SaveInvestAccount(InvestmentAccount account) {
        return repository.save(account);
    }

    public String deleteInvestAccount (InvestmentAccount account){
        repository.delete(account);
        return "Account removed" + account.getId();

    }

    public InvestmentAccount updateInvestAccount(InvestmentAccount account){
        InvestmentAccount existingAccount = repository.findById(account.getId()).orElse(null);
        existingAccount.setSecurities(account.getSecurities());
        return repository.save(existingAccount);
    }

    public double accountTotal(InvestmentAccount account){
        double sum=0; 
        for(Security security: account.getSecurities()){
            sum+=YahooFinance.get(t.getTicker()).getQuote().getPrice().doubleValue()     
        }
    }
   





}