package com.sapstrt.emanager.service.preexpense.parser;

import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by vvarma on 10/3/13.
 */
public class ModeParser extends AbstractParser implements Parser {
    List<String> keywords;

    public ModeParser() {
        keywords=new ArrayList<>();
        keywords.add("credit card");
        keywords.add("debit card");
        keywords.add("atm");
        keywords.add("cash");
    }
//case sensitive and regex cases !
    public Map.Entry<String, String> parseInformationFromText(String messageTokens) {
        Map.Entry<String,String> modeEntry=null;
        String key=null;
        for (String keyword:keywords){
            if (messageTokens.contains(keyword)){
                key=keyword;
                break;
            }
        }
        if (key!=null)
            switch (key){
                case "credit card":
                    modeEntry=new AbstractMap.SimpleEntry<>("mode","credit card");
                    break;
                case "debit card":
                    modeEntry=new AbstractMap.SimpleEntry<>("mode","debit card");
                    break;
                case "cash":
                case "atm":
                    modeEntry=new AbstractMap.SimpleEntry<>("mode","cash");
            }
        return modeEntry;
    }

    public Map.Entry<String, String> getExpenseMapGivenKeyWordMap(Map<String,String> keywordMap) {
        Map.Entry<String,String> entry=null;
        String key=null;
        for (String keyword:keywords){
            String val=keywordMap.get(keyword);
            if (val!=null){
                key=keyword;
                break;
            }

        }
        if (key!=null)
            switch (key){
                case "credit card":
                    entry=new AbstractMap.SimpleEntry<>("mode","credit card");
                    break;
                case "debit card":
                    entry=new AbstractMap.SimpleEntry<>("mode","debit card");
                    break;
                case "cash":
                case "atm":
                    entry=new AbstractMap.SimpleEntry<>("mode","cash");
            }
        return entry;
    }
}
