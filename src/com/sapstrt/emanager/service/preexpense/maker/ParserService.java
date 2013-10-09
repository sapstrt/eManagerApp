package com.sapstrt.emanager.service.preexpense.maker;

import java.util.List;
import java.util.Map;

/**
 * Created by vvarm1 on 10/7/13.
 */
public class ParserService {
    List<String> keywords;
    Map<String, String> keywordMap;

    public void breakMessageByKeywords(String message) {
        message=formatMessage(message);

                

    }

    private String formatMessage(String message) {
        message=message.replaceAll("[(\\s\\.)][(\\.\\s)]"," . ");
        message=message.replaceAll("[(\\s,)][(,\\s)]"," , ");
        return message;
    }

    public static void main(String[] args) {
        ParserService service = new ParserService();
        String message = "Hi. this, is ,vinay .are you . 88.97 ";

        System.out.println(message);

    }

}
