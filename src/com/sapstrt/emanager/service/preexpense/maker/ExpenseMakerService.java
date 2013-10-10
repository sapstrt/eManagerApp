package com.sapstrt.emanager.service.preexpense.maker;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.expense.ExpenseService;
import com.sapstrt.emanager.service.expense.ExpenseServiceImpl;
import com.sapstrt.emanager.service.preexpense.filter.MessageFilter;
import com.sapstrt.emanager.service.preexpense.filter.MessageFilterImpl;
import com.sapstrt.emanager.service.util.LocationService;
import com.sapstrt.emanager.service.util.SMSData;

import java.util.ArrayList;

/**
 * Created by vvarm1 on 10/10/13.
 */
public class ExpenseMakerService extends IntentService {
    public ExpenseMakerService() {
        super("ExpenseMaker");
    }
    String TAG="com.sapstrt.emanager";
    ExpenseMaker expenseMaker=new ExpenseMakerImpl();
    ExpenseService expenseService=new ExpenseServiceImpl(this);
    LocationService locationService=new LocationService();
    MessageFilter filter=new MessageFilterImpl();
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"Started Service"+"123231");
        try{
            Bundle bundle=intent.getExtras();
            if (bundle!=null){

                ArrayList<SMSData> smsList= (ArrayList<SMSData>) bundle.get("messages");
                Log.d(TAG,"found "+smsList.size()+" messages");
                for (SMSData sms:smsList){
                    if (filter.isUsefulMessage(sms,this)){
                        Expense expense=expenseMaker.createExpense(sms);
                        if (expense!=null){
                            checkLocationService(expense);
                            Log.d(TAG,expense.toString());
                            expenseService.createNewExpense(expense);
                        }

                    }

                }
            }else{
                Log.d(TAG,"bundle is empty"+"123231");
            }

        }catch (Exception e){
            Log.d(TAG,"Error Service");
            Log.e(TAG,e.toString()+ e.getCause()+e.getMessage());
        }
        Log.d(TAG,"Done Service");
    }
    private void checkLocationService(Expense expense){

        if (expense.getLocation()==null){
            expense.setLocation(locationService.getDeviceLocation(this));
        }
    }
}
