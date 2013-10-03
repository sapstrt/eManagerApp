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
public class ModeParser implements Parser {
    List<String> keywords;

    public ModeParser() {
        keywords=new ArrayList<>();
        keywords.add("credit card");
        keywords.add("debit card");
        keywords.add("atm");
    }
//case sensitive and regex cases !
    public Map.Entry<String, String> parseInformationFromText(String[] messageTokens) {
        Map.Entry<String,String> modeEntry=null;
        List<String> tokens= Arrays.asList(messageTokens);
        for (String keyword:keywords){
            int index=-1;
            if ((index=tokens.indexOf(keyword))>=0){
                modeEntry=new AbstractMap.SimpleEntry<>("mode",keyword);
                break;
            }
        }
        return modeEntry;
    }
}
