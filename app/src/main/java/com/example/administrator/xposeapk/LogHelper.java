package com.example.administrator.xposeapk;

import android.util.Log;

import de.robv.android.xposed.XposedBridge;

/**
 * Created by Administrator on 2017/3/16.
 */
public class LogHelper {
    public static void Log(String str, boolean showinxposed) {
        String logstr = (new java.util.Date()).toString() + ":(wechat)" + str;
        if (showinxposed) {
            XposedBridge.log(logstr);
        }
        Log.d("yuzhao", str);
    }
}
