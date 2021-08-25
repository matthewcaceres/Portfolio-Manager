package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    Transaction getTransactionById (int id);

    Transaction addTransaction (Transaction transaction);

    Transaction updateTransaction (Transaction transaction);

    void deleteTransaction (Transaction transaction);

}
