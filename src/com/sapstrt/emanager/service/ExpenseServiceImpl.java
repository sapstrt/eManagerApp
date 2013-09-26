package com.sapstrt.emanager.service;

import android.content.Context;

import com.sapstrt.emanager.database.ExpenseDataSource;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.exception.ExpenseNotFoundException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cambas on 9/25/13.
 */
public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseDataSource expenseDataSource;
    public ExpenseServiceImpl(Context context) {
        expenseDataSource = new ExpenseDataSource(context);
    }
    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expenseList = new ArrayList<Expense>();
        expenseList = expenseDataSource.getAllExpense();
        return expenseList;
    }
    @Override
    public List<Expense> searchExpenseByName(String name) {
        List<Expense> expenseList = new ArrayList<Expense>();
        expenseList = expenseDataSource.getExpenseByName(name);
        if (expenseList.isEmpty()) {
            return null;
        } else {
            return expenseList;
        }
    }

    @Override
    public void createNewExpense(Expense expense) {
        if (null != expense)
            expenseDataSource.createExpense(expense);
        else
            throw new ExpenseNotFoundException  (("Expense Not Found"));
    }

    @Override
    public void updateExpense(Expense expense) {
        if (null != expense)
            expenseDataSource.editExpense(expense);
        else
            throw new ExpenseNotFoundException(("Expense Not Found"));
    }

    @Override
    public void deleteExpense(Expense expense) {
        if (null != expense) {
            Integer expenseId = expense.getExpenseId();
            expenseDataSource.deleteExpense(expenseId);
        } else
            throw new ExpenseNotFoundException("Expense Not Found");
    }
}
