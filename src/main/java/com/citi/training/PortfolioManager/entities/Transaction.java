package com.citi.training.PortfolioManager.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column (name="cash_id")
    private int cashId;

    @Column(name="value")
    private double value;

    @Column(name="date")
    private LocalDate date;

    //what was it spend on and where it came from
    @Column(name="spent_on")
    private String spentOn;

    @Column(name="came_from")
    private String cameFrom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSpentOn() {
        return spentOn;
    }

    public void setSpentOn(String spentOn) {
        this.spentOn = spentOn;
    }

    public String getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(String cameFrom) {
        this.cameFrom = cameFrom;
    }
}
