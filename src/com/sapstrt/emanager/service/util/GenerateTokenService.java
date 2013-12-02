package com.sapstrt.emanager.service.util;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.sapstrt.emanager.service.configuration.SettingsFileWriter;

import java.io.IOException;

/**
 * Created by pteltu on 10/24/13.
 */
public class GenerateTokenService {

    private String TAG="com.sapstrt.resttokenmyprojectTag";
    /*String mScope="audience:server:client_id:830805664955-0bir467ucnddlqftqdain4949b40f0mh.apps.googleusercontent.com";*/

    String mScope="audience:server:client_id:830805664955-eltb9g08j7eom61c6l07po5bqi1hj6do.apps.googleusercontent.com";



    public String getToken(Context context) {
            String token = null;
            SettingsFileWriter settingsFileWriter=new SettingsFileWriter();

           String emailId= settingsFileWriter.getDataFromSettingsFile(context,"GoogleAccount");
            try {
                token = GoogleAuthUtil.getToken(context, emailId, mScope);
            } catch (IOException transientEx) {
                Log.e(TAG, transientEx.toString());
            } catch (UserRecoverableAuthException e) {
                Log.e(TAG, e.toString());
            } catch (GoogleAuthException authEx) {
                Log.e(TAG, authEx.toString());
            }

            if(token!=null){
                Log.i(TAG, "Access token retrieved:" + token);
            }


        return token;
    }
}
