package com.sapstrt.emanager.service.preexpense;

import android.telephony.SmsMessage;

import com.sapstrt.emanager.domain.Expense;

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
        parsers=new HashSet<Parser>();
        parsers.add(new AmountParser());
    }

    @Override
    public Expense createExpense(SmsMessage sms) {
        String[] tokens=tokenize(sms.getMessageBody());
        Map<String,String> map=new HashMap<String, String>();
        for (Parser parser:parsers){
            Map.Entry<String,String> tempEntry=parser.parseInformationFromText(tokens);
            map.put(tempEntry.getKey(),tempEntry.getValue());
        }
        Expense expense=new Expense();
        for (String key:map.keySet()){
            switch (key){
                case "amount":expense.setAmount(Double.parseDouble(map.get(key)));
            }
        }
        return expense;
    }
    String[] tokenize(String string){
        return string.replaceAll("[(\\s\\.)][(\\.\\s)]"," ").split(" ");
    }
}
