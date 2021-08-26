package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.*;
import com.citi.training.PortfolioManager.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

    public List<Double> getNetWorthTime(int id, String time) throws IOException {
        User user = repository.findById(id).get();
        HashMap<String,Double> map = new HashMap<>();
        ArrayList<String> tickers = new ArrayList<>();
        HashMap<String,Integer> tickerMap = new HashMap<>();
        for(InvestmentAccount ia: user.getInvestmentAccountList()){
            for(Security security: ia.getSecurities()){
                tickers.add(security.getSymbol());
                tickerMap.put(security.getSymbol(),security.getQuantity());
            }
        }
        Calendar calendar = new GregorianCalendar();
        LocalDate date = null;
        int days=0;
        switch(time){
            case "week":
                date = LocalDate.now().minusDays(7);
                days=7;
                break;
            case "month":
                date = LocalDate.now().minusDays(28);
                days=28;
                break;
            case "quarter":
                date = LocalDate.now().minusDays(28*3);
                days=28*3;
                break;
            case "year":
                date = LocalDate.of(2021, Month.JANUARY,4);
                long daysBetwwn = ChronoUnit.DAYS.between(date,LocalDate.now());
                days = (int) daysBetwwn;
                break;
            default:
                date = LocalDate.now().minusDays(7);
                break;
        }
        if(time.equals("year"))
            calendar.set(2021,0,4);
        else
            calendar.set(2021,date.getMonthValue()-1,date.getDayOfMonth());


        String[] tickersArr= new String[tickers.size()];
        tickersArr = tickers.toArray(tickersArr);
        Map<String,Stock> stocksTickers = YahooFinance.get(tickersArr);
        for(String ticker: tickers){
            List<HistoricalQuote> history = stocksTickers.get(ticker).getHistory(calendar,Interval.DAILY);
            for(HistoricalQuote h : history){
                String dateF =format((GregorianCalendar) h.getDate());
                Double val = map.get(dateF);
                if(val==null)
                    map.put(dateF,h.getClose().doubleValue() * tickerMap.get(ticker));
                else
                    map.put(dateF,val + (h.getClose().doubleValue() *tickerMap.get(ticker)));
            }

        }

        GregorianCalendar calendar2 = new GregorianCalendar();
        LocalDate date2 = LocalDate.now();
        calendar2.set(2021,date2.getMonthValue()-1,date2.getDayOfMonth());
        String dateToday = format(calendar2);
        Double val = map.get(dateToday);
        if(val==null){
            double worth = 0;
            for(String ticker: tickers){
                worth += YahooFinance.get(ticker).getQuote().getPrice().doubleValue() * tickerMap.get(ticker);
            }
            map.put(dateToday,worth);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String oldDate=date.format(formatter);
        int i =0;
        while(i<=days){
            double worth =0;
            for(CashAccount ca : user.getCashAccountList()){
                for(Transaction t: ca.getTransactionList()){
                    if(t.getDate().isBefore(date)){
                        worth+=t.getValue();
                    }
                }
            }
             val = map.get(date.format(formatter));
            if(val==null){
                map.put(date.format(formatter),map.get(oldDate));
            }
            else {

                map.put(date.format(formatter), val + worth);
            }
            oldDate=date.format(formatter);
            date = date.plusDays(1);
            i++;

        }



        return sortMapDate(map);

    }

    public static String format(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());

        return dateFormatted;
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
    public HashMap<String,Double> getIndicesToday() throws IOException {
        HashMap<String,Double> stocks = new HashMap<>();
        String[] tickers = {"^DJI","^GSPC","^IXIC","^TNX"};
        for(String ticker: tickers){
            stocks.put(ticker,YahooFinance.get(ticker).getQuote().getChangeInPercent().doubleValue());
        }


        return stocks;

    }

    public HashMap<String,Double> getIndicesYTD() throws IOException {
        HashMap<String,Double> stocks = new HashMap<>();

        Calendar calendar = new GregorianCalendar();
        calendar.set(2021,0,4);
        String[] tickers = {"^DJI","^GSPC","^IXIC","^TNX"};
        Map<String,Stock> stocksTickers = YahooFinance.get(tickers);
        for(String ticker: tickers){
            List<HistoricalQuote> history = stocksTickers.get(ticker).getHistory(calendar,Interval.WEEKLY);
            Double change = ((YahooFinance.get(ticker).getQuote().getPrice().doubleValue() - history.get(0).getClose().doubleValue())/YahooFinance.get(ticker).getQuote().getPrice().doubleValue())*100;
            stocks.put(ticker,change);
        }


        return stocks;

    }

    public HashMap<String,Double> getIndicesWeek() throws IOException {
        HashMap<String,Double> stocks = new HashMap<>();

        Calendar calendar = new GregorianCalendar();
        LocalDate date = LocalDate.now().minusDays(7);


        calendar.set(2021,date.getMonthValue()-1,date.getDayOfMonth()-1);
        String[] tickers = {"^DJI","^GSPC","^IXIC","^TNX"};
        Map<String,Stock> stocksTickers = YahooFinance.get(tickers);
        for(String ticker: tickers){
            List<HistoricalQuote> history = stocksTickers.get(ticker).getHistory(calendar,Interval.WEEKLY);
            Double change = ((YahooFinance.get(ticker).getQuote().getPrice().doubleValue() - history.get(0).getClose().doubleValue())/YahooFinance.get(ticker).getQuote().getPrice().doubleValue())*100;
            stocks.put(ticker,change);
        }


        return stocks;

    }

    public HashMap<String,Double> getIndicesMonth() throws IOException {
        HashMap<String,Double> stocks = new HashMap<>();

        Calendar calendar = new GregorianCalendar();
        LocalDate date = LocalDate.now().minusDays(28);


        calendar.set(2021,date.getMonthValue()-1,date.getDayOfMonth()-1);
        String[] tickers = {"^DJI","^GSPC","^IXIC","^TNX"};
        Map<String,Stock> stocksTickers = YahooFinance.get(tickers);
        for(String ticker: tickers){
            List<HistoricalQuote> history = stocksTickers.get(ticker).getHistory(calendar,Interval.WEEKLY);
            Double change = ((YahooFinance.get(ticker).getQuote().getPrice().doubleValue() - history.get(0).getClose().doubleValue())/YahooFinance.get(ticker).getQuote().getPrice().doubleValue())*100;
            stocks.put(ticker,change);
        }


        return stocks;

    }

    public HashMap<String,Double> getIndicesMonth3() throws IOException {
        HashMap<String,Double> stocks = new HashMap<>();

        Calendar calendar = new GregorianCalendar();
        LocalDate date = LocalDate.now().minusDays(28*3);


        calendar.set(2021,date.getMonthValue()-1,date.getDayOfMonth()-1);
        String[] tickers = {"^DJI","^GSPC","^IXIC","^TNX"};
        Map<String,Stock> stocksTickers = YahooFinance.get(tickers);
        for(String ticker: tickers){
            List<HistoricalQuote> history = stocksTickers.get(ticker).getHistory(calendar,Interval.WEEKLY);
            Double change = ((YahooFinance.get(ticker).getQuote().getPrice().doubleValue() - history.get(0).getClose().doubleValue())/YahooFinance.get(ticker).getQuote().getPrice().doubleValue())*100;
            stocks.put(ticker,change);
        }


        return stocks;

    }


    public static HashMap<String, Double> sortMap(HashMap<String, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        int posvalues = 0;
        int negvalues = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue() < 0 && negvalues != 5) {
                negvalues += 1;
                temp.put(list.get(i).getKey(), list.get(i).getValue());
            } else
                break;

        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getValue() > 0 && posvalues != 5) {
                posvalues += 1;
                temp.put(list.get(i).getKey(), list.get(i).getValue());
            } else
                break;
        }
        return temp;
    }
        public static List<Double> sortMapDate(HashMap<String, Double> hm)
        {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            // Create a list from elements of HashMap
            List<Map.Entry<String, Double> > list =
                    new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

            // Sort the list
            Collections.sort(list, Comparator.comparing(o -> LocalDate.parse(o.getKey(), formatter)));

            // put data from sorted list to hashmap
            List< Double> temp = new LinkedList<Double>();

            for (int i=0;i<list.size();i++) {
               temp.add((double)Math.round(list.get(i).getValue()*100)/100);

            }

            return temp;
    }





    public List<CashAccount> getCashFlow(int id, LocalDate date){
        User user = repository.findById(id).get();
        List<CashAccount> accounts= new ArrayList<CashAccount>();
        List<CashAccount> userAccounts = user.getCashAccountList();
        for(CashAccount ca: userAccounts){
            CashAccount ac = new CashAccount();
            ac.setUserId(ca.getUserId());
            ac.setName(ca.getName());
            ac.setId(ca.getId());
            ac.setTotal(ca.getTotal());
            List<Transaction> transactions = new ArrayList<>();
            ac.setTransactionList(transactions);
            for(Transaction t: ca.getTransactionList()){
                if(t.getDate().isAfter(date)) {
                    Transaction copy = new Transaction();
                    copy.setCashId(t.getCashId());
                    copy.setId(t.getId());
                    copy.setValue(t.getValue());
                    copy.setCameFrom(t.getCameFrom());
                    copy.setSpentOn(t.getSpentOn());
                    copy.setDate(t.getDate());
                    transactions.add(t);
                }
            }
            accounts.add(ac);
        }

        return accounts;
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

    public List<CashAccount> getCashAccounts(int id){
        User original = repository.getById(id);
        return original.getCashAccountList();
    }

    public boolean deleteUser(int id){
        if(repository.getById(id)!=null){
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
