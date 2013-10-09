package com.sapstrt.emanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.domain.AmountComparator;
import com.sapstrt.emanager.domain.DateComparator;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.domain.LocationComparator;
import com.sapstrt.emanager.domain.ModeComparator;
import com.sapstrt.emanager.service.configuration.Configure;
import com.sapstrt.emanager.service.configuration.InterFileService;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;
import com.sapstrt.emanager.service.util.NotificationService;

import java.util.Collections;
import java.util.List;

/**
 * Created by pteltu on 10/7/13.
 */
public class DrawerActivity extends Activity implements View.OnClickListener {


    ExpenseService expenseService;
    Configure configurer=new Configure();
    NotificationService notificationService=new NotificationService();
    List<Expense> expenseList;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        InterFileService fileService=new InterFileService();
        fileService.createInternalFile(this);
        boolean isCofigured=configurer.getConfiguration(this);
        if(isCofigured==true)
        {
        notificationService.clearAllNotifications(this);
        setContentView(R.layout.navigation_drawer);
        expenseService = new ExpenseServiceImpl(this);
        expenseList=expenseService.getAllExpenses();

        createTable();

        tableLayout= (TableLayout) findViewById(R.id.main_table);
        tableLayout.setOnClickListener(this);


        Button addButton=(Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        }else
        {
            //starting the configuration activity
            Intent intent = new Intent(this, GetConfigurationActivity.class);
            this.startActivity(intent);
        }



    }
    public void createTable(){
        tableLayout = (TableLayout) findViewById(R.id.main_table);
        TableRow tr_head = new TableRow(this);

        tr_head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

        TextView label_date = new TextView(this);
        label_date.setId(R.id.date);
        label_date.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
        label_date.setText("DATE");
        label_date.setOnClickListener(this);
        tr_head.addView(label_date);

        TextView label_amount = new TextView(this);
        label_amount.setId(R.id.amount);
        label_amount.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
        label_amount.setText("AMOUNT");
        label_amount.setOnClickListener(this);
        tr_head.addView(label_amount);

        TextView label_location = new TextView(this);
        label_location.setId(R.id.location);
        label_location.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
        label_location.setText("LOCATION");
        label_location.setOnClickListener(this);
        tr_head.addView(label_location);

        TextView label_mode = new TextView(this);
        label_mode.setId(R.id.mode);
        label_mode.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
        label_mode.setText("MODE");
        label_mode.setOnClickListener(this);
        tr_head.addView(label_mode);

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));


        for(Expense expense:expenseList){
                TableRow tr = new TableRow(this);
                tr.setBackgroundColor(Color.GRAY);
                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));

                TextView labelDATE = new TextView(this);
                labelDATE.setText(expense.getDate());
                labelDATE.setTextColor(Color.WHITE);
                labelDATE.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
                tr.addView(labelDATE);

                TextView labelAMOUNT = new TextView(this);
                labelAMOUNT.setText(expense.getAmount().toString());
                labelAMOUNT.setTextColor(Color.WHITE);
                labelAMOUNT.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
                tr.addView(labelAMOUNT);

                TextView labelLOCATION = new TextView(this);
                labelLOCATION.setText(expense.getLocation());
                labelLOCATION.setTextColor(Color.WHITE);
                labelLOCATION.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
                tr.addView(labelLOCATION);

                TextView labelMODE = new TextView(this);
                labelMODE.setText(expense.getMode());
                labelMODE.setTextColor(Color.WHITE);
                labelMODE.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
                tr.addView(labelMODE);

            tableLayout.addView(tr, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));

            TableRow tr2 = new TableRow(this);
            tr2.setBackgroundColor(Color.WHITE);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            TextView labelDATE2 = new TextView(this);
            labelDATE2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
            tr2.addView(labelDATE2);

            TextView labelAMOUNT2 = new TextView(this);
            labelAMOUNT2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
            tr2.addView(labelAMOUNT2);

            TextView labelLOCATION2 = new TextView(this);
            labelLOCATION2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
            tr2.addView(labelLOCATION2);

            TextView labelMODE2 = new TextView(this);
            labelMODE2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ));
            tr2.addView(labelMODE2);

            tableLayout.addView(tr2, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));

        }
    }

    protected void onResume() {
        super.onResume();
        /*if(configurer.getConfiguration(this))
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
        }*/
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addButton:
                Expense expense=new Expense();
                expense.setExpenseName(((EditText) findViewById(R.id.expenseName)).getText().toString());
                String amt=((EditText)findViewById(R.id.amountAddForm)).getText().toString();
                expense.setAmount(Double.parseDouble(amt));
                expense.setDate(((EditText) findViewById(R.id.dateAddForm)).getText().toString());
                expense.setMode(((EditText) findViewById(R.id.modeAddForm)).getText().toString());
                expense.setLocation(((EditText) findViewById(R.id.locationAddForm)).getText().toString());
                expenseService.createNewExpense(expense);
                Toast.makeText(this," Expense Added! ", Toast.LENGTH_LONG).show();
                ((EditText)findViewById(R.id.expenseName)).setText("");
                ((EditText) findViewById(R.id.amountAddForm)).setText("");
                ((EditText)findViewById(R.id.dateAddForm)).setText("");
                ((EditText)findViewById(R.id.modeAddForm)).setText("");
                ((EditText)findViewById(R.id.locationAddForm)).setText("");
                tableLayout.removeAllViews();
                tableLayout.invalidate();
                tableLayout.refreshDrawableState();
                expenseList=expenseService.getAllExpenses();
                createTable();
                break;
            case R.id.date:
                Collections.sort(expenseList, new DateComparator());
                tableLayout.removeAllViews();
                tableLayout.invalidate();
                tableLayout.refreshDrawableState();
                createTable();
                break;
            case R.id.amount:
                Collections.sort(expenseList,new AmountComparator());
                tableLayout.removeAllViews();
                tableLayout.invalidate();
                tableLayout.refreshDrawableState();
                createTable();
                break;
            case R.id.location:
                Collections.sort(expenseList,new LocationComparator());
                tableLayout.removeAllViews();
                tableLayout.invalidate();
                tableLayout.refreshDrawableState();
                createTable();
                break;
            case R.id.mode:
                Collections.sort(expenseList,new ModeComparator());
                tableLayout.removeAllViews();
                tableLayout.invalidate();
                tableLayout.refreshDrawableState();
                createTable();
                break;
            default:
                break;

        }

    }


}
