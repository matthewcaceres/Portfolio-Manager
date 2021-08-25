package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.Transaction;
import com.citi.training.PortfolioManager.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository repository;

    @Override
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public Transaction getTransactionById(int id) {
        Transaction transaction = repository.findById(id).get();
        return transaction;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Transaction transactionToBeUpdated = repository.findById(transaction.getId()).orElse(null);
        transactionToBeUpdated.setValue(transaction.getValue());
        return repository.save(transactionToBeUpdated);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        repository.delete(transaction);
    }
}
