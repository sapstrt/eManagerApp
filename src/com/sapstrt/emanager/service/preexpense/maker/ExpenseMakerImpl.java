package com.sapstrt.emanager.service.preexpense.maker;

import android.telephony.SmsMessage;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.util.LocationService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by vvarma on 9/29/13.
 */
public class ExpenseMakerImpl implements ExpenseMaker {
   LocationService locationService;
    ParserService parserService;

    public ExpenseMakerImpl() {
        locationService=new LocationService();
        parserService=new ParserService();

    }


    public Expense createExpense(SmsMessage sms) {
        Expense expense=null;
        Map<String,String> map=parserService.getExpenseMapFromMessage(sms.getMessageBody());

        if (map.get("type")!=null){
            expense=new Expense();
            for (String key:map.keySet()){
                switch (key){
                    case "amount":expense.setAmount(Double.parseDouble(map.get(key)));
                        break;
                    case "date":expense.setDate(map.get(key));
                        break;
                    case "location":expense.setLocation(map.get(key));
                        break;
                    case "mode":expense.setMode(map.get(key));
                        break;
                    default:
                }
            }
            expense.setExpenseName("Auto");
            if (expense.getDate()==null){
                SimpleDateFormat fmt=new SimpleDateFormat("dd-MMM-yyyy");
                expense.setDate(fmt.format(new Date(sms.getTimestampMillis())));
            }
            if (expense.getLocation()==null){
                expense.setLocation(locationService.getDeviceLocation());
            }
        }

        return expense;
    }

}
