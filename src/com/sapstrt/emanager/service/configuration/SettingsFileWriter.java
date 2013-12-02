package com.sapstrt.emanager.service.configuration;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by cambas on 10/8/13.
 */
public class SettingsFileWriter {

    String FILENAME = "settings_file";
    public void writeToSettingsFile(Context context , Map<String,String> banksList)
    {

        FileOutputStream fos = null;
         try {

             for (Map.Entry<String, String> entry : banksList.entrySet())
             {
                String settingsText=entry.getKey()+"="+entry.getValue();
                fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                fos.write(settingsText.getBytes());
                fos.close();
             }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public String getDataFromSettingsFile(Context context,String key){
        String value=null;

        FileInputStream fis=null;
        try {
            fis= context.openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int content;
        try {
            while ((content = fis.read()) != -1) {

                String entry=(char) content+"";
                String entryKey=entry.substring(0,entry.indexOf('='));
                String entryValue=entry.substring(entry.indexOf('=')+1);
                if(entryKey.equalsIgnoreCase(key)){
                    return entryValue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
