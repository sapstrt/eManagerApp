package com.sapstrt.emanager.service.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


import com.sapstrt.emanager.activity.MainActivity;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.preexpense.ExpenseMaker;
import com.sapstrt.emanager.service.preexpense.ExpenseMakerImpl;
import com.sapstrt.emanager.service.preexpense.MessageFilter;
import com.sapstrt.emanager.service.preexpense.MessageFilterImpl;

/**
 * Created by vvarm1 on 9/25/13.*/


public class SmsReceiver extends BroadcastReceiver {
    NotificationService notificationService=new NotificationService();
    MessageFilter messageFilter=new MessageFilterImpl();
    ExpenseMaker maker=new ExpenseMakerImpl();
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
                if (messageFilter.isUsefulMessage(sms)){
                    Expense expense=maker.createExpense(sms);
                    Toast.makeText(context,expense.toString(),Toast.LENGTH_LONG).show();
                    notificationService.sendSmallNotification(context,MainActivity.class,"New Expense waiting for approval.");

                }
            }

        }
    }
}

