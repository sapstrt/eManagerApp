package com.sapstrt.emanager.service.preexpense.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by vvarma on 9/29/13.
 */
public class DateParser extends AbstractParser implements Parser {
    List<String> keywords;
    List<String> dateFormats;
    public DateParser() {
        keywords=new ArrayList<>();
        keywords.add("on");
        dateFormats=new ArrayList<>();
        dateFormats.add("dd-MMM-yy");
        dateFormats.add("yyyy-MM-dd");
    }


    public Map.Entry<String, String> parseInformationFromText(String messageTokens) {
        Map.Entry<String,String> dateEntry=null;
        for (String keyword:keywords){
            int index=-1;
            List<String> tokens= Arrays.asList(tokenise(messageTokens));
            if ((index=tokens.indexOf(keyword))>=0){
                String tempDate=null;
                SimpleDateFormat tempFmt=null;
                SimpleDateFormat finalFmt=new SimpleDateFormat("dd-MMM-yyyy");
                if ((tempDate=tokens.get(index+1))!=null){
                    for (String fmmtter:dateFormats){
                        tempFmt=new SimpleDateFormat(fmmtter);
                        try{
                            Date date=tempFmt.parse(tempDate);
                            dateEntry=new AbstractMap.SimpleEntry<>("date",finalFmt.format(date));
                            break;
                        } catch (ParseException e) {
                        }
                    }
                }

            }
        }
        return dateEntry;
    }

}
