package com.sapstrt.emanager.service.preexpenseNotUsed.maker;

import android.telephony.SmsMessage;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.util.LocationServiceNotUsed;
import com.sapstrt.emanager.service.util.SMSData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by vvarma on 9/29/13.
 */
public class ExpenseMakerImpl implements ExpenseMaker {
   LocationServiceNotUsed locationServiceNotUsed;
    ParserService parserService;

    public ExpenseMakerImpl() {
        locationServiceNotUsed =new LocationServiceNotUsed();
        parserService=new ParserService();

    }

    public Expense createExpense(SMSData sms){
        return createExpense(sms.getMessageBody(),sms.getMessageAddress(),sms.getTimseStamp());
    }
    private Expense createExpense(String message, String address,Long timeStamp){
        Expense expense=null;
        Map<String,String> map=parserService.getExpenseMapFromMessage(message);

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
            //expense.setExpenseName("Auto");
            //expense.setCreatedBy();
           // expense.setGrpId();

            if (expense.getDate()==null){
                SimpleDateFormat fmt=new SimpleDateFormat("dd-MMM-yyyy");
                expense.setDate(fmt.format(new Date(timeStamp)));
            }
        }

        return expense;
    }
    public Expense createExpense(SmsMessage sms) {
        return createExpense(sms.getMessageBody(),sms.getOriginatingAddress(),sms.getTimestampMillis());
    }

}
