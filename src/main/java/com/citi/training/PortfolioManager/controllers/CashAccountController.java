package com.citi.training.PortfolioManager.controllers;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.services.CashAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cash_account")
@CrossOrigin
public class CashAccountController {

    @Autowired
    private CashAccountService service;

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public CashAccount getCashAccountById (@PathVariable("id") int id) {
        CashAccount cashAccount = service.getCashAccountById(id);
        return cashAccount;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void addNewCashAccount (@RequestBody CashAccount cashAccount) {
        service.addCashAccount(cashAccount);
    }

}
