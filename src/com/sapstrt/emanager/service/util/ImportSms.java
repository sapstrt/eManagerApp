package com.sapstrt.emanager.service.util;

import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by cambas on 10/5/13.
 */
public class ImportSms {
    private static final ImportSms importSms = new ImportSms();


    protected ImportSms() {
    }

    public static ImportSms getInstance() {
        return importSms;
    }

    List<SMSData> smsList = new ArrayList<>();

    //reads All sms from phone
    public void readSMSFromPhone(Uri uri, Cursor c) {
        Pattern p = Pattern.compile("(.*)-(.*)BK");
        String address = null;
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                SMSData sms = new SMSData();

                address = c.getString(c.getColumnIndexOrThrow("address")).toString();
                if (p.matcher(address).matches()) {
                    sms.setMessageBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
                    sms.setMessageAddress(address);
                    smsList.add(sms);
                }
                c.moveToNext();
            }
        }
        c.close();
    }

    public Set<String> getBanksFromMessages() {
        Set<String> banksList = new HashSet<>();
        Set<String> bankSenderSet = new HashSet<>();
        for (SMSData sms : smsList) {
            String address = sms.getMessageAddress().toUpperCase();
            bankSenderSet.add(address);
        }
        String bankName = null;
        for (String address : bankSenderSet) {
            bankName = address.substring(address.indexOf("-") + 1, address.toCharArray().length-2);
            if (!banksList.contains(bankName))
                banksList.add(bankName);
        }


        return banksList;

    }


}
