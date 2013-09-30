package com.sapstrt.emanager.service.preexpense.filter;

import android.telephony.SmsMessage;

import java.util.List;

/**
 * Created by vvarma on 9/29/13.
 */
public interface MessageFilter {

    boolean isUsefulMessage(SmsMessage sms);
}
