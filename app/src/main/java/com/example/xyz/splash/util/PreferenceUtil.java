package com.example.xyz.splash.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xyz on 29/10/17.
 */

public class PreferenceUtil {

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LOGIN_DETAILS = "login_details";

    public static void saveSignupCredentials(Context context, String prefName, String email, String password) {
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_DETAILS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public static String fetchPrefString(Context context, String prefName, String key) {
        if(context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, null);
        }
        return null;
    }
}
