package com.sapstrt.emanager.service.preexpense.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
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
                ParsePosition pos=new ParsePosition(0);
                if ((tempDate=tokens.get(index+1))!=null){
                    for (String fmmtter:dateFormats){
                        tempFmt=new SimpleDateFormat(fmmtter);
                        tempFmt.setLenient(false);
                        Date date=tempFmt.parse(tempDate,pos);
                        if (date!=null){
                            dateEntry=new AbstractMap.SimpleEntry<>("date",finalFmt.format(date));
                            break;
                        }
                    }
                }
            }
        }
        return dateEntry;
    }

    public Map.Entry<String, String> getExpenseMapGivenKeyWordMap(Map<String,String> keywordMap) {
        Map.Entry<String,String> entry=null;
        SimpleDateFormat sdf;
        SimpleDateFormat finalFmt=new SimpleDateFormat("dd-MMM-yyyy");
        ParsePosition pos=new ParsePosition(0);
        for (String keyword:keywords){
            String val=keywordMap.get(keyword);
            if (val!=null){
                String[] valArr=val.split(" ");
                for (String fmt:dateFormats){
                    sdf=new SimpleDateFormat(fmt);
                    sdf.setLenient(false);
                    Date date=sdf.parse(valArr[0],pos);
                    if (date!=null){
                        entry=new AbstractMap.SimpleEntry<>("date",finalFmt.format(date));
                        break;
                    }
                }
            }
        }
        return entry;
    }
}
