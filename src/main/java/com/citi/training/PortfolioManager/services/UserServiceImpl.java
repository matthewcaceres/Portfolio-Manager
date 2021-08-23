package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.*;
import com.citi.training.PortfolioManager.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;


    public User getUserById(int id){
        User user = repository.findById(id).get();
        return user;
    }
    public double getNetWorth(int id) throws IOException {
        User user = repository.findById(id).get();
        double worth = 0;
        for(CashAccount ca : user.getCashAccountList()){
            worth+=ca.getTotal();
        }
        for(InvestmentAccount ia: user.getInvestmentAccountList()){
            for(Security security: ia.getSecurities()){
                Stock stock = YahooFinance.get(security.getSymbol());

                worth+=security.getQuantity() * stock.getQuote().getPrice().doubleValue();
            }
        }

        return worth;
    }

    public HashMap<String,Double> getMoversLosers(int id) throws IOException {
        User user = repository.findById(id).get();
        HashMap<String,Double> stocks = new HashMap<>();
        for(InvestmentAccount ia: user.getInvestmentAccountList()){
            for(Security security: ia.getSecurities()){
                stocks.put(security.getSymbol(),YahooFinance.get(security.getSymbol()).getQuote().getChangeInPercent().doubleValue());
            }
        }

        return sortMap(stocks);
    }

    public static HashMap<String, Double> sortMap(HashMap<String, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        int posvalues =0;
        int negvalues =0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getValue()<0 && negvalues!=5){
                negvalues+=1;
                temp.put(list.get(i).getKey(), list.get(i).getValue());
            }
            else
                break;

        }
        for (int i=list.size()-1;i>=0;i--) {
            if(list.get(i).getValue()>0 && posvalues!=5){
                posvalues+=1;
                temp.put(list.get(i).getKey(), list.get(i).getValue());
            }
            else
                break;
        }
        return temp;
    }

    public double getNetWorthSince(int id, String date) throws ParseException, IOException {

        LocalDate beforeDate= LocalDate.parse(date);
        User user = repository.findById(id).get();
        double worth = 0;
        for(CashAccount ca : user.getCashAccountList()){
            for(Transaction t: ca.getTransactionList()){
                if(t.getDate().isBefore(beforeDate)){
                    worth+=t.getValue();
                }
            }
        }
        for(InvestmentAccount ia: user.getInvestmentAccountList()){
            for(Security security: ia.getSecurities()){
                Stock stock = YahooFinance.get(security.getSymbol());
//                stock.getHistory()
                worth+=security.getQuantity() * stock.getQuote().getPrice().doubleValue();
            }
        }

        return worth;
    }

    public List<CashAccount> getCashAccounts(int id){
        User user = repository.findById(id).get();
        return user.getCashAccountList();
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    public List<InvestmentAccount> getInvestmentAccounts(int id){
        User user = repository.findById(id).get();
        return user.getInvestmentAccountList();
    }



    public User addUser(User user){
        return repository.save(user);
    }



    @Override
    public User addInvestMentAccount(InvestmentAccount inv, int id) {
        User user = repository.findById(id).get();
        user.getInvestmentAccountList().add(inv);
        return repository.save(user);
    }

    @Override
    public User addCashAccount(CashAccount ca, int id) {
        User user = repository.findById(id).get();
        user.getCashAccountList().add(ca);
        return repository.save(user);
    }

    public User getByName(String name){
        return repository.findByName(name);
    }

    public User updateUser(int id, User user){
        User original = repository.getById(id);
        original.setName(user.getName());
        return repository.save(original);
    }

    public boolean deleteUser(int id){
        if(repository.getById(id)!=null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
