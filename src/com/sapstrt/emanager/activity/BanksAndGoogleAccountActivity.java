package com.sapstrt.emanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.service.configuration.InterFileService;
import com.sapstrt.emanager.service.configuration.SettingsFileWriter;
import com.sapstrt.emanager.service.util.AppFirstTimeUseService;
import com.sapstrt.emanager.service.util.GoogleAccountNames;
import com.sapstrt.emanager.service.util.ImportSms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cambas on 10/7/13.
 */
public class BanksAndGoogleAccountActivity extends Activity implements View.OnClickListener {

    ImportSms smsImporter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_banks);

        ListView listViewBank = (ListView) findViewById(R.id.bankList);
        ListView listViewAccount = (ListView) findViewById(R.id.accountList);

        smsImporter = ImportSms.getInstance();
        List<String> banksList =new ArrayList<>(smsImporter.getBanksFromMessages());

        GoogleAccountNames g=new GoogleAccountNames(this);
        List<String> accountList=new ArrayList<>(g.getAccountNames());

        listViewBank.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, banksList));
        //listViewBank.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listViewAccount.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice,accountList));

        Button btn = (Button) findViewById(R.id.proceed);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        ListView listViewBank = (ListView) findViewById(R.id.bankList);
        ListView listViewAccount = (ListView) findViewById(R.id.accountList);

        Map<String, String> banksAndAccSelected = new TreeMap<>();

        SparseBooleanArray spBanks = listViewBank.getCheckedItemPositions();
        int accIndex=listViewAccount.getCheckedItemPosition();

        for (int i = 0; i < spBanks.size(); i++) {
            if (spBanks.valueAt(i) == true) {
                String s = ((TextView) listViewBank.getChildAt(i)).getText().toString();
                banksAndAccSelected.put("Bank" + i, s);
            }
        }

        String accName=((TextView)listViewAccount.getChildAt(accIndex)).getText().toString();
        banksAndAccSelected.put("GoogleAccount",accName);

        InterFileService fileWriter = new InterFileService();
        fileWriter.writeInternalFile(this);

        SettingsFileWriter settingsFileWriter = new SettingsFileWriter();
        settingsFileWriter.writeToSettingsFile(this, banksAndAccSelected);



        AppFirstTimeUseService service=new AppFirstTimeUseService(this);
        service.getStarted();

        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);

    }



}

