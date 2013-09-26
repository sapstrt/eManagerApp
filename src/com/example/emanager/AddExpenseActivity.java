package com.example.emanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;

/**
 * Created by pteltu on 9/25/13.
 */
public class AddExpenseActivity extends Activity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
        Button saveButton = (Button) findViewById(R.id.addButton);
        saveButton.setOnClickListener((android.view.View.OnClickListener)this);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener((android.view.View.OnClickListener)this);

    }

    @Override
    public void onClick(View view) {

        if(((Button)view).getId()==R.id.addButton){
            Expense expense=new Expense();
            expense.setExpenseName(((EditText) findViewById(R.id.expenseName)).getText().toString());
            String amt=((EditText)findViewById(R.id.amount)).getText().toString();
            expense.setAmount(Double.parseDouble(amt));
            /*//expense.setDate( (Date)findViewById(R.id.date).toString());
            expense.setNote(((EditText) findViewById(R.id.note)).getText().toString());*/
        }
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }
}