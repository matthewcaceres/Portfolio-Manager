package com.citi.training.PortfolioManager.controllers;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import com.citi.training.PortfolioManager.entities.User;
import com.citi.training.PortfolioManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(value="/{id}")
    public User getUser(@PathVariable("id") int id){
        User user =  service.getUserById(id);
        return user;

    }

    @GetMapping(value="")
    public User getUser(@RequestParam String name){
        User user =  service.getByName(name);
        return user;

    }
    @GetMapping(value="/{id}/networth")
    public Double getNetworth(@PathVariable("id") int id,@RequestParam(required = false) String date){
        if(date==null) {
            double worth = 0;
            try {
                worth = service.getNetWorth(id);
            } catch (IOException e) {
                return null;
            }
            return worth;
        }
        else{
            double worth = 0;
            try {
                worth = service.getNetWorthSince(id, date);
            } catch (ParseException | IOException e) {
                return null;
            }
            return worth;
        }

    }



    @GetMapping(value="/{id}/invest")
    public List<InvestmentAccount> getInvestmentAccounts(@PathVariable("id") int id){
        return service.getInvestmentAccounts(id);

    }

    @GetMapping(value="/{id}/cash")
    public List<CashAccount> getCashAccounts(@PathVariable("id") int id){
        return service.getCashAccounts(id);

    }

    @GetMapping(value="/all")
    public List<User> getUsers(){
        return service.getAll();

    }

    @PostMapping(value="/{id}/invest", consumes="application/json")
    public ResponseEntity<User> addInvestmentAccounts(@PathVariable("id") int id, InvestmentAccount inv){
        User u = service.addInvestMentAccount(inv, id);
        return new ResponseEntity<>(u, HttpStatus.CREATED);

    }

    @PostMapping(value="/{id}/cash", consumes="application/json")
    public ResponseEntity<User> addCashAccounts(@PathVariable("id") int id, @RequestBody CashAccount ca){
        User u = service.addCashAccount(ca,id);
        return new ResponseEntity<>(u, HttpStatus.CREATED);

    }

    @PostMapping(value="", consumes="application/json")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User u = service.addUser(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);

    }


}
