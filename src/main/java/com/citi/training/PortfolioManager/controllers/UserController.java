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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
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
//    @GetMapping(value="/{id}/networth")
//    public Double getNetworth(@PathVariable("id") int id,@RequestParam(required = false) String date){
//        if(date==null) {
//            double worth = 0;
//            try {
//                worth = service.getNetWorth(id);
//            } catch (IOException e) {
//                return null;
//            }
//            return worth;
//        }
//        else{
//            double worth = 0;
//            try {
//                worth = service.getNetWorthSince(id, date);
//            } catch (ParseException | IOException e) {
//                return null;
//            }
//            return worth;
//        }
//
//    }
    @GetMapping(value="/{id}/networth")
    public List<Double> getNetworthWeek(@PathVariable("id") int id,@RequestParam(required = false) String time){
        List<Double> list = null;
        try {
            list = service.getNetWorthTime(id, time);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;

    }

    @GetMapping(value="/{id}/movers")
    public HashMap getMovers(@PathVariable("id") int id){
        HashMap<String,Double> map = null;
        try {
            map = service.getMoversLosers(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

    @GetMapping(value="/indices" )
    public HashMap getIndices(@RequestParam(required = false) String time){
        HashMap<String,Double> map = null;
        try {
        switch (time){
            case "YTD":
                map = service.getIndicesYTD();
                break;
            case "3month":
                map = service.getIndicesMonth3();
                break;
            case "month":
                map = service.getIndicesMonth();
                break;
            case "week":
                map = service.getIndicesWeek();
                break;
            default:
                map = service.getIndicesToday();
                break;
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }



    @GetMapping(value="/{id}/invest")
    public List<InvestmentAccount> getInvestmentAccounts(@PathVariable("id") int id){
        return service.getInvestmentAccounts(id);

    }

    @GetMapping(value="/{id}/cash")
    public List<CashAccount> getCashAccounts(@PathVariable("id") int id,@RequestParam(required = false) String time){
        if(time==null){
            return service.getCashAccounts(1);
        }
        LocalDate date = LocalDate.parse(time);
        return service.getCashFlow(id,date);

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

    @PutMapping(value="/{id}", consumes="application/json")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user){
        return new ResponseEntity<>(service.updateUser(id,user),HttpStatus.OK);

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteUser(@PathVariable("id")int id){
         if(service.deleteUser(id))
             return new ResponseEntity(HttpStatus.OK);
         return new ResponseEntity(HttpStatus.NOT_FOUND);

    }


}
