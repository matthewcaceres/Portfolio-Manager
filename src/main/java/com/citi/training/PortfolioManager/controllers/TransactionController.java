package com.citi.training.PortfolioManager.controllers;

import com.citi.training.PortfolioManager.entities.Transaction;
import com.citi.training.PortfolioManager.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Transaction getTransactionById (@PathVariable("id") int id) {
        Transaction transaction = service.getTransactionById(id);
        return transaction;
    }
}
