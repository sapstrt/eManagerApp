package com.sapstrt.emanager.service;

import android.content.Context;

import com.sapstrt.emanager.database.ExpenseDataSource;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.exception.ExpenseNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List<String> prepareListDataHeader(){
        List<String> headerList=new ArrayList<String>();
        List<Expense> expenseList=expenseDataSource.getAllExpense();
        for(Expense e:expenseList){
            headerList.add(e.getExpenseName());
        }
        return headerList;
    }

    @Override
    public HashMap<String,List<String>> prepareListDataMap(){
        HashMap<String,List<String>> ExpenseMap=new HashMap<String,List<String>>();
        List<Expense> expenseList=expenseDataSource.getAllExpense();
        for(Expense e:expenseList){
            List<String> childList=new ArrayList<String>();
            childList.add("Amount   :"+e.getAmount().toString());
            childList.add("Date         :"+e.getDate());
            childList.add("Location :"+e.getLocation());
            childList.add("Mode      :"+e.getMode());
            ExpenseMap.put(e.getExpenseName(),childList);
        }
        return ExpenseMap;
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
/*
childList.add("Amount   :"+e.getAmount().toString());
childList.add("Date         :"+e.getDate());
childList.add("Location :"+e.getLocation());
childList.add("Mode      :"+e.getMode());*/
