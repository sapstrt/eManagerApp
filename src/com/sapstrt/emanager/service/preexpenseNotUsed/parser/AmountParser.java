package com.sapstrt.emanager.service.preexpenseNotUsed.parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by vvarma on 9/29/13.
 */
public class AmountParser extends AbstractParser implements Parser {
    List<String> keywords;

    public AmountParser() {
        keywords=new ArrayList<>();
        keywords.add("rs");
        keywords.add("inr");
    }


    public Map.Entry<String, String> parseInformationFromText(String messageTokens) {
        Map.Entry<String,String> amountEntry=null;
        for (String keyword:keywords){
            int index=-1;
            List<String> tokens=Arrays.asList(tokenise(messageTokens));
            if ((index=tokens.indexOf(keyword))>=0){
                if(tokens.get(index+1)!=null){
                    try{
                        Double.parseDouble(tokens.get(index+1));
                        amountEntry=new AbstractMap.SimpleEntry<>("amount",tokens.get(index+1));
                        break;
                    }catch (NumberFormatException e){

                    }

                }
            }

        }
        return amountEntry;
    }

    public Map.Entry<String, String> getExpenseMapGivenKeyWordMap(Map<String,String> keywordMap) {
        Map.Entry<String,String> entry=null;
        for (String keyword:keywords){
            String val=keywordMap.get(keyword);
            if (val!=null){
                String[] valArr=val.split(" ");
                try{
                    Double.parseDouble(valArr[0]);
                    entry=new AbstractMap.SimpleEntry<>("amount",valArr[0]);
                    break;
                }catch (Exception e){
                }
            }
        }
        return entry;
    }


}
