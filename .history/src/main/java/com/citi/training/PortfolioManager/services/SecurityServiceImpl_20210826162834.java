package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.Security;
import com.citi.training.PortfolioManager.repos.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private SecurityRepository repository;


    public Security saveSecurity(Security security){
        return repository.save(security);
    }

    public List<Security> saveSecurities(List<Security> securities){
        return repository.saveAll(securities);
    }

    public List<Security> getSecurities(){
        return repository.findAll();
    }

    public Security getSecurityById(int id){
        Security security = repository.findById(id).get();
        return security;

    }

    public String getTicker


    public String deleteSecurity(Security security){
        repository.delete(security);
        return "Security removed !!" + security.getId();
    }

    public Security updateSecurity(Security security){
        Security existingSecurity = repository.findById(security.getId()).orElse(null);
        existingSecurity.setQuantity(security.getQuantity());
        return repository.save(existingSecurity);
    }


}
