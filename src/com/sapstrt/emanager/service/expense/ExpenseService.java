package com.sapstrt.emanager.service.expense;


import com.sapstrt.emanager.domain.Expense;

import java.util.HashMap;
import java.util.List;

/**
 * Created by cambas on 9/25/13.
 */
public interface ExpenseService {

    public List<Expense> getAllExpenses();
    public List<Expense> searchExpenseByName( String name);
    public boolean createNewExpense(Expense expense);
    public void updateExpense(Expense expense);
    public void deleteExpense(Expense expense);
/*    public List<String> prepareListDataHeader();
    public HashMap<String,List<String>> prepareListDataMap();*/
}
