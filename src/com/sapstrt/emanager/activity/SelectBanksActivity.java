package com.sapstrt.emanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.service.configuration.InterFileService;
import com.sapstrt.emanager.service.configuration.SettingsFileWriter;
import com.sapstrt.emanager.service.preexpense.maker.ExpenseMakerService;
import com.sapstrt.emanager.service.util.ImportSms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cambas on 10/7/13.
 */
public class SelectBanksActivity extends Activity implements View.OnClickListener {

    ImportSms smsImporter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_banks);
        ListView lv = (ListView) findViewById(R.id.listView1);
         smsImporter = ImportSms.getInstance();
        List<String> banksList = null;
        banksList = new ArrayList<>(smsImporter.getBanksFromMessages());
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, banksList));
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        ListView lv = (ListView) findViewById(R.id.listView1);
        InterFileService fileWriter = new InterFileService();
        SettingsFileWriter settingsFileWriter = new SettingsFileWriter();
        Map<String, String> banksSelected = new TreeMap<>();
        SparseBooleanArray sp = lv.getCheckedItemPositions();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < sp.size(); i++) {
            if (sp.valueAt(i) == true) {
                String s = ((TextView) lv.getChildAt(i)).getText().toString();
                banksSelected.put("Bank" + i, s);
            }
        }
        fileWriter.writeInternalFile(this);
        settingsFileWriter.writeToSettingsFile(this, banksSelected);
        Log.d("com.sapstrt.emanager","creating bundle "+ smsImporter.getSmsList().size());
        Intent serviceStarter=new Intent(this, ExpenseMakerService.class);
        serviceStarter.putExtra("messages",smsImporter.getSmsList());
        this.startService(serviceStarter);

        Intent intent = new Intent(this, AccountAssociation.class);
        this.startActivity(intent);
        /*Intent intent = new Intent(this, DrawerActivity.class);
        this.startActivity(intent);*/

    }
}

