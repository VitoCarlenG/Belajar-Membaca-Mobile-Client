package com.example.uts_pbp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_PLAY="isPlay";

    public UserPreferences(Context context) {
        this.context=context;
        sharedPreferences=context.getSharedPreferences("userPreferences",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void setPlay() {
        editor.putBoolean(IS_PLAY, true);
        editor.commit();
    }

    public boolean checkPlay() {
        return sharedPreferences.getBoolean(IS_PLAY, false);
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
