package com.sapstrt.emanager.service.preexpenseNotUsed.parser;

import java.util.Map;

/**
 * Created by vvarma on 9/29/13.
 */
public interface Parser {
    @Deprecated
    Map.Entry<String,String> parseInformationFromText(String messageTokens);
    Map.Entry<String,String> getExpenseMapGivenKeyWordMap(Map<String,String> keywordMap);

}
