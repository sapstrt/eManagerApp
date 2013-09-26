package com.example.emanager;


import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;


import com.example.emanager.service.ExpenseService;
import com.example.emanager.service.ExpenseServiceImpl;

import database.ExpenseDataSource;


public class MainActivity extends ListActivity implements OnClickListener{
	List<Expense> expenseList=new ArrayList<Expense>();
    ExpenseService expenseService;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        expenseService=new ExpenseServiceImpl(this) ;
        expenseList=expenseService.getAllExpenses();

        ListView listView=(ListView)findViewById(android.R.id.list);

        if(!expenseList.isEmpty()){
        ArrayAdapter<Expense> adapter = new ArrayAdapter<Expense>(this,
                android.R.layout.simple_list_item_1, expenseList);
        setListAdapter(adapter);
        }
        else{
            View welcomeMsg = findViewById(R.id.welcome);
            listView.setEmptyView(welcomeMsg);
        }

        ImageButton addButton = (ImageButton) findViewById(R.id.addExpense);
        Button generateButton = (Button) findViewById(R.id.generateExpense);

        addButton.setOnClickListener((android.view.View.OnClickListener)this);
        generateButton.setOnClickListener((android.view.View.OnClickListener)this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.addExpense:
                Intent intent = new Intent(this,AddExpenseActivity.class);
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
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

}
/*public class MainActivityVinay extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<SMSData> smsList = new ArrayList<SMSData>();
        Uri uri = Uri.parse("content://sms/inbox");
        CursorLoader cursorLoader=new CursorLoader(this,uri,null,null,null,null);
        Cursor c=cursorLoader.loadInBackground();
        // Read the sms data and store it in the list
        if(c.moveToFirst()) {
            for(int i=0; i < c.getCount(); i++) {
                SMSData sms = new SMSData();
                sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
                sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
                smsList.add(sms);

                c.moveToNext();
            }
        }
        c.close();

        // Set smsList in the ListAdapter
        setListAdapter(new ListAdapter(this, smsList));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        SMSData sms = (SMSData)getListAdapter().getItem(position);

        Toast.makeText(getApplicationContext(), sms.getBody(), Toast.LENGTH_LONG).show();

    }

}*/
