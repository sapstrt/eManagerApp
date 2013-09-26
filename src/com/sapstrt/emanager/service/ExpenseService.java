package com.sapstrt.emanager.service;


import com.sapstrt.emanager.domain.Expense;

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
