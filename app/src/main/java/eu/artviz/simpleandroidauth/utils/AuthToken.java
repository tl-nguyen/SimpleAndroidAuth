package eu.artviz.simpleandroidauth.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class AuthToken {

    private SharedPreferences sharedPref = null;

    public AuthToken(Context context) {
        sharedPref = context
                .getSharedPreferences("eu.artviz.simpleandroidauth.AUTH", Context.MODE_PRIVATE);
    }

    public String getToken() {
        return sharedPref.getString("token", null);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", token);
        editor.apply();
    }
}
