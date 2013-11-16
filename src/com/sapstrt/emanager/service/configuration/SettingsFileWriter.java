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
    private static final SettingsFileWriter settingsFileWriter=new SettingsFileWriter();

    private SettingsFileWriter(){

    }
    public static SettingsFileWriter getInstance(){
        return settingsFileWriter;
    }
    public void writeToSettingsFile(Context c, Map<String,String> banksList)
    {

        FileOutputStream fos = null;
         try {

             for (Map.Entry<String, String> entry : banksList.entrySet())
             {
                String settingsText=entry.getKey()+"="+entry.getValue();
                fos = c.openFileOutput(FILENAME, Context.MODE_PRIVATE);
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
        String value="";
        try {
            FileInputStream fis=context.openFileInput(FILENAME);
           //code to read file n get value for required key
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value ;
    }
}
