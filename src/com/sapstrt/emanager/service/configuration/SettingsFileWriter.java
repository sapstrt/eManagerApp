package com.sapstrt.emanager.service.configuration;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by cambas on 10/8/13.
 */
public class SettingsFileWriter {

    public void writeToSettingsFile(Context c, Map<String,String> banksList)
    {
        String FILENAME = "settings_file";


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
}
