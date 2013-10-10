package com.sapstrt.emanager.service.configuration;

import android.content.Context;

/**
 * Created by cambas on 10/5/13.
 */
public class Configure {

    public boolean getConfiguration(Context c)
    {
        boolean isConfigurationDone;
        FileReader reader=new FileReader();
        String fileValue=reader.readFile(c);
        isConfigurationDone=fileValue.equalsIgnoreCase("true");
        return isConfigurationDone;
    }
}
