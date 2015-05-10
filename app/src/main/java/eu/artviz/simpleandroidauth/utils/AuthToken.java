package eu.artviz.simpleandroidauth.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class AuthToken {

    private static String cachedToken = null;
    private static SharedPreferences sharedPref = null;

    public static String getToken(Context context) {
        if (cachedToken == null) {
            if (sharedPref == null) {
                sharedPref = context
                        .getSharedPreferences("eu.artviz.simpleandroidauth.AUTH", Context.MODE_PRIVATE);
            }

            cachedToken = sharedPref.getString("token", null);
        }

        return cachedToken;
    }

    public static void setToken(Context context, String token) {
        if (sharedPref == null) {
            sharedPref = context
                    .getSharedPreferences("eu.artviz.simpleandroidauth.AUTH", Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", token);
        editor.apply();

        cachedToken = token;
    }
}
