package com.sapstrt.emanager.activity;

import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sapstrt.emanager.database.ExpenseDataSource;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.adapter.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.sapstrt.emanager.R;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;
import com.sapstrt.emanager.service.util.NotificationService;


public class MainActivity extends Activity {
    ExpenseService expenseService;
    NotificationService notificationService=new NotificationService();
    ExpandableListAdapter expListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ExpandableListView listView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationService.clearAllNotifications(this);
        expenseService = new ExpenseServiceImpl(this);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    protected void onDestroy() {

        super.onDestroy();
    }


   @Override
    protected void onResume() {
        super.onResume();
        listView = (ExpandableListView) findViewById(R.id.lvExp);
        listDataHeader=expenseService.prepareListDataHeader();
        listDataChild=expenseService.prepareListDataMap();
        if (!listDataHeader.isEmpty() && !listDataChild.isEmpty()) {
            expListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
            listView.setAdapter(expListAdapter);
        } else {
            View welcomeMsg = findViewById(R.id.welcome);
            listView.setEmptyView(welcomeMsg);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                listDataHeader=expenseService.prepareListDataHeader();
                listDataChild=expenseService.prepareListDataMap();
                expListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
                listView.setAdapter(expListAdapter);
                break;
            case R.id.action_settings:
                break;
            case R.id.action_add:
                Intent intent = new Intent(this, AddExpenseActivity.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }


    protected void onPause() {

        super.onPause();
    }

}

