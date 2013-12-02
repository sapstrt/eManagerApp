package com.sapstrt.emanager.service.util;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

/**
 * Created by vvarm1 on 10/4/13.
 */
public class LocationServiceNotUsed implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {

   public void onConnected(Bundle dataBundle) {}

   public void onDisconnected() {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public String getDeviceLocation() {
        return "Bangalore";
    }

    public String getDeviceLocation(Context context) {
        Location mLocation;

        LocationClient locationClient = new LocationClient(context, this, this);
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS){
            mLocation=locationClient.getLastLocation();
            return mLocation.toString();
        }
        return "default";

    }
}
