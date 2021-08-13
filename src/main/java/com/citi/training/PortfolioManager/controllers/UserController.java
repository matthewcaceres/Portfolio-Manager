package com.citi.training.PortfolioManager.controllers;

import com.citi.training.PortfolioManager.entities.User;
import com.citi.training.PortfolioManager.services.UserService;
import com.citi.training.PortfolioManager.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
