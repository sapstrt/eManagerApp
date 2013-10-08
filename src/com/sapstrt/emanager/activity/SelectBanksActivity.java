package com.sapstrt.emanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.service.util.ImportSms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cambas on 10/7/13.
 */
public class SelectBanksActivity extends Activity {
    ImportSms smsImporter=new ImportSms();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_banks);
        List<String> banksList=new ArrayList<>();
        banksList.add("CITI");
        banksList.add("HDFC");
        banksList.add("SBI");
        banksList.add("ICICI");
       /* banksList=smsImporter.getBanksFromMessages();*/
        final ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,banksList));
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Button btn =(Button) findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SparseBooleanArray sp = lv.getCheckedItemPositions();
                StringBuffer str = new StringBuffer();
                for(int i=0;i<sp.size();i++){
                    if(sp.valueAt(i)==true){
                        String s = ((TextView) lv.getChildAt(i)).getText().toString();
                        str = str.append(" "+s);
                    }
                }
                Toast.makeText(SelectBanksActivity.this, "Selected items are " + str.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
