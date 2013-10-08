package com.sapstrt.emanager.service.configuration;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by cambas on 10/5/13.
 */
public class FileReader {

    public String readFile(Context c)
    {
        String isConfigured=null;
        AssetManager assetManager=c.getAssets();
        Properties prop=new Properties();
        try {

            InputStream in=assetManager.open("configuration.properties");
            prop.load(in);
            isConfigured=prop.getProperty("isConfigured");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  isConfigured;
    }
}
