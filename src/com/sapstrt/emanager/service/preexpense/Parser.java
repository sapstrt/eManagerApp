package com.sapstrt.emanager.service.preexpense;

import android.telephony.SmsMessage;

import java.util.Map;

/**
 * Created by vvarma on 9/29/13.
 */
public interface Parser {
    Map.Entry<String,String> parseInformationFromText(String[] messageTokens);
}
