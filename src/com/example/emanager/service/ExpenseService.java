package com.example.emanager.service;

import android.content.Context;

import com.example.emanager.Expense;

import java.util.List;

/**
 * Created by cambas on 9/25/13.
 */
public interface ExpenseService {

    public List<Expense> getAllExpenses(Context context);
    public List<Expense> searchExpenseByName(Context context, String name);
    public void createNewExpense(Context context,Expense expense);
    public void updateExpense(Context context,Expense expense);
    public void deleteExpense(Context context,Expense expense);
}
