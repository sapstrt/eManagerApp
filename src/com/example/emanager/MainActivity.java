package com.example.emanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.emanager.service.ExpenseService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	List<Expense> expenseList=new ArrayList<Expense>();
    ExpenseService expenseService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Expense expense=new Expense();
        expense.setExpenseName("XYZ");
        expense.setLocation("BANGALORE");
        expense.setAmount(200.0);
        expense.setDate("1/1/2013");
		expenseService.createNewExpense(this,expense);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
