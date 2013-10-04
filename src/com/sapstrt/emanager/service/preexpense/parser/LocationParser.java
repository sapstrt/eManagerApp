package com.sapstrt.emanager.service.preexpense.parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by vvarm1 on 10/1/13.
 */
public class LocationParser extends AbstractParser implements Parser {
    List<String> keywords;

    public LocationParser() {
        keywords=new ArrayList<>();
        keywords.add("in");
        keywords.add("at");
    }


    public Map.Entry<String, String> parseInformationFromText(String messageTokens) {
        List<String> tokens= Arrays.asList(tokenise(messageTokens));
        Map.Entry<String,String> locationEntry=null;
        for (String keyword:keywords){
            int index=-1;
            String loc;
            if((index=tokens.indexOf(keyword))>=0){
                if ((loc=tokens.get(index+1))!=null){
                    if (locationEntry==null)
                        locationEntry=new AbstractMap.SimpleEntry<>("location",loc);
                    else
                        locationEntry.setValue(locationEntry.getValue()+" "+ loc);
                }
            }
        }
        return locationEntry;
    }
}
