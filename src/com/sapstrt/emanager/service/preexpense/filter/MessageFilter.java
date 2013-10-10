package com.sapstrt.emanager.service.preexpense.filter;

import android.content.Context;
import android.telephony.SmsMessage;

import com.sapstrt.emanager.service.util.SMSData;

import java.util.List;

/**
 * Created by vvarma on 9/29/13.
 */
public interface MessageFilter {

    boolean isUsefulMessage(SmsMessage sms,Context context);
    boolean isUsefulMessage(SMSData sms,Context context);
}
