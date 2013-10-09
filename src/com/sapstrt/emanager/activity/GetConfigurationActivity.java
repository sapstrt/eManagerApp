package com.sapstrt.emanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sapstrt.emanager.R;
import com.sapstrt.emanager.service.util.ImportSms;

/**
 * Created by cambas on 10/5/13.
 */
public class GetConfigurationActivity extends Activity implements View.OnClickListener {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_configuration);
        Button importButton = (Button) findViewById(R.id.importButton);
        importButton.setOnClickListener((android.view.View.OnClickListener)this);

        Button skipButton = (Button) findViewById(R.id.skipButton);
        skipButton.setOnClickListener((android.view.View.OnClickListener)this);

    }

    public void onClick(View view) {

        if((view).getId()==R.id.importButton){
            importSMS();
        }
        Intent intent = new Intent(this,SelectBanksActivity.class);
        this.startActivity(intent);
    }


    private void importSMS()
    {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c= getContentResolver().query(uri, null, null ,null,null);
        ImportSms smsImporter=ImportSms.getInstance();
        startManagingCursor(c);
        smsImporter.readSMSFromPhone(uri,c);
    }

}
