package com.citi.training.PortfolioManager.controllers;

import com.citi.training.PortfolioManager.entities.Transaction;
import com.citi.training.PortfolioManager.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Transaction> getAllTransactions () {
        return service.getAllTransactions();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Transaction getTransactionById (@PathVariable("id") int id) {
        Transaction transaction = service.getTransactionById(id);
        return transaction;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return service.updateTransaction(transaction);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteTransaction (@RequestBody Transaction transaction) {
        service.deleteTransaction(transaction);
    }


}
