package com.sapstrt.emanager.service.expense;

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



    public List<String> prepareListDataHeader(){
        List<Expense> expenseList=expenseDataSource.getAllExpense();
        List<String> expenseNameList =new ArrayList<String>();
        for(Expense e:expenseList){
            expenseNameList.add(e.getExpenseName());
        }
        return expenseNameList;
    }


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

    public List<Expense> getAllExpenses() {
        List<Expense> expenseList = new ArrayList<Expense>();
        expenseList = expenseDataSource.getAllExpense();
        return expenseList;
    }

    public List<Expense> searchExpenseByName(String name) {
        List<Expense> expenseList = new ArrayList<Expense>();
        expenseList = expenseDataSource.getExpenseByName(name);
        if (expenseList.isEmpty()) {
            return null;
        } else {
            return expenseList;
        }
    }


    public void createNewExpense(Expense expense) {
        if (null != expense)
            expenseDataSource.createExpense(expense);
        else
            throw new ExpenseNotFoundException  (("Expense Not Found"));
    }


    public void updateExpense(Expense expense) {
        if (null != expense)
            expenseDataSource.editExpense(expense);
        else
            throw new ExpenseNotFoundException(("Expense Not Found"));
    }


    public void deleteExpense(Expense expense) {
        if (null != expense) {
            Integer expenseId = expense.getExpenseId();
            expenseDataSource.deleteExpense(expenseId);
        } else
            throw new ExpenseNotFoundException("Expense Not Found");
    }
}
