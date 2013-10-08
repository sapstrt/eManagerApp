package com.sapstrt.emanager.service.util;

import android.database.Cursor;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cambas on 10/5/13.
 */
public class ImportSms  {

    List<SMSData> smsList=new ArrayList<>();
    //reads All sms from phone
    public void readSMSFromPhone(Uri uri, Cursor c)
    {


        if(c.moveToFirst()) {
            for(int i=0; i < c.getCount(); i++) {
                SMSData sms = new SMSData();
                sms.setMessageBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
                sms.setMessageAddress(c.getString(c.getColumnIndexOrThrow("address")).toString());
                smsList.add(sms);

                c.moveToNext();
            }
        }
        c.close();
    }

    public List<String> getBanksFromMessages()
    {
        List<String> banksList=new ArrayList<>();
        for(SMSData sms:smsList)
        {
            String address=sms.getMessageAddress().toUpperCase();
            if((address.matches("(.*)-(.*)BK")))
            {
                String bankName=address.substring(address.indexOf("-"),address.indexOf("B"));
                banksList.add(bankName);
            }
        }

        return banksList;

    }



}
