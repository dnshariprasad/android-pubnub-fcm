package com.pubnubchat.util;

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
}
