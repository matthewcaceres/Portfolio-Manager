package com.citi.training.PortfolioManager.controllers;


import com.citi.training.PortfolioManager.entities.InvestmentAccount;
import com.citi.training.PortfolioManager.entities.Security;
import com.citi.training.PortfolioManager.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
@CrossOrigin(origins = "*")
public class SecurityController {

  @Autowired
  private SecurityService service;

  @PostMapping("/add")
  public Security addSecurity(@RequestBody Security security) {
      return service.saveSecurity(security);
  }
  @DeleteMapping("/delete")
  public String deleteSecurity(@RequestBody Security security){
      return service.deleteSecurity(security);
  }
  @PostMapping("/addSecurities")
  public List<Security> addSecurities(@RequestBody List<Security> securities){
      return service.saveSecurities(securities);
  }
  @GetMapping("/findAll")
  public List<Security> findAllSecurities(){
      return service.getSecurities();
  }

  @GetMapping("/{id}")
  public Security findSecurityUsingId(@PathVariable int id){
      Security security = service.getSecurityById(id);
      return security;
  }

  @PutMapping("/update")
  public Security updateSecurity(@RequestBody Security security){
      return service.updateSecurity(security);
  }





}
