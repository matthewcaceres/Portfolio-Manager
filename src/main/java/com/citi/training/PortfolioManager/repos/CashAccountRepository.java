package com.citi.training.PortfolioManager.repos;

import com.citi.training.PortfolioManager.entities.CashAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashAccountRepository  extends JpaRepository<CashAccount, Integer> {
}
