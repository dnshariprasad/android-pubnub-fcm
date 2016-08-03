package com.pubnubchat.util;

import android.content.Context;
import android.provider.Settings;

import java.util.List;

/**
 * Created by Hari on 8/3/16.
 */
public class Util {
    public static boolean isNotNullAndNotEmpty(String string) {
        return string != null && !string.isEmpty();
    }

    public static boolean isNotNullAndNotEmpty(List list) {
        return list != null && !list.isEmpty();
    }

    public static String getUdid(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
