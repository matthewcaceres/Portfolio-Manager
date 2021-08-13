package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.Transaction;

public interface TransactionService {

    Transaction getTransactionById (int id);

    Transaction addTransaction (Transaction transaction);

    Transaction updateTransaction (Transaction transaction);

    void deleteTransaction (Transaction transaction);

}
