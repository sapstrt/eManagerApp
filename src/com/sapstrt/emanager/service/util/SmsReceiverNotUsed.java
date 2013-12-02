package com.sapstrt.emanager.service.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;



import com.sapstrt.emanager.activity.MainActivity;
import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.configuration.Configure;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;
import com.sapstrt.emanager.service.preexpenseNotUsed.maker.ExpenseMaker;
import com.sapstrt.emanager.service.preexpenseNotUsed.maker.ExpenseMakerImpl;
import com.sapstrt.emanager.service.preexpenseNotUsed.filter.MessageFilter;
import com.sapstrt.emanager.service.preexpenseNotUsed.filter.MessageFilterImpl;

/**
 * Created by vvarm1 on 9/25/13.*/


public class SmsReceiverNotUsed extends BroadcastReceiver {
    NotificationServiceNotUsed notificationServiceNotUsed =new NotificationServiceNotUsed();
    MessageFilter messageFilter=new MessageFilterImpl();
    ExpenseMaker maker=new ExpenseMakerImpl();
    Configure configure=new Configure();
    public static final String SMS_EXTRA_NAME ="pdus";

    public void onReceive(Context context, Intent intent) {

        if (configure.getConfiguration(context)==true){
            Bundle extras=intent.getExtras();
            if (extras!=null){
                Object[] smsExtra = (Object[]) extras.get( SMS_EXTRA_NAME );
                for ( int i = 0; i < smsExtra.length; ++i )
                {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
                    if (messageFilter.isUsefulMessage(sms,context)){
                        Expense expense=maker.createExpense(sms);
                        if (expense!=null){
                            ExpenseService expenseService=new ExpenseServiceImpl(context);
                            if (expenseService.createNewExpense(expense))
                                notificationServiceNotUsed.sendSmallNotification(context,MainActivity.class,"New Expense waiting for approval.");
                        }
                    }
                }
            }
        }

    }
}

