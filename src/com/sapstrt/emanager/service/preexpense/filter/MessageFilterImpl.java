package com.sapstrt.emanager.service.preexpense.filter;

import android.content.Context;
import android.telephony.SmsMessage;
import android.util.Log;

import com.sapstrt.emanager.service.configuration.FileReader;
import com.sapstrt.emanager.service.preexpense.filter.MessageFilter;
import com.sapstrt.emanager.service.util.SMSData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vvarma on 9/29/13.
 */
public class MessageFilterImpl implements MessageFilter {


    private boolean isUsefulMessage(String address,Context context) {
        List<String> addresses=getUsefulAddress(context);
        for(String s:addresses){
            if (address.contains(s))
                return true;
        }
        return false;
    }

    @Override
    public boolean isUsefulMessage(SmsMessage sms,Context context) {
        return isUsefulMessage(sms.getOriginatingAddress(),context);
    }

    @Override
    public boolean isUsefulMessage(SMSData sms,Context context) {
        return isUsefulMessage(sms.getMessageAddress(),context);
    }

    List<String> getUsefulAddress(Context context){
        Log.d("com.sapstrt.emanager","useful addresses");


        List<String> addresses;
        FileReader reader=new FileReader();
        addresses=reader.readSettingsFile(context);
        Log.d("com.sapstrt.emanager","addresses "+addresses.toString());
        return addresses;
    }
}
