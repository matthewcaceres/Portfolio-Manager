package com.citi.training.PortfolioManager.repos;

import com.citi.training.PortfolioManager.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
