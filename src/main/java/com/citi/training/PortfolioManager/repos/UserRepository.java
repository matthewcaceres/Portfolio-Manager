package com.citi.training.PortfolioManager.repos;

import com.citi.training.PortfolioManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
