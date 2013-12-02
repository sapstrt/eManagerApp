package com.sapstrt.emanager.service.preexpenseNotUsed.maker;

import com.sapstrt.emanager.service.preexpenseNotUsed.parser.AmountParser;
import com.sapstrt.emanager.service.preexpenseNotUsed.parser.DateParser;
import com.sapstrt.emanager.service.preexpenseNotUsed.parser.LocationParser;
import com.sapstrt.emanager.service.preexpenseNotUsed.parser.ModeParser;
import com.sapstrt.emanager.service.preexpenseNotUsed.parser.Parser;
import com.sapstrt.emanager.service.preexpenseNotUsed.parser.TypeParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by vvarm1 on 10/7/13.
 */
public class ParserService {

    List<String> keywords = new ArrayList<>();
    List<Parser> parsers=new ArrayList<>();

    public ParserService() {
        keywords.add("rs");
        keywords.add("inr");
        keywords.add("on");
        keywords.add("in");
        keywords.add("at");
        keywords.add("credit card");
        keywords.add("debit card");
        keywords.add("atm");
        keywords.add("cash");
        keywords.add("balance");
        keywords.add("credit");
        keywords.add("expense");
        keywords.add("purchase");
        keywords.add("debit");
        parsers.add(new DateParser());
        parsers.add(new LocationParser());
        parsers.add(new AmountParser());
        parsers.add(new ModeParser());
        parsers.add(new TypeParser());
    }

    private String formatMessage(String message) {
        String w = "$1" + " " + "$2" + "$3";
        String w2 = "$1" + "$2" + " " + "$3";
        message = message.replaceAll("(\\w+)([\\.,])([\\s$])", w);
        message = message.replaceAll("(\\s)([\\.,])(\\w+)", w2);
        return message;
    }
    private Map<String, String> generateKeyMapFromMessage(String message){
        message=message+" ";
        message = formatMessage(message.toLowerCase());
        String[] splitedMsg = message.split("[ ]+");
        Map<String, String> result = new HashMap<>();
        int i = 0;
        String value = "";
        String key = null;
        while (i < splitedMsg.length) {
            if (keywords.contains(splitedMsg[i])) {
                value = "";
                key=splitedMsg[i];
                if (splitedMsg[i].equals("credit")||splitedMsg.equals("debit")){
                    if (i<splitedMsg.length&&splitedMsg[i+1].equals("card")){
                        key=key+" "+splitedMsg[i+1];
                        i++;
                    }
                }
                result.put(key, value);

            } else {
                value = value + splitedMsg[i]+" ";
                if (key != null) result.put(key, value);
            }
            i++;
        }
        result.remove(",");
        result.remove(".");
        return result;
    }

    public Map<String,String> getExpenseMapFromMessage(String message){
        Map<String,String> keyMap=generateKeyMapFromMessage(message);
        Map<String,String> retMap=new HashMap<>();
        for (Parser parser:parsers){
            Map.Entry<String,String>entry=parser.getExpenseMapGivenKeyWordMap(keyMap);
            if (entry!=null)
                retMap.put(entry.getKey(),entry.getValue());
        }
        return retMap;
    }
    /*public static void main(String[] args){
        ParserService service=new ParserService();
        String message="Credit of Rs 340 on 21-JAN-2012 using credit card at bangalore central from wokoko.";
        Map<String,String> keyMap=new HashMap<>();
        for(Map.Entry<String,String> entry:service.getExpenseMapFromMessage(message).entrySet())
            System.out.println(entry.getKey() + ":" + entry.getValue());
    }*/
}


