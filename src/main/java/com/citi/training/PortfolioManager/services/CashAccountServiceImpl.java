package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.entities.Transaction;
import com.citi.training.PortfolioManager.repos.CashAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashAccountServiceImpl implements CashAccountService{

    @Autowired
    private CashAccountRepository repository;

    @Override
    public CashAccount getCashAccountById(int id) {
        Optional<CashAccount> cashAccount =repository.findById(id);
        if(cashAccount.isPresent()){
            return cashAccount.get();
        }
        else return null;
    }

    @Override
    public CashAccount addCashAccount(CashAccount acct) {
        acct.setId(0);
        return repository.save(acct);
    }

    @Override
    public void deleteCashAccount(CashAccount ca) {
        repository.delete(ca);
    }

    @Override
    public CashAccount updateCashAccount(CashAccount ca) {
        return repository.save(ca);
    }

    @Override
    public List<Transaction> getTransactionList(int id) {
        CashAccount ca = repository.findById(id).get();
        return ca.getTransactionList();
    }

}
