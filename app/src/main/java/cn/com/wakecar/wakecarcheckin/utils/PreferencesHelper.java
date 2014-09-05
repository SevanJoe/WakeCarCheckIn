package cn.com.wakecar.wakecarcheckin.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sevan on 2014/8/3 0003.
 */
public class PreferencesHelper {
    private static final String FILE_NAME = "default";

    private static SharedPreferences sharedPreferences;

    public static void init(final Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static boolean getBoolForKey(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static int getIntForKey(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static float getFloatForKey(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static long getLongForKey(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static String getStringForKey(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void setBoolForKey(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setIntForKey(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setFloatForKey(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void setLongForKey(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void setStringForKey(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
