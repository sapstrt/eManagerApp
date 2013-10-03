package com.sapstrt.emanager.service.preexpense.maker;

import android.telephony.SmsMessage;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.service.preexpense.parser.AmountParser;
import com.sapstrt.emanager.service.preexpense.parser.DateParser;
import com.sapstrt.emanager.service.preexpense.parser.LocationParser;
import com.sapstrt.emanager.service.preexpense.parser.ModeParser;
import com.sapstrt.emanager.service.preexpense.parser.Parser;
import com.sapstrt.emanager.service.preexpense.parser.TypeParser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by vvarma on 9/29/13.
 */
public class ExpenseMakerImpl implements ExpenseMaker {
    Set<Parser> parsers;

    public ExpenseMakerImpl() {
        parsers=new HashSet<>();
        parsers.add(new AmountParser());
        parsers.add(new DateParser());
        parsers.add(new LocationParser());
        parsers.add(new ModeParser());
        parsers.add(new TypeParser());
    }


    public Expense createExpense(SmsMessage sms) {
        Expense expense=null;
        String[] tokens=tokenize(sms.getMessageBody());
        Map<String,String> map=new HashMap<>();
        for (Parser parser:parsers){
            Map.Entry<String,String> tempEntry=parser.parseInformationFromText(tokens);
            if(tempEntry!=null)
                map.put(tempEntry.getKey(),tempEntry.getValue());
        }
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
                    //case "type":expense.setType(map.get(key));
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
        }

        return expense;
    }
    String[] tokenize(String string){
        return string.replaceAll("[(\\s\\.)][(\\.\\s)]"," ").split(" ");
    }
}
