package com.example.android.androidapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by anjana on 6/14/16.
 */
public class InternetUtils {

    // http://stackoverflow.com/questions/4238921/detect-whether-there-is-an-internet-connection-available-on-android

    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo info = InternetUtils.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }
}
