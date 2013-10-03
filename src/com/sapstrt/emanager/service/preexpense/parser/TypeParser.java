package com.sapstrt.emanager.service.preexpense.parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by vvarma on 10/3/13.
 */
public class TypeParser implements Parser {
    List<String> keywords;

    public TypeParser() {
        keywords=new ArrayList<>();
        keywords.add("balance");
        keywords.add("credit");
        keywords.add("expense");
        keywords.add("purchase");
        keywords.add("debit");
    }

    @Override
    public Map.Entry<String, String> parseInformationFromText(String[] messageTokens) {
        Map.Entry<String,String> typeEntry=null;
        List<String> tokens= Arrays.asList(messageTokens);
        String key=null;
        for (String keyword:keywords){
            for (String token:tokens){
                if (keyword.equals(token)){
                    if (tokens.get(tokens.indexOf(token)+1)!=null&&tokens.get(tokens.indexOf(token)+1).equals("card"))
                        continue;
                    key=keyword;
                    break;
                }
            }
        }
        if (key!=null)
            switch (key){
                case "balance":
                    break;
                case "expense":
                case "purchase":
                case "credit":
                    typeEntry=new AbstractMap.SimpleEntry<>("type","credit");
                    break;
                case "debit":
                    typeEntry=new AbstractMap.SimpleEntry<>("type","debit");
                    break;
        }
        return typeEntry;
    }
}
