package com.sapstrt.emanager.service.util;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.sapstrt.emanager.activity.DrawerActivity;
import com.sapstrt.emanager.service.configuration.SettingsFileWriter;

import java.io.IOException;

/**
 * Created by pteltu on 10/24/13.
 */
public class GenerateTokenService {

    private String TAG="com.sapstrt.resttokenmyprojectTag";
    String mScope="audience:server:client_id:830805664955-0bir467ucnddlqftqdain4949b40f0mh.apps.googleusercontent.com";



    public String getToken(Context context) {
            String token = null;
            String emailId= SettingsFileWriter.getInstance().getDataFromSettingsFile(context,"Account");
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
