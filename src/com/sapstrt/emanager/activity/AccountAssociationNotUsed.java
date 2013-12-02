package com.sapstrt.emanager.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.auth.GoogleAuthUtil;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.service.configuration.InterFileService;
import com.sapstrt.emanager.service.configuration.SettingsFileWriter;
import com.sapstrt.emanager.service.util.GenerateTokenService;
import com.sapstrt.emanager.service.util.HTTPService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pteltu on 11/16/13.
 */
public class AccountAssociationNotUsed extends Activity implements View.OnClickListener {
    private String TAG="com.sapstrt.resttokenmyprojectTag";
    ListView accList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_accountsNotUsed);
        accList = (ListView) findViewById(R.id.accountList);
        ArrayList<String> accountNames=getAccountNames();

        accList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, accountNames));
        accList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        Button btn = (Button) findViewById(R.id.proceed);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {

        InterFileService fileWriter = new InterFileService();
        SettingsFileWriter settingsFileWriter = new SettingsFileWriter();
        Map<String, String> accountSelected = new TreeMap<>();
        int i = accList.getCheckedItemPosition();
        String s = ((TextView) accList.getChildAt(i)).getText().toString();
        accountSelected.put("Account", s);

        fileWriter.writeInternalFile(this);
        settingsFileWriter.writeToSettingsFile(this, accountSelected);

        GenerateTokenService generateTokenService=new GenerateTokenService();
        String token=generateTokenService.getToken(this);
        HTTPService httpService=HTTPService.getInstance();
        try {
            httpService.sendTokenToServer(token);
            String response=httpService.getResponseString();

            if(response.equalsIgnoreCase("User Authenticated!")){
                Intent intentMainActivity = new Intent(this, MainActivity.class);
                this.startActivity(intentMainActivity);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }



    }
    private ArrayList<String> getAccountNames() {
        AccountManager mAccountManager = AccountManager.get(this);
        Account[] accounts = mAccountManager.getAccountsByType(
                GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        ArrayList<String>  names = new  ArrayList<String>();
        for (int i = 0; i <accounts.length; i++) {
            names.add(accounts[i].name);
        }
        return names;
    }
}
