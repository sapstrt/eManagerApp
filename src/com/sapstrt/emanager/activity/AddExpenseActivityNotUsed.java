package com.sapstrt.emanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;

/**
 * Created by pteltu on 9/25/13.
 */
public class AddExpenseActivityNotUsed extends Activity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenseNotUsed);
        Button saveButton = (Button) findViewById(R.id.addButton);
        saveButton.setOnClickListener((android.view.View.OnClickListener)this);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener((android.view.View.OnClickListener)this);

    }

    public void onClick(View view) {

        if((view).getId()==R.id.addButton){
            Expense expense=new Expense();
            expense.setExpenseName(((EditText) findViewById(R.id.expenseName)).getText().toString());
            String amt=((EditText)findViewById(R.id.amount)).getText().toString();
            expense.setAmount(Double.parseDouble(amt));
            expense.setDate( ((EditText) findViewById(R.id.date)).getText().toString());
            expense.setMode(((EditText) findViewById(R.id.mode)).getText().toString());
            expense.setLocation(((EditText) findViewById(R.id.location)).getText().toString());

            ExpenseService expenseService=new ExpenseServiceImpl(this);
            expenseService.createNewExpense(expense);

        }
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }
}