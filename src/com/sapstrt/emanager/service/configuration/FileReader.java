package com.sapstrt.emanager.service.configuration;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cambas on 10/5/13.
 */
public class FileReader {

    public String readFile(Context c) {
        String isConfigured=null;
        FileInputStream in=null;
        try {
            in=c.openFileInput("config_file");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String fileData=reader.readLine();
            isConfigured=fileData.substring(fileData.indexOf("=")+1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  isConfigured;
    }
}
