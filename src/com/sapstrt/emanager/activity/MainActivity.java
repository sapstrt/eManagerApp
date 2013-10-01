package com.sapstrt.emanager.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.adapter.ExpandableListAdapter;
import com.sapstrt.emanager.service.ExpenseService;
import com.sapstrt.emanager.service.ExpenseServiceImpl;
import com.sapstrt.emanager.service.NotificationService;

import java.util.HashMap;
import java.util.List;



public class MainActivity extends Activity implements OnClickListener {
    //List<Expense> expenseList = new ArrayList<Expense>();
    ExpenseService expenseService ;
    NotificationService notificationService=new NotificationService();
    //ArrayAdapter<Expense> adapter;
    ExpandableListAdapter expListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationService.clearAllNotifications(this);
        setContentView(R.layout.activity_main);
        expenseService =new ExpenseServiceImpl(this);

        ImageButton addButton = (ImageButton) findViewById(R.id.addExpense);
        addButton.setOnClickListener((android.view.View.OnClickListener) this);

        Button refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener((android.view.View.OnClickListener) this);
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
            case R.id.refresh:
                //expenseService.getAllExpenses();
                expListAdapter.notifyDataSetChanged();
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
        //expenseList = expenseService.getAllExpenses();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.lvExp);
        listDataHeader=expenseService.prepareListDataHeader();
        listDataChild=expenseService.prepareListDataMap();
        if (!listDataHeader.isEmpty() && !listDataChild.isEmpty()) {
            /*adapter = new ArrayAdapter<Expense>(this,
                    android.R.layout.simple_list_item_1, expenseList);
            setListAdapter(adapter);*/
            expListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
            // setting list adapter
            listView.setAdapter(expListAdapter);
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

