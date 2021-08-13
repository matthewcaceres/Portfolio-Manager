package com.citi.training.PortfolioManager.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @JoinColumn(name="user_id", referencedColumnName="id")
    @OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    List<CashAccount> cashAccountList = new ArrayList<>();

    @JoinColumn(name="user_id", referencedColumnName="id")
    @OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    List<InvestmentAccount> investmentAccountList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CashAccount> getCashAccountList() {
        return cashAccountList;
    }

    public void setCashAccountList(List<CashAccount> cashAccountList) {
        this.cashAccountList = cashAccountList;
    }

    public List<InvestmentAccount> getInvestmentAccountList() {
        return investmentAccountList;
    }

    public void setInvestmentAccountList(List<InvestmentAccount> investmentAccountList) {
        this.investmentAccountList = investmentAccountList;
    }
}
