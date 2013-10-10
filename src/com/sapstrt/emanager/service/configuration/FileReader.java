package com.sapstrt.emanager.service.configuration;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            return "false";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  isConfigured;
    }
    public List<String> readSettingsFile(Context c) {
        String isConfigured=null;
        List<String> listOfBanks=new ArrayList<>();
        FileInputStream in=null;
        try {
            in=c.openFileInput("settings_file");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String fileData=null;
                    while((fileData=reader.readLine())!=null)
                    {
                        listOfBanks.add(fileData.substring(fileData.indexOf("=")+1));

                    }

        } catch (FileNotFoundException e) {


        } catch (IOException e) {

        }
        return  listOfBanks;
    }
}
