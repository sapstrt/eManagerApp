package com.sapstrt.emanager.service.preexpenseNotUsed.parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by vvarma on 10/3/13.
 */
public class TypeParser extends AbstractParser implements Parser {
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
    public Map.Entry<String, String> parseInformationFromText(String messageTokens) {
        Map.Entry<String,String> typeEntry=null;
        String key=null;
        for (String keyword:keywords){
            if (messageTokens.contains(keyword)){
                if (keyword.equals("credit")||keyword.equals("debit")){
                    List<String> tokens=Arrays.asList(messageTokens.split(" "));
                    int index=tokens.indexOf(keyword);
                    if (index+1<tokens.size())
                        if (tokens.get(index+1).equals("card"))
                            continue;
                }
                key=keyword;
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

    @Override
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
                case "balance":
                    break;
                case "expense":
                case "purchase":
                case "credit":
                    entry=new AbstractMap.SimpleEntry<>("type","credit");
                    break;
                case "debit":
                    entry=new AbstractMap.SimpleEntry<>("type","debit");
                    break;
            }
        return entry;
    }

}
