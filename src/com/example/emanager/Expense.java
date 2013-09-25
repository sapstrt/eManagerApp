package com.example.emanager;


import java.sql.Date;

public class Expense {

    private  String expenseName;
    private Double amount;
    private Date date;
    private  String note;

    public Expense(String expenseName, Double amount, Date date, String note) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseName='" + expenseName + '\'' +
                ", amount='" + amount + '\'' +
                ", date=" + date +
                ", note='" + note + '\'' +
                '}';
    }

    public Expense() {
    }
}
