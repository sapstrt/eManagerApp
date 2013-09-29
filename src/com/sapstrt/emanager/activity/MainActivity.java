package com.sapstrt.emanager.activity;


import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.view.View.OnClickListener;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;
import com.sapstrt.emanager.service.util.NotificationService;


public class MainActivity extends ListActivity implements OnClickListener {
    List<Expense> expenseList = new ArrayList<Expense>();
    ExpenseService expenseService;
    NotificationService notificationService=new NotificationService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationService.clearAllNotifications(this);
        setContentView(R.layout.activity_main);
        expenseService = new ExpenseServiceImpl(this);
        ImageButton addButton = (ImageButton) findViewById(R.id.addExpense);
        Button generateButton = (Button) findViewById(R.id.generateExpense);

        addButton.setOnClickListener((android.view.View.OnClickListener) this);
        generateButton.setOnClickListener((android.view.View.OnClickListener) this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addExpense:
                Intent intent = new Intent(this, AddExpenseActivity.class);
                this.startActivity(intent);
                break;
            case R.id.generateExpense:

                ArrayAdapter<Expense> adapter = new ArrayAdapter<Expense>(this,
                        android.R.layout.simple_list_item_1, expenseList);
                setListAdapter(adapter);
                break;
            default:
                throw new RuntimeException("Button ID not known");
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        expenseList = expenseService.getAllExpenses();
        ListView listView = (ListView) findViewById(android.R.id.list);
        if (!expenseList.isEmpty()) {
            ArrayAdapter<Expense> adapter = new ArrayAdapter<Expense>(this,
                    android.R.layout.simple_list_item_1, expenseList);
            setListAdapter(adapter);
        } else {
            View welcomeMsg = findViewById(R.id.welcome);
            listView.setEmptyView(welcomeMsg);
        }
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

}

