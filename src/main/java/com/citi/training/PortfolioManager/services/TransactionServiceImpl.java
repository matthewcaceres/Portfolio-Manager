package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.Transaction;
import com.citi.training.PortfolioManager.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction getTransactionById(int id) {
        Transaction transaction = repository.findById(id).get();
        return transaction;
    }
}
