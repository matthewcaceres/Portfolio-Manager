package com.citi.training.PortfolioManager.entities;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.citi.training.PortfolioManager.entities.Transaction;

@Entity
@Table(name="cash_account")
public class CashAccount implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private int userId;

    @JoinColumn(name="cash_id", referencedColumnName="id")
    @OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    List<Transaction> transactionList = new ArrayList<>();

    @Column(name="total")
    private int total;

    @Column(name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
