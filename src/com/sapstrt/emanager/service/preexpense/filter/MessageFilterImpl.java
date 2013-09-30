package com.sapstrt.emanager.service.preexpense.filter;

import android.telephony.SmsMessage;

import com.sapstrt.emanager.service.preexpense.filter.MessageFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vvarma on 9/29/13.
 */
public class MessageFilterImpl implements MessageFilter {


    public boolean isUsefulMessage(SmsMessage sms) {

        if (getUsefulAddress().contains(sms.getOriginatingAddress())){
            return true;
        }
        return false;
    }
    List<String> getUsefulAddress(){
        List<String> addresses=null;
        addresses=new ArrayList<>();
        addresses.add("990");
        addresses.add("111");
        return addresses;
    }
}
