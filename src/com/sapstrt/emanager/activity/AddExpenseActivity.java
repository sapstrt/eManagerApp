package com.sapstrt.emanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;
import com.sapstrt.emanager.service.util.GenerateTokenService;
import com.sapstrt.emanager.service.util.HTTPService;

/**
 * Created by pteltu on 9/25/13.
 */
public class AddExpenseActivity extends Activity implements View.OnClickListener {
    String TAG="com.sapstrt.emanager";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
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

            GenerateTokenService generateTokenService=new GenerateTokenService();
            HTTPService httpService= HTTPService.getInstance();
            String token=generateTokenService.getToken(this);
            httpService.sendExpenseToServer(token,expense);
            String response=httpService.getResponseString();

            if(response.equalsIgnoreCase("Expense Added!")){
                Log.d(TAG, "Expense added to sever as well");
            }

        }
        Intent intent = new Intent(this,DrawerActivity.class);
        this.startActivity(intent);
    }
}