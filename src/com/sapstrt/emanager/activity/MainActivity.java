package com.sapstrt.emanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.service.adapter.ExpandableListAdapter;
import com.sapstrt.emanager.service.configuration.Configure;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;
import com.sapstrt.emanager.service.util.NotificationService;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity {
    ExpenseService expenseService;
    Configure configurer=new Configure();
    NotificationService notificationService=new NotificationService();
    ExpandableListAdapter expListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ExpandableListView listView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isCofigured=configurer.getConfiguration(this);


        if(isCofigured==true)
        {
            notificationService.clearAllNotifications(this);
            expenseService = new ExpenseServiceImpl(this);
        }
        else
        {
            //starting the configuration activity
            Intent intent = new Intent(this, GetConfigurationActivity.class);
            this.startActivity(intent);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    protected void onDestroy() {

        super.onDestroy();
    }



    protected void onResume() {
        super.onResume();
        if(configurer.getConfiguration(this))
        {
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
    }


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

