package com.sapstrt.emanager.service.configuration;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by cambas on 10/9/13.
 */
public class InterFileService {

    public void createInternalFile(Context context)
    {
        String FILENAME = "config_file";
        String string = "isConfigured=false";

        FileOutputStream fos = null;
        FileReader reader=new FileReader();
        if(reader.readFile(context).equalsIgnoreCase("false"))
        {
        try {
            fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

        public void writeInternalFile(Context context)
        {
            String FILENAME = "config_file";
            String string = "isConfigured=true";

            FileOutputStream fos = null;
            try {
                fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                fos.write(string.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

