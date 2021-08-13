package com.citi.training.PortfolioManager.entities;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.transaction.Transaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cash_account")
public class CashAccount implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @JoinColumn(name="cash_id", referencedColumnName="id")
    @OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    List<Transaction> transactionList = new ArrayList<>();

    @Column(name="total")
    private int total;

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


}
