package com.citi.training.PortfolioManager.services;

import com.citi.training.PortfolioManager.entities.Security;

import java.util.List;

public interface SecurityService {

    Security saveSecurity(Security security);
    List<Security> saveSecurities(List<Security> securities);
    List<Security> getSecurities();
    Security getSecurityById(int id);
    String deleteSecurity(Security security);
    Security updateSecurity(Security security);

}
