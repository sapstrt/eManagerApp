package com.sapstrt.emanager.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;


import com.sapstrt.emanager.R;
import com.sapstrt.emanager.activity.MainActivity;

/**
 * Created by vvarm1 on 9/25/13.*/


public class SmsReceiver extends BroadcastReceiver {
    NotificationService notificationService=new NotificationService();
    public static final String SMS_EXTRA_NAME ="pdus";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras=intent.getExtras();
        String messages = "";
        if (extras!=null){
            Object[] smsExtra = (Object[]) extras.get( SMS_EXTRA_NAME );
            for ( int i = 0; i < smsExtra.length; ++i )
            {
                SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
                String body = sms.getMessageBody().toString();
                String address = sms.getOriginatingAddress();
                messages += "SMS from " + address + " :\n";
                messages += body + "\n";
            }
            notificationService.sendSmallNotification(context,MainActivity.class,"New Expense waiting for approval.");
            Toast.makeText( context, messages, Toast.LENGTH_SHORT ).show();
        }
    }
}

