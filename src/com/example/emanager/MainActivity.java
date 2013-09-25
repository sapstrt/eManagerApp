package com.example.emanager;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends ListActivity implements OnClickListener{
	List<Expense> expenseList=new ArrayList<Expense>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        Expense expense=new Expense("gfg",23.0,null,"dgd");
        expenseList.add(expense);

        ListView listView=(ListView)findViewById(R.id.listOfExpenses);

            View welcomeMsg = findViewById(R.id.welcome);
            listView.setEmptyView(welcomeMsg);


       ImageButton addButton = (ImageButton) findViewById(R.id.addExpense);
       Button generateButton = (Button) findViewById(R.id.generateExpense);

        addButton.setOnClickListener((android.view.View.OnClickListener)this);
        generateButton.setOnClickListener((android.view.View.OnClickListener)this);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
}
