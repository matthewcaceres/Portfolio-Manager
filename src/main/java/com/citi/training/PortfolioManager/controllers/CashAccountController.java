package com.citi.training.PortfolioManager.controllers;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.services.CashAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cash_account")
@CrossOrigin(origins = "*")
public class CashAccountController {

    @Autowired
    private CashAccountService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CashAccount getCashAccountById(@PathVariable("id") int id) {
        CashAccount cashAccount = service.getCashAccountById(id);
        return cashAccount;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNewCashAccount(@RequestBody CashAccount cashAccount) {

        service.addCashAccount(cashAccount);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteCashAccount(@RequestBody CashAccount ca) {
        service.deleteCashAccount(ca);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateCashAccount(@RequestBody CashAccount ca) {
        service.updateCashAccount(ca);
    }
}
