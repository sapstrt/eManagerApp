package com.example.emanager.service;

import android.content.Context;

import com.example.emanager.Expense;

import java.util.List;

/**
 * Created by cambas on 9/25/13.
 */
public interface ExpenseService {

    public List<Expense> getAllExpenses();
    public List<Expense> searchExpenseByName( String name);
    public void createNewExpense(Expense expense);
    public void updateExpense(Expense expense);
    public void deleteExpense(Expense expense);
}
