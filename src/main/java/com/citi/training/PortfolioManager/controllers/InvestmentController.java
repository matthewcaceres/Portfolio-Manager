package com.citi.training.PortfolioManager.controllers;


import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import com.citi.training.PortfolioManager.entities.Security;
import com.citi.training.PortfolioManager.entities.User;
import com.citi.training.PortfolioManager.services.InvestService;
import com.citi.training.PortfolioManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invest")
public class InvestmentController {
    @Autowired
    private InvestService service;

    @PostMapping("/add")
    public InvestmentAccount addAccount(@RequestBody InvestmentAccount account) {
        return service.SaveInvestAccount(account);
    }
    @DeleteMapping("/delete")
    public String deleteAccount(@RequestBody InvestmentAccount account){
        return service.deleteInvestAccount(account);
    }
    @GetMapping("/{id}")
    public InvestmentAccount findAccountUsingId(@PathVariable int id){
        InvestmentAccount account = service.getInvestAccountById(id);
        return account;
    }
    @PutMapping("/update")
    public InvestmentAccount updateAccount(@RequestBody InvestmentAccount account){
        return service.updateInvestAccount(account);
    }



}
