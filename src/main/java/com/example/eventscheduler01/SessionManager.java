package com.example.eventscheduler01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "LoginPreference";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;
    private Context context;  // Add context field to use for navigation intents.

    public SessionManager(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void logoutUser() {
        // Clear the SharedPreferences
        editor.clear();
        editor.apply();

        // Optionally, you can add an intent to redirect the user to the Login Activity or similar
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
