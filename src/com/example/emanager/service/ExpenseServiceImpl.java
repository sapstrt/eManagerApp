package com.example.emanager.service;

import android.content.Context;

import com.example.emanager.Expense;
import com.example.emanager.ExpenseNotFoundException;

import java.util.ArrayList;
import java.util.List;

import database.ExpenseDataSource;

/**
 * Created by cambas on 9/25/13.
 */
public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDataSource expenseDataSource;

    @Override
    public List<Expense> getAllExpenses(Context context) {
        expenseDataSource=new ExpenseDataSource(context);
        List<Expense> expenseList=new ArrayList<Expense>();
        expenseList=expenseDataSource.getAllExpense();
        if(expenseList.isEmpty()){
            return null;
        }
        else
        {
            return expenseList;
        }

    }

    @Override
    public List<Expense> searchExpenseByName(Context context, String name) {
        expenseDataSource=new ExpenseDataSource(context);
        List<Expense> expenseList=new ArrayList<Expense>();
        expenseList=expenseDataSource.getExpenseByName(name);
        if(expenseList.isEmpty()){
            return null;
        }
        else
        {
            return expenseList;
        }
    }

    @Override
    public void createNewExpense(Context context, Expense expense) {
        expenseDataSource=new ExpenseDataSource(context);
        if(null!=expense)
        expenseDataSource.createExpense(expense);
        else
            throw new ExpenseNotFoundException(("Expense Not Found"));

    }

    @Override
    public void updateExpense(Context context, Expense expense) {
        expenseDataSource=new ExpenseDataSource(context);
        if(null!=expense)
            expenseDataSource.editExpense(expense);
        else
            throw new ExpenseNotFoundException(("Expense Not Found"));

    }

    @Override
    public void deleteExpense(Context context, Expense expense) {
        if (null!=expense)
        {
            Integer expenseId=expense.getExpenseId();
            expenseDataSource=new ExpenseDataSource(context);
            expenseDataSource.deleteExpense(expenseId);
        }
        else
            throw new ExpenseNotFoundException("Expense Not Found");


    }
}
