package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import com.citi.training.PortfolioManager.entities.Security;
import com.citi.training.PortfolioManager.entities.User;
import com.citi.training.PortfolioManager.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;

    public User getUserById(int id){
        User user = repository.findById(id).get();
        return user;
    }
    public int getNetWorth(int id){
        User user = repository.findById(id).get();
        int worth = 0;
        for(CashAccount ca : user.getCashAccountList()){
            worth+=ca.getTotal();
        }
        for(InvestmentAccount ia: user.getInvestmentAccountList()){
            for(Security security: ia.getSecurities()){
                //get 5 placeholder... get value from api
                worth+=security.getQuantity() * 5;
            }
        }

        return worth;
    }

    public List<CashAccount> getCashAccounts(int id){
        User user = repository.findById(id).get();
        return user.getCashAccountList();
    }

    public List<InvestmentAccount> getInvestmentAccounts(int id){
        User user = repository.findById(id).get();
        return user.getInvestmentAccountList();
    }





}
