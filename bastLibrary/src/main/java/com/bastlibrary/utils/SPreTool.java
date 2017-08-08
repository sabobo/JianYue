package com.bastlibrary.utils;

import android.content.Context;

import com.bastlibrary.utils.SPreferences.SharedPreferencesTool;

/**
 * Created by 储存值 on 2017/6/27 0027.
 */

public class SPreTool {
    private static SPreTool mInstance;
    private final String profile_name = "userinfo";
    private static final String PREFERENCES_UP_APK_INFO_isUp = "isUp";
    private static final String PREFERENCES_UP_APK_INFO_VERSION = "version";
    private static final String PREFERENCES_UP_APK_INFO_MESSAGE = "message";
    private static final String PREFERENCES_UP_APK_INFO_URL = "upLoadApkUrl";
    public synchronized static SPreTool getInstance() {
        if (mInstance == null) {
            mInstance = new SPreTool();
        }
        return mInstance;
    }

    public SPreTool() {
    }

    public static void clearPreferences(Context ctx, String profileName) {
        SPreTool.clearPreferences(ctx, profileName);
    }

    public void putValue(Context ctx, String key, Object value) {
        SharedPreferencesTool.putValue(ctx, profile_name, key, value);
    }

    public int getIntValue(Context ctx, String key) {
        return SharedPreferencesTool.getIntValue(ctx, profile_name, key);
    }

    public String getStringValue(Context ctx, String key) {
        return SharedPreferencesTool.getStringValue(ctx, profile_name, key);
    }

    public boolean getBooleanValue(Context ctx, String key) {
        return SharedPreferencesTool.getBooleanValue(ctx, profile_name, key);
    }

    public boolean getBooleanValue(Context ctx, String key, boolean defVal) {
        return SharedPreferencesTool.getBooleanValue(ctx, profile_name, key, defVal);
    }

    public long getLongValue(Context ctx, String key) {
        return SharedPreferencesTool.getLongValue(ctx, profile_name, key);
    }


    public void saveUpLoadApk(Context context, Boolean isUp, String VersionCode, String message, String URl) {
        SPreTool.getInstance().putValue(context, PREFERENCES_UP_APK_INFO_isUp, isUp);
        SPreTool.getInstance().putValue(context, PREFERENCES_UP_APK_INFO_VERSION, VersionCode);
        SPreTool.getInstance().putValue(context, PREFERENCES_UP_APK_INFO_MESSAGE, message);
        SPreTool.getInstance().putValue(context, PREFERENCES_UP_APK_INFO_URL, URl);
    }
}
