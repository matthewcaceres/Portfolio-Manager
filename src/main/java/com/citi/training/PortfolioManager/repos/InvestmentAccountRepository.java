package com.citi.training.PortfolioManager.repos;

import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Integer> {
}
