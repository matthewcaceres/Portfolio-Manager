package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import com.citi.training.PortfolioManager.entities.Security;
import com.citi.training.PortfolioManager.entities.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    User getUserById(int id);
    double getNetWorth(int id) throws IOException;
    double getNetWorthSince(int id, String beforeDate) throws ParseException, IOException;

    List<CashAccount> getCashAccounts(int id);

    List<User> getAll();

    List<InvestmentAccount> getInvestmentAccounts(int id);

    User addCashAccount(CashAccount ca, int id);
    User addInvestMentAccount(InvestmentAccount inv, int id);
    User addUser(User user);
    User getByName(String name);
    User updateUser(int id,User user);
    boolean deleteUser(int id);
    HashMap<String,Double> getMoversLosers(int id) throws IOException;
    HashMap<String,Double> getIndicesToday() throws IOException;
    HashMap<String,Double> getIndicesYTD() throws IOException;
    HashMap<String,Double> getIndicesWeek() throws IOException;
    HashMap<String,Double> getIndicesMonth() throws IOException;
    HashMap<String,Double> getIndicesMonth3() throws IOException;
    List<Double> getNetWorthTime(int id, String time) throws IOException;
}
