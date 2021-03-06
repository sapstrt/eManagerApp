package com.sapstrt.emanager.domain;

public class Expense {

    private Integer expenseId;
    private String expenseName;
    private String date;
    private Double amount;
    private String location;
    private String mode;
    private Integer grpId;
    private Integer userId;

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", expenseName='" + expenseName + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", location='" + location + '\'' +
                ", mode='" + mode + '\'' +
                ", grpId=" + grpId +
                ", userId=" + userId +
                '}';
    }
}
