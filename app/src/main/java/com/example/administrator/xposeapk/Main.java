package com.example.administrator.xposeapk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.LauncherActivity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodBestMatch;
import static de.robv.android.xposed.XposedHelpers.newInstance;


public class Main implements IXposedHookLoadPackage {
    private String mtype;
    private String misSend;
    private String mcontent;
    private String mtalker;
    private static Activity launcherUiActivity;
    private static Activity AlbumPreviewUI;
    private Object galleryStubService = null;
    private boolean insertquanflag = false;
    private ArrayList<String> userslists;

    private static boolean ALIPAY_PACKAGE_ISHOOK = false;


    private double gpsX = 24.306584;
    private double gpsY = 109.38846;

    private long lasttick = 0;
    private boolean yaoyiyaoenble = false;


    private ServiceConnection serviceConnection;

    private List<String> systemName = Arrays.asList("setBounds", "sleep", "hashCode", "notify", "toString", "getClass", "notifyAll", "wait", "equals", "get", "set", "toArray", "listIterator", "loadClass", "subList");


    private boolean flagclickbegin = false;

    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.tencent.mm")) {
            return;
        }

        if (!lpparam.processName.equals("com.tencent.mm")) {
            return;
        }


//        if (!lpparam.processName.startsWith("com.tencent")) {
//            return;
//        }

//
//        if (!lpparam.packageName.equals("com.eg.android.AlipayGphone")) {
//            return;
//        }
//        log("  zhifubao 监听" + "\n");


//        final Class<?> myclass = XposedHelpers.findClass("com.alipay.mobile.base.security.CI", lpparam.classLoader);
//
//        final Method[] ms = myclass.getDeclaredMethods();
//        Log.d("mylog", "count " + ms.length + "");
//        for (int i = 0; i < ms.length; i++) {
//            Log.d("mylog", "hook name:" + ms[i].getName());
//            try {
//                final int finalI = i;
//                Log.d("mylog", "hook name:" + ms[i].getName());
//                XposedBridge.hookMethod(ms[i], new XC_MethodHook() {
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                        String mename = ms[finalI].getName();
//                        Log.d("mylog", "name:" + mename);
//                        if (param.args != null) {
//                            for (int i = 0; i < param.args.length; i++) {
//
//                                Log.d("mylog", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");
//
//
//
//                            }
//                        }
//
//
//                        if (param.getResult() != null) {
//                            if(ms[finalI].getName().equals("RL")){
//
//
//                            }
//                            Log.d("mylog", "return :" + param.getResult().toString());
//
//                        }
//
//
//                    }
//                });
//            } catch (Exception err) {
//
//                log("error：" + err.getMessage());
//            }
//        }
//
//
//        final Class<?> myclass1 = XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService", lpparam.classLoader);
//
//        final Method[] ms1 = myclass1.getDeclaredMethods();
//        Log.d("my1log", "count " + ms1.length + "");
//        for (int i = 0; i < ms1.length; i++) {
//            Log.d("my1log", "hook name:" + ms1[i].getName());
//            try {
//                final int finalI = i;
//                Log.d("my1log", "hook name:" + ms1[i].getName());
//                XposedBridge.hookMethod(ms1[i], new XC_MethodHook() {
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                        String mename = ms1[finalI].getName();
//                        Log.d("my1log", "name:" + mename);
//                        if (param.args != null) {
//                            for (int i = 0; i < param.args.length; i++) {
//
//                                Log.d("my1log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");
//
//
//
//                            }
//                        }
//
//
//                        if (param.getResult() != null) {
//                            if(ms1[finalI].getName().equals("RL")){
//
//
//                            }
//                            Log.d("my1log", "return :" + param.getResult().toString());
//
//                        }
//
//
//                    }
//                });
//            } catch (Exception err) {
//
//                log("error：" + err.getMessage());
//            }
//        }

//        Class<?> classappy = XposedHelpers.findClass("com.alipay.mobile.base.security.CI",lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(classappy, "a", classappy, Activity.class, new XC_MethodReplacement() {
//            @Override
//            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                log("返回");
//                return null;
//            }
//        });


//                XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.mobile.security.login.ui.AlipayUserLoginActivity",lpparam.classLoader), "onCreate", Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  afadfafaf LauncherActivity MainActivity" + "\n");
//            }
//
//        });
//
//
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), "onCreate", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  afadfafaf LauncherActivity StandAloneService" + "\n");
//            }
//
//        });
//
//
//
//        Class<?> classappy1 = XposedHelpers.findClass("com.alipay.mobile.quinox.LauncherActivity",lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(classappy1, "onCreate", Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("LauncherActivity onCreated" + "\n");
//            }
//
//        });


        //     hook(lpparam.classLoader,null);

//        if (lpparam.appInfo != null && (lpparam.appInfo.flags & 129) == 0) {
//            final String packageName = lpparam.packageName;
//            // ApplicationInfo.FLAG_SYSTEM
//            final String processName = lpparam.processName;
//            if ("com.eg.android.AlipayGphone".equals(packageName)) {
//                try {
//
//                    XposedHelpers.findAndHookMethod(Application.class, "attach", new Object[]{Context.class, new XC_MethodHook() {
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.afterHookedMethod(param);
//                            Context context = (Context) param.args[0];
//                            ClassLoader appClassLoader = context.getClassLoader();
//                            if ("com.eg.android.AlipayGphone".equals(processName) && !ALIPAY_PACKAGE_ISHOOK) {
//                                ALIPAY_PACKAGE_ISHOOK = true;;
//                                XposedBridge.log("handleLoadPackage: " + packageName);
//                                Toast.makeText(context, "获取到alipay=>>classloader", 1).show();
//                                log("hook住了");
//                                hook(appClassLoader, context);
//
////                                Context mContext = (Context) XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.app.ActivityThread", null), "currentActivityThread", new Object[0]), "getSystemContext", new Object[0]);
////                                Context wxcontext = mContext.createPackageContext(lpparam.packageName, Context.CONTEXT_IGNORE_SECURITY);
////                                new AliPayHook().hook(lpparam.classLoader,wxcontext);
//                            }
//                        }
//                    }});
//                } catch (Throwable e) {
//                    XposedBridge.log(e);
//                }
//            }
//        }


//        Class<?> securityCheckClazz = XposedHelpers.findClass("com.alipay.mobile.base.security.CI", lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new Object[]{String.class, String.class, String.class, new XC_MethodHook() {
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                log("  zhifubao security.CI 1" + "\n");
//                Object object = param.getResult();
//                XposedHelpers.setBooleanField(object, "a", false);
//                param.setResult(object);
//                super.afterHookedMethod(param);
//            }
//        }});
//        XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new Object[]{Class.class, String.class, String.class, new XC_MethodReplacement() {
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log("  zhifubao security.CI 2" + "\n");
//                return Byte.valueOf((byte) 1);
//            }
//        }});
//        XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new Object[]{ClassLoader.class, String.class, new XC_MethodReplacement() {
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log("  zhifubao security.CI 3" + "\n");
//                return Byte.valueOf((byte) 1);
//            }
//        }});
//        XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new Object[]{new XC_MethodReplacement() {
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log("  zhifubao security.CI 4" + "\n");
//                return Boolean.valueOf(false);
//            }
//        }});

//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.mobile.security.login.ui.AlipayUserLoginActivity",lpparam.classLoader), "onCreate", Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  afadfafaf LauncherActivity MainActivity" + "\n");
//            }
//
//        });
//          XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), "a",XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  1 " + "\n");
//                Log.i("Dump Stack: ", "---------------start----------------");
//
//                Throwable ex = new Throwable();
//
//                StackTraceElement[] stackElements = ex.getStackTrace();
//
//                if (stackElements != null) {
//
//                    for (int i = 0; i < stackElements.length; i++) {
//
//
//
//                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//
//                                +"----"+stackElements[i].getFileName()
//
//                                +"----" + stackElements[i].getLineNumber()
//
//                                +"----" +stackElements[i].getMethodName());
//
//                    }
//
//                }
//
//                Log.i("Dump Stack: ", "---------------over----------------");
//            }
//
//        });
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), "a", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  2 " + "\n");
//                Log.i("Dump Stack: ", "---------------start----------------");
//
//                Throwable ex = new Throwable();
//
//                StackTraceElement[] stackElements = ex.getStackTrace();
//
//                if (stackElements != null) {
//
//                    for (int i = 0; i < stackElements.length; i++) {
//
//
//
//                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//
//                                +"----"+stackElements[i].getFileName()
//
//                                +"----" + stackElements[i].getLineNumber()
//
//                                +"----" +stackElements[i].getMethodName());
//
//                    }
//
//                }
//
//                Log.i("Dump Stack: ", "---------------over----------------");
//            }
//
//        });
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), "b",XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  3 " + "\n");
//                Log.i("Dump Stack: ", "---------------start----------------");
//
//                Throwable ex = new Throwable();
//
//                StackTraceElement[] stackElements = ex.getStackTrace();
//
//                if (stackElements != null) {
//
//                    for (int i = 0; i < stackElements.length; i++) {
//
//
//
//                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//
//                                +"----"+stackElements[i].getFileName()
//
//                                +"----" + stackElements[i].getLineNumber()
//
//                                +"----" +stackElements[i].getMethodName());
//
//                    }
//
//                }
//
//                Log.i("Dump Stack: ", "---------------over----------------");
//            }
//
//        });
//
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), "b",Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  4 " + "\n");
//                Log.i("Dump Stack: ", "---------------start----------------");
//
//                Throwable ex = new Throwable();
//
//                StackTraceElement[] stackElements = ex.getStackTrace();
//
//                if (stackElements != null) {
//
//                    for (int i = 0; i < stackElements.length; i++) {
//
//
//
//                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//
//                                +"----"+stackElements[i].getFileName()
//
//                                +"----" + stackElements[i].getLineNumber()
//
//                                +"----" +stackElements[i].getMethodName());
//
//                    }
//
//                }
//
//                Log.i("Dump Stack: ", "---------------over----------------");
//            }
//
//        });
//
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), "c",Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  5 " + "\n");
//                Log.i("Dump Stack: ", "---------------start----------------");
//
//                Throwable ex = new Throwable();
//
//                StackTraceElement[] stackElements = ex.getStackTrace();
//
//                if (stackElements != null) {
//
//                    for (int i = 0; i < stackElements.length; i++) {
//
//
//
//                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//
//                                +"----"+stackElements[i].getFileName()
//
//                                +"----" + stackElements[i].getLineNumber()
//
//                                +"----" +stackElements[i].getMethodName());
//
//                    }
//
//                }
//
//                Log.i("Dump Stack: ", "---------------over----------------");
//            }
//
//        });
//
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.android.phone.wallet.inwallet.service.StandAloneService",lpparam.classLoader), "d",Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  6 " + "\n");
//                Log.i("Dump Stack: ", "---------------start----------------");
//
//                Throwable ex = new Throwable();
//
//                StackTraceElement[] stackElements = ex.getStackTrace();
//
//                if (stackElements != null) {
//
//                    for (int i = 0; i < stackElements.length; i++) {
//
//
//
//                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//
//                                +"----"+stackElements[i].getFileName()
//
//                                +"----" + stackElements[i].getLineNumber()
//
//                                +"----" +stackElements[i].getMethodName());
//
//                    }
//
//                }
//
//                Log.i("Dump Stack: ", "---------------over----------------");
//            }
//
//        });

//        XposedHelpers.findAndHookMethod(Application.class, "attach",Context.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  7 " + "\n");
//                Log.i("Dump Stack: ", "---------------start----------------");
//
//                Throwable ex = new Throwable();
//
//                StackTraceElement[] stackElements = ex.getStackTrace();
//
//                if (stackElements != null) {
//
//                    for (int i = 0; i < stackElements.length; i++) {
//
//
//
//                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//
//                                +"----"+stackElements[i].getFileName()
//
//                                +"----" + stackElements[i].getLineNumber()
//
//                                +"----" +stackElements[i].getMethodName());
//
//                    }
//
//                }
//
//                Log.i("Dump Stack: ", "---------------over----------------");
//            }
//
//        });


//        if (!lpparam.packageName.equals("com.eg.android.AlipayGphone")) {
//            return;
//        }
//        log("进入支付宝监听");
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.apmobilesecuritysdk.scanattack.common.ScanAttack",lpparam.classLoader), "xpInstalled", Context.class, new XC_MethodReplacement() {
//            @Override
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log("ScanAttack" + "\n");
//                return false;
//            }
//
//        });
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.apmobilesecuritysdk.scanattack.common.ScanAttack",lpparam.classLoader), "xpFieldInHook", Context.class, new XC_MethodReplacement() {
//            @Override
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log("ScanAttack xpFieldInHook" + "\n");
//                return null;
//            }
//
//        });
//
//
//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.apmobilesecuritysdk.scanattack.common.ScanAttack",lpparam.classLoader), "xpMethodInHook", Context.class, new XC_MethodReplacement() {
//            @Override
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log("ScanAttack xpMethodInHook" + "\n");
//                return null;
//            }
//
//        });
//
//
//
//
//
//        Class<?> classappy = XposedHelpers.findClass("com.alipay.mobile.base.security.CI",lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(classappy, "a", classappy, Activity.class, new XC_MethodReplacement() {
//            @Override
//            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                log("返回");
//                return null;
//            }
//        });
////        log("进入淘宝监听1");
//
//        Class<?> classappy1 = XposedHelpers.findClass("com.alipay.mobile.quinox.LauncherActivity",lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(classappy1, "onCreate", Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("LauncherActivity onCreated" + "\n");
//            }
//
//        });


//        log("进入淘宝监听1");
//
//
//        XposedHelpers.findAndHookMethod("com.alipay.mobile.quinox.LauncherActivity", lpparam.classLoader, "onCreate",  Bundle.class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("LauncherActivity onCreated 1" + "\n");
//
//
//                super.beforeHookedMethod(param);
//            }
//
//        });
        /*
        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.scanner.ui.BaseScanUI", lpparam.classLoader, "a",  String.class,int.class,int.class,int.class,XposedHelpers.findClass("com.tencent.mm.plugin.scanner.util.e$a",lpparam.classLoader),new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object o1 = param.args[0];
                Object o2 = param.args[1];
                Object o3 = param.args[2];
                Object o4 =param.args[3];
//                int o5 = (int)param.args[4];
//                long o6 = (long)param.args[5];
//                long o7 = (long)param.args[6];

                Log.d("yuzhao","Log1::"+o1+"---Log2:"+o2+"---Log3:"+o3+"---Log4:"+o4);
                //Log.d("yuzhao","LOd:0:"+tableName+"---"+a2);



                super.beforeHookedMethod(param);
            }

        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.scanner.util.e", lpparam.classLoader, "a",  int.class,int.class,String.class,XposedHelpers.findClass("com.tencent.mm.ac.l",lpparam.classLoader),new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object o1 = param.args[0];
                Object o2 = param.args[1];
                Object o3 = param.args[2];
                Object o4 =param.args[3];
//                int o5 = (int)param.args[4];
//                long o6 = (long)param.args[5];
//                long o7 = (long)param.args[6];

                log("Log1::"+o1+"---Log2:"+o2+"---Log3:"+o3+"---Log4:"+o4);
                //Log.d("yuzhao","LOd:0:"+tableName+"---"+a2);



                super.beforeHookedMethod(param);
            }

        });


        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.remittance.ui.RemittanceBaseUI", lpparam.classLoader, "onCreate",  Bundle.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Activity a1 = (Activity) param.thisObject;
                Object o2 = a1.getIntent().getStringExtra("receiver_name");
                log("RemittanceBaseUI::"+o2);



                super.beforeHookedMethod(param);
            }

        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.pluginsdk.wallet.h", lpparam.classLoader, "a",  Context.class, int.class, String.class, int.class, XposedHelpers.findClass("com.tencent.mm.plugin.wallet.a",lpparam.classLoader) ,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object a1 = param.args[2];
                log("com.tencent.mm.pluginsdk.wallet.h::"+a1);



                super.beforeHookedMethod(param);
            }

        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.bh.d", lpparam.classLoader, "b",  Context.class, String.class, String.class, Intent.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object o1 = param.args[0];
                Object o2 = param.args[1];
                Object o3 = param.args[2];
                Object o4 =param.args[3];


                log("com.tencent.mm.bh.d Log1::"+o1+"---Log2:"+o2+"---Log3:"+o3+"---Log4:"+o4);

                Intent a1 = (Intent) o4;
                String a2 = a1.getStringExtra("receiver_name");
                log("com.tencent.mm.bh.d receiver_name ::"+a2);



                super.beforeHookedMethod(param);
            }

        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.bh.d", lpparam.classLoader, "a",  Context.class, String.class, String.class,  Intent.class, boolean.class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                Object o1 = param.args[0];
                Object o2 = param.args[1];
                Object o3 = param.args[2];
                Object o4 =param.args[3];
                Object o5 =param.args[4];


                log("com.tencent.mm.bh.d a Log1::"+o1+"---Log2:"+o2+"---Log3:"+o3+"---Log4:"+o4+"---Log5:"+o5);

                Intent a1 = (Intent) o4;
                String a2 = a1.getStringExtra("receiver_name");
                log("com.tencent.mm.bh.d a receiver_name ::"+a2);




            }

        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.remittance.ui.RemittanceAdapterUI", lpparam.classLoader, "onCreate",  Bundle.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Activity a1 = (Activity) param.thisObject;
                Object o2 = a1.getIntent().getStringExtra("receiver_name");
                log("RemittanceAdapterUI::"+o2);



                super.beforeHookedMethod(param);
            }

        });

//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "d", String.class,String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.d" + a1 + "------------------param2：" + a2);
//
//
//                super.beforeHookedMethod(param);
//            }
//
//        });


        XposedHelpers.findAndHookMethod("com.tencent.mm.wallet_core.c.i", lpparam.classLoader, "e", int.class,int.class,String.class,XposedHelpers.findClass("com.tencent.mm.network.q",lpparam.classLoader),new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object a0 = param.thisObject;

                Object a1 = XposedHelpers.getObjectField(a0,"AbQ");
                Object a2 = XposedHelpers.getObjectField(a0,"AbR");
                Object a3 = XposedHelpers.getObjectField(a0,"AbS");
                Object a4 = XposedHelpers.getObjectField(a0,"ttG");
                Object a5 = XposedHelpers.getObjectField(a0,"ttH");
                int a6 = XposedHelpers.getIntField(a0,"ttI");

                log("com.tencent.mm.wallet_core.c.i::"+a1+"---Log2:"+a2+"---Log3:"+a3+"---Log4:"+a4+"---Log5:"+a5+"---Log6:"+ a6);


//                this.AbQ = jSONObject2.optString("pay_flag");
//                this.AbR = jSONObject2.optString("return_url");
//                this.AbS = jSONObject2.optString("wappay_jumped_url");
//                this.ttG = jSONObject2.optInt("is_gen_cert");
//                this.ttH = jSONObject2.optString("crt_token");
//                this.ttI = jSONObject2.optInt("is_hint_crt");


            }

        });


        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.remittance.ui.RemittanceAdapterUI", lpparam.classLoader, "c",  String.class, int.class,Intent.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object o1 = param.args[0];
                Object o2 = param.args[1];
                Object o3 = param.args[2];


                log("com.tencent.mm.bh.d Log1::"+o1+"---Log2:"+o2+"---Log3:"+o3);

                Intent a1 = (Intent) o3;
                String a2 = a1.getStringExtra("receiver_name");



                log("com.tencent.mm.bh.d receiver_name ::"+a2);

                log("com.tencent.mm.plugin.remittance.ui.RemittanceAdapterUI " + "\n"+
                        "fee" + a1.getStringExtra("fee") + "desc"+ a1.getStringExtra("desc")+
                       "scan_remittance_id"+ a1.getStringExtra("scan_remittance_id")+
                       "receiver_true_name"+ a1.getStringExtra("receiver_true_name")+
                       "receiver_true_name_busi" +a1.getStringExtra("receiver_true_name_busi")+
                       "receiver_tips"+ a1.getStringExtra("receiver_tips")+
                         "rcvr_new_desc"   +    a1.getStringExtra("rcvr_new_desc")+
                        "payer_desc"+        a1.getStringExtra("payer_desc")+
                          "rcvr_open_id"    +  a1.getStringExtra("rcvr_open_id")+
                           "mch_name"+     a1.getStringExtra("mch_name")+
                            "mch_info"  +  a1.getStringExtra("mch_info")+
                           "mch_photo"  +   a1.getStringExtra("mch_photo")+
                           "mch_type"    + a1.getStringExtra("mch_type")+
                            "mch_time"+    a1.getStringExtra("mch_time"));



                super.beforeHookedMethod(param);
            }

        });












        /*
        serviceConnection= new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                galleryStubService = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.plugin.gallery.stub.a$a",lpparam.classLoader),"Q",iBinder);

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                galleryStubService = null;

            }
        };





        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                log("com.tencent.mm.ui.LauncherUI onCreated" + "\n");
                launcherUiActivity = (Activity) param.thisObject;
            }
        });
        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.gallery.ui.AlbumPreviewUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                log("com.tencent.mm.plugin.gallery.ui.AlbumPreviewUI onCreated" + "\n");
                AlbumPreviewUI = (Activity) param.thisObject;
            }
        });






        Class<?> dbClazz4 = XposedHelpers.findClass("com.tencent.mars.xlog.Xlog", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(dbClazz4,"logD", String.class, String.class, String.class, int.class, int.class, long.class, long.class, String.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Object o1 = param.args[0];
                Object o2 = param.args[1];
                Object o3 = param.args[2];
//                int o4 =(int) param.args[3];
//                int o5 = (int)param.args[4];
//                long o6 = (long)param.args[5];
//                long o7 = (long)param.args[6];
                Object o8 = param.args[7];
                Log.d("yuzhao","Log::"+o1+"---Log1:"+o2+"---Log2:"+o3+"---Log3:"+"---Log4:"+"---Log5:"+"---Log6:"+"---Log7:"+o8+"---");
                //Log.d("yuzhao","LOd:0:"+tableName+"---"+a2);
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //printTableInfo(param);
            }

        });
        XposedHelpers.findAndHookMethod(dbClazz4,"logI", String.class, String.class, String.class, int.class, int.class, long.class, long.class, String.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Object o1 = param.args[0];
                Object o2 = param.args[1];
                Object o3 = param.args[2];
//                int o4 =(int) param.args[3];
//                int o5 = (int)param.args[4];
//                long o6 = (long)param.args[5];
//                long o7 = (long)param.args[6];
                Object o8 = param.args[7];
                Log.d("yuzhao","Log:i:"+o1+"---Log1:"+o2+"---Log2:"+o3+"---Log3:"+"---Log4:"+"---Log5:"+"---Log6:"+"---Log7:"+o8+"---");
                //Log.d("yuzhao","LOd:0:"+tableName+"---"+a2);
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //printTableInfo(param);
            }

        });







        Class<?> dbClazz1 = XposedHelpers.findClass("com.tencent.mm.bu.f", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(dbClazz1, "insert", String.class, String.class, ContentValues.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {



                printTableInfo(param);




                String tableName = (String) param.args[0];




                //接受消息，isSend = 0;
                ContentValues val = (ContentValues)param.args[2];
                mtype = val.getAsString("type");
                misSend =  val.getAsString("isSend");
                mcontent=val.getAsString("content");
                mtalker = val.getAsString("talker");
                Log.d("yuzhao88",tableName + insertquanflag +mtype);

                if(insertquanflag && tableName.equals("rcontact") && mtype.equals("0") ){
                    String quanusername = val.getAsString("username");
                    Log.d("yuzhao88",quanusername);
                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.l",lpparam.classLoader),"a",quanusername,userslists,"你邀请%s加入了群聊",false,"");
                    userslists = null;
                    insertquanflag =false;
                    Log.d("yuzhao88","end");

                }
//                mtalker="medianote"; //语音记事本
//                Log.d("yuzhao","type:"+mtype+"isSend："+misSend+"content"+mcontent);
//                if(launcherUiActivity != null) {
//                    launcherUiActivity.bindService(new Intent(launcherUiActivity.getBaseContext(), XposedHelpers.findClass("com.tencent.mm.plugin.gallery.stub.GalleryStubService", lpparam.classLoader)), serviceConnection, 1);
//                }


                if(mtype.equals("1") && misSend.equals("0") && mcontent.equals("12")) {


//                    Intent intent = new Intent();
//                    intent.setClass(launcherUiActivity.getBaseContext(), XposedHelpers.findClass("com.tencent.mm.ui.chatting.ChattingUI",lpparam.classLoader));
//                    intent.putExtra("Chat_User", "9115052390@chatroom");
//                    intent.addFlags(67108864);
//                    launcherUiActivity.startActivity(intent);



                    // 初始化sendmessage环境
//                    Class<?> classEnvirinit= XposedHelpers.findClass("com.tencent.mm.hardcoder.HardCoderJNI", lpparam.classLoader);
//                    XposedHelpers.callStaticMethod(classEnvirinit,"startPerformance",true,0,3,0,
//                            XposedHelpers.getStaticIntField(classEnvirinit,"hcSendMsgTimeout"),1000,202,12288,"MicroMsg.ChattingUI.TextImp");

                    //构造消息类,content 为 hello
//                    Class<?> classConstructorMessage = XposedHelpers.findClass("com.tencent.mm.modelmulti.i", lpparam.classLoader);
//                    Object instanceConstructormessge = XposedHelpers.newInstance(classConstructorMessage, "9631051480@chatroom", "hello", 1, 0, null);

                    //存储数据库并发送消息

//                    Class<?> classSendmessge = XposedHelpers.findClass("com.tencent.mm.ac.o", lpparam.classLoader);
//                    //构造com.tencent.mm.ac.o$a 类
//                    Class<?> classSendparam = XposedHelpers.findClass("com.tencent.mm.ac.o$a", lpparam.classLoader);
//                    //获取sendmesage static 类
//                    Object instanceSendmessge = XposedHelpers.callStaticMethod(classSendmessge, "a", XposedHelpers.newInstance(classSendparam));
//                    //调用 存储数据库并发送消息函数
//                    XposedHelpers.callMethod(instanceSendmessge, "a", instanceConstructormessge, 0);
//                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au", lpparam.classLoader), "Dv");
//                    XposedHelpers.callMethod(sender, "a", instanceConstructormessge, 0);
                    //点击发送语音时的点击存储操作，做了数据库插入操作。
//                    Object filename= XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"nR",mtalker);
//                    if(filename != null ){
//                        Log.d("yuzhao","filename:"+filename);
//                    }else{
//                        Log.d("yuzhao","filename fail filename is null:");
//                    }
//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"W","4915080611189e19d0df4da106",1000);
//                    if(galleryStubService == null)
//                    {
//                        log("galleryStubService is null");
//                    }else{
//                        XposedHelpers.callMethod(galleryStubService,"a","/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-05-01-09-45-05-162_com.tencent.mm.png",
//                                "wxid_k2qvvyeecbhf21",true,0,true);
//                        XposedHelpers.callMethod(galleryStubService,"aj",11610,"2,0");
//                        XposedHelpers.callMethod(galleryStubService,"Aw");
//                        Intent intent = new Intent();
//                        intent.putExtra("CropImage_Compress_Img",true);
//                        ArrayList arrayList3 = new ArrayList();
//                        arrayList3.add("/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-05-01-09-45-05-162_com.tencent.mm.png");
//                        intent.putExtra("KSelectImgUseTime", 2000);
//
//                        intent.setClassName(launcherUiActivity.getBaseContext(), "com.tencent.mm.ui.chatting.SendImgProxyUI");
//                        intent.putStringArrayListExtra("CropImage_OutputPath_List", arrayList3);
//
//                        ArrayList arrayList5 = new ArrayList();
//                        arrayList5.add("/storage/emulated/0/tencent/MicroMsg/578026bf6c6b3bc5b902c43f65f9d07b/video/1654581306189e19d0d85741.mp4");
//                        intent.putStringArrayListExtra("key_select_video_list", arrayList5);
//                        intent.putExtra("GalleryUI_FromUser", "wxid_vq7m1vvo8oo22");
//                        intent.putExtra("GalleryUI_ToUser", "wxid_k2qvvyeecbhf21");
//                        intent.putExtra("CropImage_limit_Img_Size", 26214400);
//                        intent.putExtra("GalleryUI_IsSendImgBackground", true);
//                        launcherUiActivity.startActivityForResult(intent, 4373);
//                        XposedHelpers.callMethod(galleryStubService,"aj",14205,"1,1,1,1,0,0,false,false,false,false,false,false");



//                                Log.d("my1log", "=============== call my method ===================" );
//                                ArrayList arrayList3 = new ArrayList();
//                                arrayList3.add("/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-05-01-09-45-05-162_com.tencent.mm.png");
//                                ArrayList arrayList4 = new ArrayList();
//
//                                Object getSendPiclistAdm = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.am.n",lpparam.classLoader),"QQ");
//                                arrayList4 = (ArrayList)XposedHelpers.callMethod(getSendPiclistAdm,"lw","wxid_k2qvvyeecbhf21");
//
//                                Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.am.o", lpparam.classLoader), "OX");
//                                XposedHelpers.callMethod(sender, "a", arrayList4,"wxid_vq7m1vvo8oo22" ,"wxid_k2qvvyeecbhf21",arrayList3,0,false,2130837989);


                                //构造图片模型


//                        Class<?> classpic = XposedHelpers.findClass("com.tencent.mm.am.l",lpparam.classLoader);
//                        Object instancepic = XposedHelpers.newInstance(classpic,"wxid_vq7m1vvo8oo22", "wxid_k2qvvyeecbhf21", "/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-05-01-09-45-05-162_com.tencent.mm.png",0);
//                        //发送图片
//
//                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au", lpparam.classLoader), "Dv");
//                    XposedHelpers.callMethod(sender, "a", instancepic, 0);

                        //发送视频
//                        ArrayList arrayList = new ArrayList();
//                        arrayList.add("/storage/emulated/0/tencent/MicroMsg/578026bf6c6b3bc5b902c43f65f9d07b/video/1654581306189e19d0d85741.mp4");
//                        Class<?> classsmp4 = XposedHelpers.findClass("com.tencent.mm.pluginsdk.model.j",lpparam.classLoader);
//                        final Runnable sendmp4runnable = (Runnable)XposedHelpers.newInstance(classsmp4,launcherUiActivity.getBaseContext(), arrayList, null, "wxid_k2qvvyeecbhf21", 2, null);
//                        new Thread(sendmp4runnable).start();

                        //发送语音  --未成功，只看到插入数据库的操作

//                        Object objectSendviod = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.storage.az",lpparam.classLoader));
//                        XposedHelpers.callMethod(objectSendviod,"ed","wxid_k2qvvyeecbhf21");
//                        XposedHelpers.callMethod(objectSendviod,"setType",34);
//                        XposedHelpers.callMethod(objectSendviod,"eW",1);
//                        XposedHelpers.callMethod(objectSendviod,"ee","3116350620189e19d0dc0ca102");
//                        XposedHelpers.callMethod(objectSendviod,"eV",2);
//                        Object insert = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.c",lpparam.classLoader),"FQ");
//                        XposedHelpers.callMethod(insert,"Q",objectSendviod);

                        //构造语音 --未成功，只看到插入数据库的操作
//                        Class<?> classVoice = XposedHelpers.findClass("com.tencent.mm.modelvoice.f",lpparam.classLoader);
//                        Object instanceVoice= XposedHelpers.newInstance(classVoice,"0316340621189e19d0dc8e2103");
//                        Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au", lpparam.classLoader), "Dv");
//                        XposedHelpers.callMethod(sender, "a", instanceVoice, 0);
                    //发送语音  -- 未成功， 这个是对已有语音的删除操作

                    //XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"nu","5616040625189e19d0da3e0104");

                    //Object filename= XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"nR",mtalker);

                    //发送语音 ---未成功待续
//                 XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"W",filename,1000);
//                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.m", lpparam.classLoader), "TI");
//                    XposedHelpers.callMethod(sender, "run");


                    //发送位置 -- 失败 ，发送位置 report 不起什么作用
//                    Object reporter = XposedHelpers.getStaticObjectField(XposedHelpers.findClass("com.tencent.mm.plugin.report.service.h",lpparam.classLoader),"qrq");
//                    Object pramesobject[] = new Object[18];
//                    pramesobject[0] = 11 ;
//                    pramesobject[1]=1;
//                    pramesobject[2] =Long.valueOf("1529997811101");
//                    pramesobject[3] = Long.valueOf("1529997811405");
//                    pramesobject[4] =Long.valueOf(System.currentTimeMillis());
//                    pramesobject[5] = Long.valueOf("1529997811405");
//                    pramesobject[6]= 1;
//                    pramesobject[7]= "22.831051/108.369774";
//                    pramesobject[8] = null;
//                    pramesobject[9]= 1529997810;
//                    pramesobject[10]="qqmap_846d8d3ffdfbbd79f4cf180f4590b966";
//                    pramesobject[11]="862630039022208";
//                    pramesobject[12]=0;
//                    pramesobject[13]=0;
//                    pramesobject[14]=0;
//                    pramesobject[15]="";
//                    pramesobject[16]=0;
//                    pramesobject[17]="qqmap_218e6e60-790a-11e8-9fb0-6c92bf61fbe7";
//
//
//
//
//                    XposedHelpers.callMethod(reporter, "h",11135,pramesobject);

//                    launcherUiActivity.getWindow().addFlags();
                    //发送位置
                    /*
//                    Object location = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.g.a.lw",lpparam.classLoader));
//                    Object locationsub = XposedHelpers.getObjectField(location,"eFm");
//                    XposedHelpers.setIntField(locationsub,"eFo",0);
//                    XposedHelpers.setDoubleField(locationsub,"lat",22.831090927124023);
//                    XposedHelpers.setDoubleField(locationsub,"lng",108.3697738647461);
//                    XposedHelpers.setIntField(locationsub,"eBo",16);
//                    XposedHelpers.setObjectField(locationsub,"label","南宁青秀万达广场(青秀区东葛路118号)");
//                    XposedHelpers.setObjectField(locationsub,"eFp","[位置]");
*/
//                    Object sender = XposedHelpers.getStaticObjectField(XposedHelpers.findClass("com.tencent.mm.sdk.b.a",lpparam.classLoader),"xJM");
//                /*    XposedHelpers.callMethod(sender,"m",location); */
//                    Object locB= XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.g.a.or",lpparam.classLoader));
//                    Object locBsub = XposedHelpers.getObjectField(locB,"eIE");
//                    XposedHelpers.setObjectField(locBsub,"eIF","wxid_k2qvvyeecbhf21");
//                    XposedHelpers.setObjectField(locBsub,"content","<msg><location x=\"22.831090927124023\" y=\"108.3697738647461\" scale=\"16\" label=\"南宁青秀万达广场(青秀区东葛路118号)\" poiname=\"[位置]\" infourl=\"\" maptype=\"0\" /></msg>");
//                    XposedHelpers.setIntField(locBsub,"type",48);
//                    XposedHelpers.setIntField(locBsub,"flags",0);
//                    XposedHelpers.callMethod(sender,"m",locB);
//                /*    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelstat.o",lpparam.classLoader),"a",2004,(float)108.3697738647461,(float)22.831090927124023,0); */
        //发送名片
//                    //通过alias查找username
//                    Object obj = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.kernel.g",lpparam.classLoader),"l",XposedHelpers.findClass("com.tencent.mm.plugin.messenger.foundation.a.h",lpparam.classLoader));
//                    Object storage = XposedHelpers.callMethod(obj,"FO");
//                    Object person = XposedHelpers.callMethod(storage,"XZ","lin90814281");
//                    //通过username查找数据库看是否存在
//                    /*
//                    Object isexitperson = XposedHelpers.callMethod(storage,"Yc","lin90814281");
//                    if(XposedHelpers.getObjectField(isexitperson,"field_username") == "") {
//                        Log.d("person", "find username is lin90814281 is fail");
//                        Object isexitpersonsub = XposedHelpers.callMethod(storage,"Yc","a33085847");
//                        if (XposedHelpers.getObjectField(isexitpersonsub,"field_username") != ""){
//                            Log.d("person", "find username is a33085847 success"+ XposedHelpers.getObjectField(isexitpersonsub,"field_username"));
//                        }
//                    }else{
//                        Log.d("person", "username is "+ XposedHelpers.getObjectField(isexitperson,"field_username"));
//                    }
//                    */
//                    Log.d("person", "username is "+ XposedHelpers.getObjectField(person,"field_username"));
//                    //发送名片开始
//                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.plugin.messenger.a.g",lpparam.classLoader),"bcH");
//                    XposedHelpers.callMethod(sender,"l",(String)XposedHelpers.getObjectField(person,"field_username"),"wxid_k2qvvyeecbhf21",false);

        //发送语音---转发
//                    Object voiceConc = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"nY","2615110629189e19d0d2e22100");
//                    String newVoicefile =(String) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"e","wxid_k2qvvyeecbhf21","2615110629189e19d0d2e22100",(int)XposedHelpers.getIntField(voiceConc,"gZm"));
//                    Object voiceInstance = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.modelvoice.f",lpparam.classLoader),newVoicefile,0);
//                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au",lpparam.classLoader),"Dv");
//                    XposedHelpers.callMethod(sender,"a",voiceInstance,0);

        //发送语音---- 根据文件转发

//                    String newVoicefile = (String) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"nR","wxid_k2qvvyeecbhf21");
//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.j",lpparam.classLoader),"q","/storage/emulated/0/tencent/MicroMsg/578026bf6c6b3bc5b902c43f65f9d07b/voice2/c0/19/msg_2716480629189e19d0d0009105.amr",
//                            XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"getFullPath",newVoicefile),false);
//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.modelvoice.q",lpparam.classLoader),"m",newVoicefile,1024,1); //1024为时间，结束时间减去开始时间，单位毫秒
//                    Object voiceInstance = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.modelvoice.f",lpparam.classLoader),newVoicefile,0);
//                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au",lpparam.classLoader),"Dv");
//                    XposedHelpers.callMethod(sender,"a",voiceInstance,0);

        //建立群聊 -- ok
//                    userslists =  new ArrayList();
//                    userslists.add("wxid_vq7m1vvo8oo22");
//                    userslists.add("wxid_k2qvvyeecbhf21");
//                    userslists.add("a33085847");
//                    Object js = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.g.a.js",lpparam.classLoader));
//                    Object eCD = XposedHelpers.getObjectField(js,"eCD");
//                    XposedHelpers.setObjectField(eCD,"eCF","nihao");
//                    XposedHelpers.setObjectField(eCD,"eCG",userslists);
////                    XposedHelpers.setObjectField(eCD,"eCC",true);
//                    Object sender1 = XposedHelpers.getStaticObjectField(XposedHelpers.findClass("com.tencent.mm.sdk.b.a",lpparam.classLoader),"xJM");
//                    XposedHelpers.callMethod(sender1,"m",js);
//                    insertquanflag = true;
//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.ui.base.h",lpparam.classLoader),"a",launcherUiActivity.getBaseContext(),"ok",true,null);
//                    Log.d("yuzhao88","begin");
//                    Object eCD2 = XposedHelpers.getObjectField(js,"eCD");
//                    List list2 = (List) XposedHelpers.getObjectField(eCD2,"eCG");
//                    Log.d("yuzhao7","after sender 2"+ Arrays.asList(list2));
//                    Object eCE = XposedHelpers.getObjectField(js,"eCE");
//                    String eCI= (String)XposedHelpers.getObjectField(eCE,"eCI");
//                    Log.d("yuzhao7","after sender 3"+ eCI );

        //建立群聊 --- 失败
//                    ArrayList<String> list =  new ArrayList();
//                    list.add("wxid_vq7m1vvo8oo22");
//                    list.add("a33085847");
//                    list.add("wxid_k2qvvyeecbhf21");
//                    String title = "hello";
//                    Object chatrom = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.chatroom.d.f",lpparam.classLoader),title,list);
//                    XposedHelpers.callMethod(sender,"a",chatrom,0);
//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.l",lpparam.classLoader),"a","12819052530@chatroom",list,"你邀请%s加入了群聊",false,"");
        //群聊操作-- 删除成员 成功
//                    Object qunObject = XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.c",lpparam.classLoader),"FX"),"hQ","12655054690@chatroom");
//                    String myOwnusername = (String) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.q",lpparam.classLoader),"GC");
//                    String roomowner =(String) XposedHelpers.getObjectField(qunObject,"field_roomowner");
//                    if (myOwnusername.equals(roomowner)) {
//                        ArrayList<String> list = new ArrayList();
//                        list.add("wxid_k2qvvyeecbhf21");
//                        Object delmember = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.chatroom.d.g", lpparam.classLoader), "12655054690@chatroom", list);
//                        Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au", lpparam.classLoader), "Dv");
//                        XposedHelpers.callMethod(sender, "a", delmember, 0);
//                    }else {
//                        Log.d("yuzhao10","你不是该群的创建者，无法移除成员");
//                    }

        //群聊操作--邀请成员
//                    Object qunObject = XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.c",lpparam.classLoader),"FX"),"hQ","12655054690@chatroom");
//                    int qunqueren = (int)XposedHelpers.callMethod(qunObject,"cle");
//                    if(qunqueren != 2){
//                        Log.d("yuzhao10","没有开启群确认");
//                    }else {
//                        Log.d("yuzhao10","已开启群确认");
//                    }
        //wxid_k2qvvyeecbhf21
//                    ArrayList<String> list = new ArrayList();
//                    list.add("wxid_6uq54h678wbs22");
//                    Object addmember = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.chatroom.d.d", lpparam.classLoader), "12130046225@chatroom", list,"SB吗");
//                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au", lpparam.classLoader), "Dv");
//                    XposedHelpers.callMethod(sender, "a", addmember, 0);
//                    //12130046225@chatroom

//                    ArrayList<String> list = new ArrayList();
//                    list.add("wxid_6uq54h678wbs22");
//                    Object delmember = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.chatroom.d.k", lpparam.classLoader), "12655054690@chatroom", list);
//                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au", lpparam.classLoader), "Dv");
//                    XposedHelpers.callMethod(sender, "a", delmember, 0);
        /*

                    List<String> gv = (List<String>)XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.m",lpparam.classLoader),"gv","12130046225@chatroom");
                    Log.d("yuzhao10","群成员数量 " + gv.size() );





















































//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try{
//                                    Thread.sleep(2000);
//                                    ArrayList arrayList3 = new ArrayList();
//                                    arrayList3.add("/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-05-01-09-45-05-162_com.tencent.mm.png");
//                                    ArrayList arrayList4 = new ArrayList();
//                                    Object getSendPiclistAdm = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.am.n",lpparam.classLoader),"QQ");
//                                    arrayList4 = (ArrayList)XposedHelpers.callMethod(getSendPiclistAdm,"lw","wxid_k2qvvyeecbhf21");
//                                    Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.am.o", lpparam.classLoader), "OX");
//                                    XposedHelpers.callMethod(sender, "a", arrayList4,"wxid_vq7m1vvo8oo22" ,"wxid_k2qvvyeecbhf21",arrayList3,0,false,2130837989);
//                                }catch (InterruptedException e){
//                                    e.printStackTrace();
//                                }
//
//
//                            }
//                        }).start();


                    }










                super.beforeHookedMethod(param);

            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("yuzhao", "com.tencent.mm.bu.f.insert");

            }

        });
        */

//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "e", String.class,String.class,new XC_MethodHook() {
//
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        Object a1 = param.args[0];
//                        Object a2 = param.args[1];
//                        Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.e" + a1 + "------------------param2：" + a2);
//
//
//                        super.beforeHookedMethod(param);
//                    }
//
//                });
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "d", String.class,String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.d" + a1 + "------------------param2：" + a2);
//
//
//                super.beforeHookedMethod(param);
//            }
//
//        });
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "i",String.class, String.class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.i" + a1 + "------------------param2：" + a2);
//                super.beforeHookedMethod(param);
//            }
//
//        });
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "d", String.class,String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Object a3 = param.args[2];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.i" + a1 + "------------------param2：" + a2+"===============param3"+ a3);
//
//
//                super.beforeHookedMethod(param);
//            }
//
//        });
//
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "w", String.class,String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Object a3 = param.args[2];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.i" + a1 + "------------------param2：" + a2+"===============param3"+ a3);
//
//
//                super.beforeHookedMethod(param);
//            }
//
//        });
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "i",String.class, String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.i" + a1 + "------------------param2：" + a2);
//                super.beforeHookedMethod(param);
//            }
//
//        });
//
//
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.ac.o", lpparam.classLoader, "a",XposedHelpers.findClass("com.tencent.mm.ac.l",lpparam.classLoader), int.class,new XC_MethodHook() {
//
//        @Override
//        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//            Object a1 = param.args[0];
//            Object a2 = param.args[1];
//            Log.d("yuzhao", "com.tencent.mm.ac.o.a" + a1 + "------------------param2：" + a2);
//
//
//            super.beforeHookedMethod(param);
//        }
//
//    });


//        final Class<?> myclass = XposedHelpers.findClass("com.tencent.mm.storage.az", lpparam.classLoader);
//
//        final Method[] ms = myclass.getDeclaredMethods();
//        Log.d("mylog", "count " + ms.length + "");
//        for (int i = 0; i < ms.length; i++) {
//            Log.d("mylog", "hook name:" + ms[i].getName());
//            try {
//                final int finalI = i;
//                Log.d("mylog", "hook name:" + ms[i].getName());
//                XposedBridge.hookMethod(ms[i], new XC_MethodHook() {
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                        String mename = ms[finalI].getName();
//                        Log.d("mylog", "name:" + mename);
//                        if (param.args != null) {
//                            for (int i = 0; i < param.args.length; i++) {
//
//                                Log.d("mylog", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");
//
//
//
//                            }
//                        }
//
//
//                        if (param.getResult() != null) {
//                            if(ms[finalI].getName().equals("RL")){
//
//
//                            }
//                            Log.d("mylog", "return :" + param.getResult().toString());
//
//                        }
//
//
//                    }
//                });
//            } catch (Exception err) {
//
//                log("error：" + err.getMessage());
//            }
//        }


//        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.gallery.model.l", lpparam.classLoader, "aRv",new XC_MethodHook() {
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                if (param.getResult() != null) {
//                    Log.d("mylog", "com.tencent.mm.plugin.gallery.model.l.aRv return :" + param.getResult().toString());
//
//                }
//            }
//
//
//        });
//
//        final Class<?> myclass1 = XposedHelpers.findClass("com.tencent.mm.am.i", lpparam.classLoader);
//
//        final Method[] ms1 = myclass1.getDeclaredMethods();
//        Log.d("my1log", "count " + ms1.length + "");
//        for (int i = 0; i < ms1.length; i++) {
//            Log.d("my1log", "hook name:" + ms1[i].getName());
//            try {
//                final int finalI = i;
//                Log.d("my1log", "hook name:" + ms1[i].getName());
//                XposedBridge.hookMethod(ms1[i], new XC_MethodHook() {
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                        String mename = ms1[finalI].getName();
//                        Log.d("my1log", "name:" + mename);
//                        if (param.args != null) {
//                            for (int i = 0; i < param.args.length; i++) {
//
//                                Log.d("my1log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");
//
//
//
//                            }
//                        }
//
//
//                        if (param.getResult() != null) {
//                            if(ms1[finalI].getName().equals("RL")){
//
//
//                            }
//                            Log.d("my1log", "return :" + param.getResult().toString());
//
//                        }
//
//
//                    }
//                });
//            } catch (Exception err) {
//
//                log("error：" + err.getMessage());
//            }
//        }
        /*
        XposedHelpers.findAndHookConstructor("com.tencent.mm.plugin.gallery.model.GalleryItem$MediaItem",lpparam.classLoader,long.class,String.class,String.class,String.class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                Object a2 = param.args[1];
                Object a3 = param.args[2];
                Object a4 = param.args[3];
                Log.d("yuzhao", "com.tencent.mm.plugin.gallery.model.GalleryItem$MediaItem" + a1 + "------------------param2：" + a2+"===============param3"+ a3+"===============param4"+a4);
            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.ChattingUI$a", lpparam.classLoader, "onActivityResult",int.class,int.class,Intent.class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                Object a2 = param.args[1];
                Object a3 = param.args[2];
                Log.d("ChattingUI", "com.tencent.mm.ui.chatting.ChattingUI$a" + a1 + "------------------param2：" + a2+"===============param3"+ a3);


                if (param.getResult() != null) {
                    Log.d("z`", "com.tencent.mm.ui.chatting.ChattingUI$a return :" + param.getResult().toString());

                }
            }



        });

        final Class<?> myclass2 = XposedHelpers.findClass("com.tencent.mm.ac.o", lpparam.classLoader);

        final Method[] ms2 = myclass2.getDeclaredMethods();
        Log.d("my1log", "count " + ms2.length + "");
        for (int i = 0; i < ms2.length; i++) {
            Log.d("my1log", "hook name:" + ms2[i].getName());
            try {
                final int finalI = i;
                Log.d("my2log", "hook name:" + ms2[i].getName());
                XposedBridge.hookMethod(ms2[i], new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        String mename = ms2[finalI].getName();
                        Log.d("my2log", "name:" + mename);
                        if (param.args != null) {
                            for (int i = 0; i < param.args.length; i++) {

                                Log.d("my2log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");



                            }
                        }


                        if (param.getResult() != null) {
                            if(ms2[finalI].getName().equals("RL")){


                            }
                            Log.d("my2log", "return :" + param.getResult().toString());

                        }


                    }
                });
            } catch (Exception err) {

                log("error：" + err.getMessage());
            }
        }



        XposedHelpers.findAndHookConstructor("com.tencent.mm.am.l",
                lpparam.classLoader, String.class,String.class ,String.class,int.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("ss1",param.args[0]+"-"+param.args[1] + "-"+param.args[2]+"-"+param.args[3]);

                    }
                });



        final Class<?> myclass3 = XposedHelpers.findClass("com.tencent.mm.pluginsdk.model.j", lpparam.classLoader);

        final Constructor[] ms3 = myclass3.getDeclaredConstructors();
        Log.d("my3log", "count " + ms3.length + "");
        for (int i = 0; i < ms3.length; i++) {
            Log.d("my3log", "hook name:" + ms3[i].getName());
            try {
                final int finalI = i;
                Log.d("my3log", "hook name:" + ms3[i].getName());
                XposedBridge.hookMethod(ms3[i], new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        String mename = ms3[finalI].getName();
                        Log.d("my3log", "name:" + mename);
                        if (param.args != null) {
                            for (int i = 0; i < param.args.length; i++) {

                                Log.d("my3log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");



                            }
                        }


                        if (param.getResult() != null) {
                            if(ms2[finalI].getName().equals("RL")){


                            }
                            Log.d("my3log", "return :" + param.getResult().toString());

                        }


                    }
                });
            } catch (Exception err) {

                log("error：" + err.getMessage());
            }
        }
        XposedBridge.hookAllConstructors(myclass3, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                if (param.args != null) {
                    for (int i = 0; i < param.args.length; i++) {

                        Log.d("my3log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");



                    }
                }
            }
        });

        final Class<?> myclass4 = XposedHelpers.findClass("com.tencent.mm.ui.chatting.ChattingUI.a", lpparam.classLoader);

        final Method[] ms4 = myclass4.getDeclaredMethods();
        Log.d("my4log", "count " + ms4.length + "");
        for (int i = 0; i < ms4.length; i++) {
            Log.d("my4log", "hook name:" + ms4[i].getName());
            try {
                final int finalI = i;
                Log.d("my4log", "hook name:" + ms[i].getName());
                XposedBridge.hookMethod(ms4[i], new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        String mename = ms4[finalI].getName();
                        Log.d("my4log", "name:" + mename);
                        if (param.args != null) {
                            for (int i = 0; i < param.args.length; i++) {

                                Log.d("my4log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");



                            }
                        }


                        if (param.getResult() != null) {
                            if(ms4[finalI].getName().equals("RL")){


                            }
                            Log.d("my4log", "return :" + param.getResult().toString());

                        }


                    }
                });
            } catch (Exception err) {

                log("error：" + err.getMessage());
            }
        }




//        XposedHelpers.findAndHookConstructor("com.tencent.mm.am.l",
//                lpparam.classLoader, int.class, String.class, String.class, String.class, int.class, XposedHelpers.findClass(), String.class, String.class,new XC_MethodHook() {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        Log.d("ss2",param.args[0]+"-"+param.args[1] + "-"+param.args[2]+"-"+param.args[3]);
//
//                    }
//                });
        XposedHelpers.findAndHookConstructor("com.tencent.mm.modelvoice.f", lpparam.classLoader, String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("mylog4",param.args[0]+"");
            }
        });
        XposedHelpers.findAndHookConstructor("com.tencent.mm.modelvoice.f", lpparam.classLoader, String.class,int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("mylog4",param.args[0]+"-"+param.args[1]);
            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.location.ui.impl.d", lpparam.classLoader, "d",Activity.class,int.class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                Object a2 = param.args[1];
                Log.d("locationUI", "com.tencent.mm.plugin.location.ui.impl.d" + a1 + "------------------param2：" + a2);


                if (param.getResult() != null) {
                    Log.d("location", "com.tencent.mm.plugin.location.ui.impl.d return :" + param.getResult().toString());

                }
            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.report.service.h", lpparam.classLoader, "h",int.class,Object[].class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                Object[] a2 =(Object[]) param.args[1];
                for (Object a: a2) {
                    Log.d("reportservice", "com.tencent.mm.plugin.report.service.h Object[] ======" + a );

                }
                Log.d("reportservice", "com.tencent.mm.plugin.report.service.h" + a1 + "------------------param2：" + a2);


                if (param.getResult() != null) {
                    Log.d("reportservice", "com.tencent.mm.plugin.report.service.h return :" + param.getResult().toString());

                }
            }
        });
        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.location.ui.RedirectUI", lpparam.classLoader, "onActivityResult",int.class,int.class,Intent.class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                Object a2 =param.args[1];

                Log.d("locationintent", "com.tencent.mm.plugin.location.ui.RedirectUI" + a1 + "------------------param2：" + a2);


                if (param.getResult() != null) {
                    Log.d("locationintent", "com.tencent.mm.plugin.location.ui.RedirectUI return :" + param.getResult().toString());

                }
            }
        });

        XposedHelpers.findAndHookConstructor("com.tencent.mm.modelmulti.i", lpparam.classLoader, String.class, String.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Object a1 = param.args[0];
                Object a2 =param.args[1];
                Object a3 =param.args[2];

                Log.d("message", "com.tencent.mm.modelmulti.i" + a1 + "------------------param2：" + a2+"------------------param3："+a3);
            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.transmit.d", lpparam.classLoader, "l",String.class,String.class,boolean.class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                Object a2 =param.args[1];
                Object a3 =param.args[2];

                Log.d("message", "com.tencent.mm.ui.transmit.d" + a1 + "------------------param2：" + a2+"------------------param3："+a3);


                if (param.getResult() != null) {
                    Log.d("message", "com.tencent.mm.ui.transmit.d return :" + param.getResult().toString());

                }
            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.b.a", lpparam.classLoader, "m", XposedHelpers.findClass("com.tencent.mm.sdk.b.b",lpparam.classLoader), new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object a1 = param.args[0];
                Object eCD = XposedHelpers.getObjectField(a1,"eCD");
                List list = (List) XposedHelpers.getObjectField(eCD,"eCG");
                Log.d("yuzhao7","list"+ Arrays.asList(list));

            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.contact.SelectContactUI", lpparam.classLoader, "dD", List.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object a1 = param.args[0];
                Log.d("yuzhao7"," dD  list"+ Arrays.asList(a1));

            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.contact.SelectContactUI$6", lpparam.classLoader, "a",int.class,int.class,String.class, XposedHelpers.findClass("com.tencent.mm.sdk.b.b",lpparam.classLoader), new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object a1 = param.args[0];
                Object a2 = param.args[1];
                Object a3 = param.args[2];
                Log.d("yuzhao7", "com.tencent.mm.ui.contact.SelectContactUI$6" + a1 + "------------------param2：" + a2+"------------------param3："+a3);
                Object a4 = param.args[3];
                Object eCD = XposedHelpers.getObjectField(a4,"eCD");
                List list = (List) XposedHelpers.getObjectField(eCD,"eCG");
                Log.d("yuzhao7","I$6 list eCG"+ Arrays.asList(list));
                Object eCE = XposedHelpers.getObjectField(a4,"eCE");
                String eCI= (String)XposedHelpers.getObjectField(eCE,"eCI");
                Log.d("yuzhao7","I$6 list eCI"+ eCI );



            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.contact.SelectContactUI$6", lpparam.classLoader, "a",int.class,int.class,String.class, XposedHelpers.findClass("com.tencent.mm.sdk.b.b",lpparam.classLoader), new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object a1 = param.args[0];
                Object a2 = param.args[1];
                Object a3 = param.args[2];
                Log.d("yuzhao7", "com.tencent.mm.ui.contact.SelectContactUI$6" + a1 + "------------------param2：" + a2+"------------------param3："+a3);
                Object a4 = param.args[3];
                Object eCD = XposedHelpers.getObjectField(a4,"eCD");
                List list = (List) XposedHelpers.getObjectField(eCD,"eCG");
                Log.d("yuzhao7","I$6 list eCG"+ Arrays.asList(list));
                Object eCE = XposedHelpers.getObjectField(a4,"eCE");
                String eCI= (String)XposedHelpers.getObjectField(eCE,"eCI");
                Log.d("yuzhao7","I$6 list eCI"+ eCI );



            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.b.a", lpparam.classLoader, "a", LinkedList.class, XposedHelpers.findClass("com.tencent.mm.sdk.b.b",lpparam.classLoader), new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object a1 = param.args[0];
                Log.d("yuzhao7", "com.tencent.mm.sdk.b.a 1" +  Arrays.asList(a1));
                Object a4 = param.args[1];
                Object eCD = XposedHelpers.getObjectField(a4,"eCD");
                List list = (List) XposedHelpers.getObjectField(eCD,"eCG");
                Log.d("yuzhao7","com.tencent.mm.sdk.b.a 2"+ Arrays.asList(list));
                Object eCE = XposedHelpers.getObjectField(a4,"eCE");
                String eCI= (String)XposedHelpers.getObjectField(eCE,"eCI");
                Log.d("yuzhao7","com.tencent.mm.sdk.b.a 3"+ eCI );



            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.z.l", lpparam.classLoader, "a",String.class,List.class,String.class,boolean.class,String.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                Object a2 =param.args[1];
                Object a3 =param.args[2];
                Object a4 =param.args[3];
                Object a5 =param.args[4];


                Log.d("weixinquan", "com.tencent.mm.z.l" + a1 + "------------------param2：" + a2+"------------------param3："+a3+"------------------param4："+a4+"------------------param5："+a5);


            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.ChattingUI.a", lpparam.classLoader, "setMMTitle",String.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                a1  = "sbs"+ a1 ;




            }
        });


        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.b", lpparam.classLoader, "setTitle",CharSequence.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                a1  = "sbsbs"+ a1 ;




            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.card.ui.CardDetailBaseUI", lpparam.classLoader, "setMMTitle",String.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                a1  = "sbsbsb"+ a1 ;




            }
        });
        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.MMActivity", lpparam.classLoader, "setMMTitle",String.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];

                a1  = "yuaresbsbsb" + a1;





            }
        });
        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.chatroom.ui.ChatroomInfoUI", lpparam.classLoader, "onCreate",Bundle.class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("yuzhao10","ChatroomInfoUI onCreated");
                final Object oo = param.thisObject;

                Log.d("yuzhao10","ChatroomInfoUI username "+ XposedHelpers.getObjectField(XposedHelpers.getObjectField(param.thisObject,"jAt"),"field_username"));

                AlertDialog dialog = new AlertDialog.Builder((Activity)oo)
                        .setTitle("roomid:")//设置对话框的标题
                        .setMessage((String)XposedHelpers.getObjectField(XposedHelpers.getObjectField(param.thisObject,"jAt"),"field_username"))//设置对话框的内容
                        //设置对话框的按钮
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();

            }
        });



        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.chatroom.ui.ChatroomInfoUI", lpparam.classLoader, "onActivityResult",int.class,int.class,Intent.class,new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("yuzhao10","ChatroomInfoUI onActivityResult");
                Object a1 = param.args[0];
                Object a2 =param.args[1];
                Object a3 =param.args[2];


                Log.d("yuzhao10", "com.tencent.mm.plugin.chatroom.ui.ChatroomInfoUI onActivityResult " + a1 + "------------------param2：" + a2+"------------------param3："+a3);



            }
        });
        */

//        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.transmit.SendAppMessageWrapperUI", lpparam.classLoader, "a",XposedHelpers.findClass("com.tencent.mm.ui.transmit.SendAppMessageWrapperUI",lpparam.classLoader),XposedHelpers.findClass("com.tencent.mm.opensdk.modelmsg.WXMediaMessage",lpparam.classLoader),String.class,int.class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Log.d("yuzhao11","SendAppMessageWrapperUI ");
//                Object a1 = param.args[0];
//                Object a2 =param.args[1];
//                Object a3 =param.args[2];
//                Object a4 =param.args[3];
//
//
//                Log.d("yuzhao11", "com.tencent.mm.ui.transmit.SendAppMessageWrapperUI param1 " + a1 + "------------------param2：" + a2+"------------------param3："+a3 +"------------------param4："+a4);
//
//
//
//            }
//        });
//
//        final Class<?> myclass = XposedHelpers.findClass("com.tencent.mm.ui.transmit.SendAppMessageWrapperUI", lpparam.classLoader);
//
//        final Method[] ms = myclass.getDeclaredMethods();
//        Log.d("mylog", "count " + ms.length + "");
//        for (int i = 0; i < ms.length; i++) {
//            Log.d("mylog", "hook name:" + ms[i].getName());
//            try {
//                final int finalI = i;
//                Log.d("mylog", "hook name:" + ms[i].getName());
//                XposedBridge.hookMethod(ms[i], new XC_MethodHook() {
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                        String mename = ms[finalI].getName();
//                        Log.d("mylog", "name:" + mename);
//                        if (param.args != null) {
//                            for (int i = 0; i < param.args.length; i++) {
//
//                                Log.d("mylog", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");
//
//
//
//                            }
//                        }
//
//
//                        if (param.getResult() != null) {
//                            if(ms[finalI].getName().equals("RL")){
//
//
//                            }
//                            Log.d("mylog", "return :" + param.getResult().toString());
//
//                        }
//
//
//                    }
//                });
//            } catch (Exception err) {
//
//                log("error：" + err.getMessage());
//            }
//        }
//
//        final Class<?> myclass2 = XposedHelpers.findClass("com.tencent.mm.ac.o", lpparam.classLoader);
//
//        final Method[] ms2 = myclass2.getDeclaredMethods();
//        Log.d("my1log", "count " + ms2.length + "");
//        for (int i = 0; i < ms2.length; i++) {
//            Log.d("my1log", "hook name:" + ms2[i].getName());
//            try {
//                final int finalI = i;
//                Log.d("my2log", "hook name:" + ms2[i].getName());
//                XposedBridge.hookMethod(ms2[i], new XC_MethodHook() {
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                        String mename = ms2[finalI].getName();
//                        Log.d("my2log", "name:" + mename);
//                        if (param.args != null) {
//                            for (int i = 0; i < param.args.length; i++) {
//
//                                Log.d("my2log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");
//
//
//
//                            }
//                        }
//
//
//                        if (param.getResult() != null) {
//                            if(ms2[finalI].getName().equals("RL")){
//
//
//                            }
//                            Log.d("my2log", "return :" + param.getResult().toString());
//
//                        }
//
//
//                    }
//                });
//            } catch (Exception err) {
//
//                log("error：" + err.getMessage());
//            }
//        }
//
//
//        final Class<?> myclass3 = XposedHelpers.findClass("com.tencent.mm.opensdk.modelmsg.WXMediaMessage", lpparam.classLoader);
//
//        final Method[] ms3 = myclass3.getDeclaredMethods();
//        Log.d("my3log", "count " + ms3.length + "");
//        for (int i = 0; i < ms3.length; i++) {
//            Log.d("my3log", "hook name:" + ms3[i].getName());
//            try {
//                final int finalI = i;
//                Log.d("my3log", "hook name:" + ms3[i].getName());
//                XposedBridge.hookMethod(ms3[i], new XC_MethodHook() {
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                        String mename = ms3[finalI].getName();
//                        Log.d("my3log", "name:" + mename);
//                        if (param.args != null) {
//                            for (int i = 0; i < param.args.length; i++) {
//
//                                Log.d("my3log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");
//
//
//
//                            }
//                        }
//
//
//                        if (param.getResult() != null) {
//                            if(ms3[finalI].getName().equals("RL")){
//
//
//                            }
//                            Log.d("my3log", "return :" + param.getResult().toString());
//
//                        }
//
//
//                    }
//                });
//            } catch (Exception err) {
//
//                log("error：" + err.getMessage());
//            }
//        }
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "d", String.class,String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Object a3 = param.args[2];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.d" + a1 + "------------------param2：" + a2+ "------------------param3：" + a3.toString());
//
//
//                super.beforeHookedMethod(param);
//            }
//
//        });
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "i",String.class, String.class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.i" + a1 + "------------------param2：" + a2);
//                super.beforeHookedMethod(param);
//            }
//
//        });
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "d", String.class,String.class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.d" + a1 + "------------------param2：" + a2);
//
//
//                super.beforeHookedMethod(param);
//            }
//
//        });
//
//
//

        /*


        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.fav.ui.h", lpparam.classLoader, "a",Context.class,String.class,String.class,XposedHelpers.findClass("com.tencent.mm.plugin.fav.a.g",lpparam.classLoader),Runnable.class,new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Object a1 = param.args[0];
                Object a2 = param.args[1];
                Object a3 = param.args[2];
                Object a4 = param.args[3];
                Object a5 = param.args[4];
                Log.d("ChattingUI", "com.tencent.mm.plugin.fav.ui.h param1" + a1 + "------------------param2：" + a2+"===============param3"+ a3
                +"------------------param4：" + a4+ "------------------param5：" + a5);

                int field_type = XposedHelpers.getIntField(a4,"field_type");
                int field_id = XposedHelpers.getIntField(a4,"field_id");

                Log.d("ChattingUI", "field_type param1" + field_type + " field_id param2" + field_id );




//                Intent a4 = (Intent) a3;

//                Log.d("ChattingUI", " selected_file_lst" + a4.getStringArrayListExtra("selected_file_lst").toString()
//                + "------------------key_select_video_list：" + a4.getStringArrayListExtra("key_select_video_list").toString()
//                        +"------------------CropImage_OutputPath_List"+ a4.getStringArrayListExtra("CropImage_OutputPath_List").toString()
//                +"------------------GalleryUI_ToUser："+ a4.getStringExtra("GalleryUI_ToUser")+
//                        "------------------with_text_content：" + a4.getStringExtra("with_text_content"));




                if (param.getResult() != null) {
                    Log.d("ChattingUI", "com.tencent.mm.plugin.fav.ui.h  return :" + param.getResult().toString());

                }
            }



        });
        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                log("com.tencent.mm.ui.LauncherUI onCreated" + "\n");
                launcherUiActivity = (Activity) param.thisObject;
            }
        });




        */
        /*

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                log("com.tencent.mm.ui.LauncherUI onCreated" + "\n");
                //launcherUiActivity = (Activity) param.thisObject;

                Object db = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.kernel.g",lpparam.classLoader),"DY");
                Object fVr = XposedHelpers.getObjectField(db,"fVr");
                log("fVr :"+ fVr);
//                Integer begin = (Integer)XposedHelpers.callMethod(fVr,"get",89,null);
//                log("begin :"+ begin);
//                int f = (int)XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.bh",lpparam.classLoader),"f",(Integer)XposedHelpers.callMethod(fVr,"get",89,null));
//                log("f :"+ f);
//                String a1 = (String)XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.bh",lpparam.classLoader),"oA",(String)XposedHelpers.callMethod(fVr,"get",8195,null));
//                log("a1 :"+ a1);
//                int a2 =(int) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.bh",lpparam.classLoader),"f",(Integer)XposedHelpers.callMethod(fVr,"get",15,null));
//                log("a2 :"+ a2);



//            }
//        });
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "i",String.class, String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Object[] a3 =(Object[]) param.args[2];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.i a1:" + a1 + "------------------param2：" + a2 + "param3:" + Arrays.asList(a3));
//                super.beforeHookedMethod(param);
//            }
//
//        });
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "d",String.class, String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Object[] a3 =(Object[]) param.args[2];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.d a1:" + a1 + "------------------param2：" + a2 + "param3:" + Arrays.asList(a3));
//                super.beforeHookedMethod(param);
//            }
//
//        });
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.w", lpparam.classLoader, "w",String.class, String.class,Object[].class,new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object a1 = param.args[0];
//                Object a2 = param.args[1];
//                Object[] a3 =(Object[]) param.args[2];
//                Log.d("yuzhao", "com.tencent.mm.sdk.platformtools.w.w a1:" + a1 + "------------------param2：" + a2 + "param3:" + Arrays.asList(a3));
//                super.beforeHookedMethod(param);
//            }
//
//        });
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.base.r", lpparam.classLoader, "dismiss",new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Log.d("yuzhao", "MicroMsg.MMProgressDialog dissmiss");
//                super.beforeHookedMethod(param);
//            }
//
//        });


//        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onResume",  new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                log("com.tencent.mm.ui.LauncherUI onResume" + "\n");
//                //launcherUiActivity = (Activity) param.thisObject;
//                Object db = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.kernel.g",lpparam.classLoader),"DY");
//                Object fVr = XposedHelpers.getObjectField(db,"fVr");
//                log(" onResume  fVr :"+ fVr);
//
//
//
//
//            }
//        });


//        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onResume",  new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                log("com.tencent.mm.ui.LauncherUI onResume" + "\n");
//                //launcherUiActivity = (Activity) param.thisObject;
//                Object db = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.kernel.g",lpparam.classLoader),"DY");
//                Object fVr = XposedHelpers.getObjectField(db,"fVr");
//                log(" onResume  fVr :"+ fVr);
//
//
//
//
//            }
//        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.sns.ui.ah", lpparam.classLoader, "a", int.class, int.class, XposedHelpers.findClass("org.b.d.i",lpparam.classLoader), String.class, List.class,
                XposedHelpers.findClass("com.tencent.mm.protocal.c.apt",lpparam.classLoader), int.class, boolean.class, List.class, XposedHelpers.findClass("com.tencent.mm.pointers.PInt",lpparam.classLoader), String.class, int.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                for(int i = 0;i<param.args.length;i++){
                    Log.d("yuzhao","haha" + param.args[i]+"\n");

                }



                Object a1 = param.args[0];
                Object a2 = param.args[1];
                Object a3 = param.args[2];
                Object a4 = param.args[3];
                Object a5 = param.args[4];
                Log.d("yuzhao",""+a1+a2+a3+a4+Arrays.asList(a5));

                Object a6 = param.args[5];
                Object a7 = param.args[6];
                Object a8 = param.args[7];
                Object a9 = param.args[8];
                Object a10 = param.args[9];
                Log.d("yuzhao","222"+a6+a7+a8+Arrays.asList(a9)+a10);





            }
        });



//        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.sns.ui.SnsUploadUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        Activity a1 = (Activity) param.thisObject;
//                        Object o2 = a1.getIntent().getStringExtra("act");
//                        Object o3 = a1.getIntent().getStringExtra("images");
//                        Log.d("yuzhao","444" +o2+o3);
//
//
//
//
//
//
//
//
//
//
//                    }
//                });
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.viewitems.b$c$a", lpparam.classLoader, "onMMMenuItemSelected", MenuItem.class, int.class,new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                MenuItem menuItem = (MenuItem) param.args[0];
//                XposedBridge.log("MenuItem :" + menuItem.getItemId());
//
//
//
//
//
//
//
//
//
//
//
//            }
//        });




//        Class mSQLiteDatabase = XposedHelpers.findClass("com.tencent.wcdb.database.SQLiteDatabase",lpparam.classLoader);
//        XposedBridge.log(""+"-->"+"Class mSQLiteDatabase == " + mSQLiteDatabase);
//
//        Class mSQLiteDatabaseConfiguration = XposedHelpers.findClass("com.tencent.wcdb.database.SQLiteDatabaseConfiguration",                    lpparam.classLoader            );
//        XposedBridge.log(""+"-->"+"Class mSQLiteDatabaseConfiguration == " + mSQLiteDatabaseConfiguration);
//
//        Class mSQLiteCipherSpec = XposedHelpers.findClass(
//                "com.tencent.wcdb.database.SQLiteCipherSpec",
//                lpparam.classLoader
//        );
//        XposedBridge.log(""+"-->"+"Class mSQLiteCipherSpec == " + mSQLiteCipherSpec);
//
//        XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteConnectionPool",
//                lpparam.classLoader,
//                "open",
//                mSQLiteDatabase,
//                mSQLiteDatabaseConfiguration,
//                byte[].class,
//                mSQLiteCipherSpec,
//                int.class,
//                new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        super.beforeHookedMethod(param);
//                        if(param.args[0]==null) return;
//                        if(param.args[2]==null) return;
//
//                        byte[] array = (byte[]) param.args[2];
//
//                        //数据库
//                        String dataBase= param.args[0].toString();
//
//                        //密码
//                        String pass=""+(new String(array));
//                        Log.d("XposedTag","array => " + pass);
//
//
//
//                        //复制数据库
////                        File file=new File(dataBase.replace("SQLiteDatabase:",""));
////                        XposedBridge.log("file:"+file.getPath());
//
//
//
//                        //FileCopy(file,desfile);
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//
//                    }
//                });

//                HookDataBase(lpparam);
//                //判断是否第一次登录
//                try {
//                    if(getMyOwnUsername(lpparam) != null ) {
//                        firstloginflag = false;
//                    }else {
//                        firstloginflag = true;
//                    }
//                } catch ( Throwable e) {
//                    log("firstloginflag true");
//                    firstloginflag = true;
//                }

//                Object homeui = XposedHelpers.getObjectField(param.thisObject,"ynB");
//                Object systemconfig = XposedHelpers.getObjectField(homeui,"ymn");
//                SharedPreferences sharedPreferences = (SharedPreferences)XposedHelpers.callMethod(systemconfig,"getSharedPreferences","system_config_prefs", 4);
//                if (sharedPreferences.getBoolean("first_launch_weixin", true)) {
//                    firstloginflag =true;
//
//                }else {
//                    log("firstloginflag false");
//                    firstloginflag = false;
//                }
//                //判断是否第一次登录
//                if(wxdatehook.isDatabaseExit()){
//                    firstloginflag = false;
//                }else {
//                    log("firstloginflag true");
//                    firstloginflag = true;
//                }


//        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.tencent.mm.ui.base.r",lpparam.classLoader), "onCreate",Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("  1 " + "\n");
//                Log.i("Dump Stack: ", "---------------start----------------");
//
//                Throwable ex = new Throwable();
//
//                StackTraceElement[] stackElements = ex.getStackTrace();
//
//                if (stackElements != null) {
//
//                    for (int i = 0; i < stackElements.length; i++) {
//
//
//
//                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//
//                                +"----"+stackElements[i].getFileName()
//
//                                +"----" + stackElements[i].getLineNumber()
//
//                                +"----" +stackElements[i].getMethodName());
//
//                    }
//
//                }
//
//                Log.i("Dump Stack: ", "---------------over----------------");
//            }
//
//        });
//        */
//
//        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.sns.ui.SnsUploadUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                Activity a1 = (Activity) param.thisObject;
//                Object o2 = a1.getIntent().getStringExtra("Ksnsforward");
//                Object o3 = a1.getIntent().getStringExtra("Ksnsupload_type");
//                Object o4 = a1.getIntent().getStringExtra("Kdescription");
//                Object o5 = a1.getIntent().getStringExtra("Ksnsupload_title");
//                Object o6 = a1.getIntent().getStringExtra("Ksnsupload_link");
//                Object o7 = a1.getIntent().getStringExtra("Ksnsupload_imgbuf");
//                Object o8 = a1.getIntent().getStringExtra("sns_kemdia_path_list");
//                Object o9 = a1.getIntent().getStringExtra("KSightPath");
//                Object o10 = a1.getIntent().getStringExtra("KSightThumbPath");
//                Object o11 = a1.getIntent().getStringExtra("sight_md5");
//                Object o12 = a1.getIntent().getStringExtra("sns_kemdia_path_list");
//                Log.d("yuzhao","1111" +o2+o3+o4+o5+o6);
//                Log.d("yuzhao","2222" +o7+o8+o9+o10+o11+o12);
//
//            }
//        });

//        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                log("com.tencent.mm.ui.LauncherUI onCreated" + "\n");
//                launcherUiActivity = (Activity) param.thisObject;
//            }
//        });


        //hooklauchui(lpparam);
        //HookSendObject(lpparam);
        //HookWeiXinLog(lpparam);
        //hookDatebasePassword(lpparam);
        //hookfriendshared(lpparam);
        // hookqfrelate(lpparam);
        //hooksendmoments(lpparam);
        //hook673chatroom(lpparam);
        //hookaddressmodel(lpparam);
        //HooKMap(lpparam);
        //hooklocation(lpparam);
        //hooklocationtest(lpparam);
        //hookcheckifhook(lpparam);
        hookDatebaseMonitor(lpparam);
        //hooknearfriends(lpparam);
        //hookphonecontact(lpparam);
        hookyaoyiyao(lpparam);
        /*

        Class<?> dbClazz1 = XposedHelpers.findClass("com.tencent.mm.cf.f", lpparam.classLoader);
//
        XposedHelpers.findAndHookMethod(dbClazz1, "update", String.class, ContentValues.class, String.class, String[].class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {


                //printTableInfo1(param);
            }
        });


        XposedHelpers.findAndHookMethod(dbClazz1, "insert", String.class, String.class, ContentValues.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {


                //printTableInfo(param);


//                String tableName = (String) param.args[0];
//
//
//                //接受消息，isSend = 0;
//                ContentValues val = (ContentValues) param.args[2];
//                mtype = val.getAsString("type");
//                misSend = val.getAsString("isSend");
//                mcontent = val.getAsString("content");
//                mtalker = val.getAsString("talker");
//                //Log.d("yuzhao88", tableName + insertquanflag + mtype);
//
//
//                mtalker = "medianote"; //语音记事本
//                Log.d("yuzhao", "type:" + mtype + "isSend：" + misSend + "content" + mcontent);
//
//
//                if (mtype.equals("1") && misSend.equals("0") && mcontent.equals("12")) {


                   //Object a = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.roomsdk.a.b",lpparam.classLoader),"YK","@chatroom");
//                   ArrayList<String> list =  new ArrayList();
//                   list.add("wxid_6uq54h678wbs22");
//                   list.add("a33085847");
//                   list.add("wxid_k2qvvyeecbhf21");
                   //Object b =XposedHelpers.callMethod(a,"b","", list);
                   //XposedHelpers.callMethod(b,"a",launcherUiActivity,"正在建立群聊",true,null);
//                   XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.model.l", lpparam.classLoader), "a", "5011622108@chatroom", list, "你邀请%s加入了群聊", false, "");


//                    Object a = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.roomsdk.a.b",lpparam.classLoader),"YK","5011622108@chatroom");
//                    Object b = XposedHelpers.callMethod(a,"c","5011622108@chatroom",list);
//                    XposedHelpers.callMethod(b,"a",launcherUiActivity,"正在邀请",true,null);


//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.model.l", lpparam.classLoader), "a", "5011622108@chatroom", list, "你邀请%s加入了群聊", false, "");


                    // batchsend();
//                    Intent intent = new Intent();
//                    intent.setClass(launcherUiActivity, XposedHelpers.findClass("com.tencent.mm.plugin.sns.ui.SnsUploadUI", lpparam.classLoader));
////                    ArrayList<String> arrayList = new ArrayList<String>();
////                    arrayList.add("/storage/emulated/0/DCIM/1528426443248.jpg");
////                    intent.putStringArrayListExtra("sns_kemdia_path_list", arrayList);
//
//
////                    //需要先设置Ktag_range_index =2
////                    int index = 3;
////                    intent.putExtra("Ktag_range_index",index);
////
////
//////                    //谁可以看
//////                    ArrayList<String> arrayList1 = new ArrayList<String>();
//////                    arrayList1.add("wxid_k2qvvyeecbhf21");
//////                    intent.putStringArrayListExtra("Klabel_name_list", arrayList1);
//////
//////                    Log.d("yuzhao", ""+Arrays.asList(arrayList1));
//                    String videopath  = "/sdcard/tencent/MicroMsg/WeiXin/wx_camera_1531102334784.mp4";
//                    String picpath = "/storage/emulated/0/DCIM/1528426443248.jpg";
//
//                    //文字
//                    String text = "哈哈";
//                    intent.putExtra("Kdescription",text);
//                    intent.putExtra("Ksnsupload_type", 14);
//
//                    intent.putExtra("sight_md5",ThreadClass.cg(videopath));
//                    //intent.putExtra("KSightDraftEntrance",false);
//                    intent.putExtra("KSightPath",videopath);
//                    intent.putExtra("KSightThumbPath",picpath);
//
//
//
//
//
//
//
//                    launcherUiActivity.startActivity(intent);
//
//                    flagclickbegin = true;


//                    Object sendshareObj = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.y.g.a", lpparam.classLoader));
//                    XposedHelpers.setObjectField(sendshareObj, "appId", "wx0f73cdef9a0b09b0");
//                    XposedHelpers.setObjectField(sendshareObj, "appName", "授渔");
//                    XposedHelpers.setIntField(sendshareObj, "ggO", 2);
//
////                    Object wXMediaMessage = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.opensdk.modelmsg.WXWebpageObject", lpparam.classLoader), "https://www.shouyu.com/course.html?curriculaThemeId=ct-7e5ca4fd-8c4b-4bc6-ad16-23cf0c6b9017");
//                    Object wXMediaMessage = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.opensdk.modelmsg.WXWebpageObject", lpparam.classLoader), "");
//
//                    Object wxsendMediaMessage = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.opensdk.modelmsg.WXMediaMessage",lpparam.classLoader));
//                    XposedHelpers.setObjectField(wxsendMediaMessage,"title","短视频自媒体实战运营速成到精通");
//                    XposedHelpers.setObjectField(wxsendMediaMessage,"description","快来报名参加吧");
//                    XposedHelpers.setObjectField(wxsendMediaMessage,"mediaObject",wXMediaMessage);
//
//
//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.pluginsdk.model.app.l", lpparam.classLoader), "a", sendshareObj, wxsendMediaMessage, mtalker, null, null);
//
//
//                    Object sendFileObject = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.opensdk.modelmsg.WXFileObject",lpparam.classLoader));
//                    XposedHelpers.callMethod(sendFileObject,"setFilePath","/sdcard/temp/temp/wxapi");
//                    Object wXMediaMessage = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.opensdk.modelmsg.WXMediaMessage",lpparam.classLoader));
//                    XposedHelpers.setObjectField(wXMediaMessage,"mediaObject",sendFileObject);
//
//                    File file = new File("/sdcard/temp/temp/wxapi");
//                    XposedHelpers.setObjectField(wXMediaMessage,"title",file.getName());
//                    XposedHelpers.setObjectField(wXMediaMessage,"description",XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.bh",lpparam.classLoader),"bB",file.length()));
//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.pluginsdk.model.app.l", lpparam.classLoader), "a", wXMediaMessage,"" ,"", mtalker, 4, null);
//
//
//                    IMediaObject wXFileObject = new WXFileObject();
//                    wXFileObject.setFilePath(str);
//                    WXMediaMessage wXMediaMessage = new WXMediaMessage();
//                    wXMediaMessage.mediaObject = wXFileObject;
//                    File file = new File(str);
//                    wXMediaMessage.title = file.getName();
//                    wXMediaMessage.description = com.tencent.mm.sdk.platformtools.bh.bB(file.length());
//                    com.tencent.mm.pluginsdk.model.app.l.a(wXMediaMessage, "", "", this.yOi, 4, null);
//
//                    h.a(this.mvI.mController.ypy, this.mvI.toUser, str, bVar.msw, new Runnable(this) {
//
//                    Object sendfav = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.fav.a.g",lpparam.classLoader));
//                    XposedHelpers.setIntField(sendfav,"field_type",18);
//                    XposedHelpers.setIntField(sendfav,"field_id",3);
//
//                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.plugin.fav.ui.h",lpparam.classLoader),"a",launcherUiActivity.getBaseContext(),mtalker,"",sendfav,null);


//                }
            }

        });


//        findAndHookMethod(XposedHelpers.findClass("com.tencent.mm.plugin.masssend.ui.b",lpparam.classLoader), "FZ",  String.class, new XC_MethodHook() {
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param)
//                    throws Throwable {
//
//
//                Log.d("mylog", param.args[0].toString() + "--");
//                Object ojz = XposedHelpers.getObjectField(param.thisObject,"oJz");
//                Object ojZ = XposedHelpers.getObjectField(param.thisObject,"oJA");
//                int size = (int)XposedHelpers.callMethod(ojZ,"size");
//                Object oJB = XposedHelpers.getObjectField(param.thisObject,"oJB");
//
//                Log.d("mylog", ojz + "--"+ojZ+"--"+size+"--"+oJB+"--");
//
//
//
//            }
//        });
*/






























    }

    private void hookyaoyiyao(LoadPackageParam lpparam) {
        XposedBridge.hookAllMethods(XposedHelpers.findClass("android.hardware.SystemSensorManager$SensorEventQueue", lpparam.classLoader), "dispatchSensorEvent", new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable {
                if (yaoyiyaoenble) {
                    yaoyiyaoenble = false;
                    lasttick = SystemClock.currentThreadTimeMillis();
                }

                if (SystemClock.currentThreadTimeMillis() - lasttick < 1000) {
                    ((float[]) paramAnonymousMethodHookParam.args[1])[0] = (new Random().nextFloat() * 1200.0F + 125.0F);
                    XposedBridge.log("我摇一摇了..");
                }

            }
        });
    }

    private void hookphonecontact(LoadPackageParam lpparam) {

        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.tencent.mm.plugin.subapp.ui.friend.b$2",lpparam.classLoader), "onClick", View.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                View view = (View) param.args[0];
                Object bVar = view.getTag();
                Field[] field = bVar.getClass().getDeclaredFields();
                for(Field a : field){
                    LogHelper.Log("Field ==" +a.getName()+"======"+ XposedHelpers.getObjectField(bVar,a.getName()),true);
                }



//                List<Object> mdx =(List<Object>) XposedHelpers.getObjectField(param.thisObject,"mdx");
//                LogHelper.Log("======size  ===============" +mdx.size(),true);
//                for(Object s : mdx){
//                    LogHelper.Log("=============everyone  ===============" +mdx.size(),true);
//                    Field[] field = s.getClass().getDeclaredFields();
//                    for(Field a : field){
//                        LogHelper.Log("Field ==" +a.getName()+"======"+ XposedHelpers.getObjectField(s,a.getName()),true);
//                    }
//                }



            }
        });



    }

    private void hooknearfriends(final LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.tencent.mm.plugin.nearby.ui.NearbyFriendsUI",lpparam.classLoader), "a", int.class, int.class, String.class,XposedHelpers.findClass("com.tencent.mm.ac.l",lpparam.classLoader) , new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                List<Object> mdx =(List<Object>) XposedHelpers.getObjectField(param.thisObject,"mdx");
                LogHelper.Log("======size  ===============" +mdx.size(),true);
                for(Object s : mdx){
                    LogHelper.Log("=============everyone  ===============" +mdx.size(),true);
                    Field[] field = s.getClass().getDeclaredFields();
                    for(Field a : field){
                        LogHelper.Log("Field ==" +a.getName()+"======"+ XposedHelpers.getObjectField(s,a.getName()),true);
                    }
                }



            }
        });

        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.tencent.mm.ui.contact.SayHiEditUI$1",lpparam.classLoader), "onMenuItemClick", MenuItem.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                LogHelper.Log("=================send log==================",true);
                LogHelper.Log("gwl" + XposedHelpers.getObjectField(param.thisObject,"gwl") + "zpX" +XposedHelpers.getObjectField(param.thisObject,"zpX")
                + "zpY" +  XposedHelpers.getObjectField(param.thisObject,"zpY") +"zpZ" + XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.ui.contact.SayHiEditUI",lpparam.classLoader),"a",XposedHelpers.getObjectField(param.thisObject,"zpZ")),true);

            }
        });

    }

    private void hookDatebaseMonitor(final LoadPackageParam lpparam) {


        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                log("com.tencent.mm.ui.LauncherUI onCreated" + "\n");
                launcherUiActivity = (Activity) param.thisObject;
            }
        });

        Class<?> dbClazz1 = XposedHelpers.findClass("com.tencent.mm.bu.f", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(dbClazz1, "insert", String.class, String.class, ContentValues.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                printTableInfo(param);


                String tableName = (String) param.args[0];


                //接受消息，isSend = 0;
                ContentValues val = (ContentValues) param.args[2];
                mtype = val.getAsString("type");
                misSend = val.getAsString("isSend");
                mcontent = val.getAsString("content");
                mtalker = val.getAsString("talker");
                //Log.d("yuzhao88", tableName + insertquanflag + mtype);


                mtalker = "medianote"; //语音记事本
                Log.d("yuzhao", "type:" + mtype + "isSend：" + misSend + "content" + mcontent);


                if (mtype.equals("1") && misSend.equals("0") && mcontent.equals("12")) {
                    //XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.bh.d",lpparam.classLoader),"y",launcherUiActivity.getBaseContext(),"nearby", ".ui.NearbyFriendsUI");
                    for(int i = 6000 ; i< 6200; i ++){
                        Util.addContact(launcherUiActivity,"i"+ i,"1857710"+ connumberStr(i));
                    }

                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.platformtools.z",lpparam.classLoader),"syncUploadMContactStatus",true,true);
                    XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.platformtools.a",lpparam.classLoader),"Vn");

                    launcherUiActivity.startActivity(new Intent(launcherUiActivity,XposedHelpers.findClass("com.tencent.mm.plugin.account.bind.ui.MobileFriendUI",lpparam.classLoader)));


                    //XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.bh.d",lpparam.classLoader),"y",launcherUiActivity.getBaseContext(),"shake", ".ui.ShakeReportUI");


                    //lasttick = SystemClock.currentThreadTimeMillis();





                }else if (mtype.equals("1") && misSend.equals("0") && mcontent.equals("13")) {
                    Util.clearContact(launcherUiActivity);
                }else if(mtype.equals("1") && misSend.equals("0") && mcontent.equals("14")){
                    yaoyiyaoenble = true;
                }
            }
        });
    }

    private String connumberStr(int i) {
        if(i<10){
            return "000"+ i;
        }else if(i < 100){
            return "00"+ i;
        }else if(i<1000){
            return "0" + i;
        }else {
            return String.valueOf(i);
        }
    }

    private void hookcheckifhook(LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod("com.tencent.mm.app.s", lpparam.classLoader, "a", StackTraceElement[].class,new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log(" ==============hook check begin ================= ,return" + param.getResult());

            }
        });

    }

    private void hooklocationtest(LoadPackageParam lpparam) {
//        if (xposedPackageJson.has("android.location.Location.getLatitude"))
//        {                XposedBridge.hookAllMethods(Location.class, "getLatitude", new MethodHookValue("修改的经度值"));  }
//        if (xposedPackageJson.has("android.location.Location.getLongitude"))
//        {                XposedBridge.hookAllMethods(Location.class, "getLongitude", new MethodHookValue("修改的维度值")));  }

        XposedHelpers.findAndHookMethod("android.location.Location.getLatitude", lpparam.classLoader, "getLatitude", Bundle.class,new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                return (double)39.000;
            }
        });
        XposedHelpers.findAndHookMethod("android.location.Location.getLatitude", lpparam.classLoader, "getLongitude", Bundle.class,new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                return (double)100.000;
            }
        });
    }

    private void hooklocation(LoadPackageParam lpparam) {
        HookUtils.HookAndChange(launcherUiActivity.getClassLoader(),40,155,100,100);
    }

    private void hookaddressmodel(LoadPackageParam lpparam) {


//        XposedHelpers.findAndHookMethod(Location.class, "getLongitude", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                XposedBridge.log("进入，开始修改经度坐标");
//                param.setResult(115.193721);
//            }
//        });
//
//        XposedHelpers.findAndHookMethod(Location.class, "getLatitude", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                XposedBridge.log("进入，开始修改纬度坐标");
//                param.setResult(33.655748);
//            }
//        });




    }

    private void hook673chatroom(LoadPackageParam lpparam) {


    }



    private void HooKMap(XC_LoadPackage.LoadPackageParam mLpp) {
        //region hook地图坐标
        try {
            final Class ml = mLpp.classLoader.loadClass("com.tencent.map.geolocation.TencentLocationManager");
            final Method ms[] = ml.getMethods();
            for (int i = 0; i < ms.length; i++) {
                if (!systemName.contains(ms[i].getName())) {

                    if (ms[i].getName().equals("requestLocationUpdates")) {
                        XposedBridge.hookMethod(ms[i], new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                for (int i = 0; i < param.args.length; i++) {
                                    log("目标对象" + i + ":" + param.args[i].getClass().getName());
                                    Class mc = param.args[i].getClass();
                                    final Method ms2[] = mc.getMethods();
                                    for (int x = 0; x < ms2.length; x++) {
                                        if (!systemName.contains(ms2[x].getName())) {
                                            if (!ms2[x].getName().equals("onLocationChanged")){
                                                continue;
                                            }

                                            log("hook  method " +ms2[x]);

                                            //   log("hook onLocationChanged");
                                            XposedBridge.hookMethod(ms2[x], onLocationChanged);
                                        }
                                    }
                                }
                            }

                        });
                    }
                }
            }
        } catch (Throwable throwable) {

            XposedBridge.log("提取信息出错：" + throwable.getMessage());
        }
        //endregion
    }

    //region 地图hook
    private XC_MethodHook onLocationChanged = new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);
            if (!MyHelper.GetString("gps", "nofile").equals("nofile")) {
                Object[] obj = param.args;
                if (obj != null) {
                    for (int v = 0; v < obj.length; v++) {
                        if (v == 0) {
                            Class mc = obj[v].getClass();
                            log("Class is " + mc.getName());
                            Field[] field = mc.getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
                            try {
                                for (int j = 0; j < field.length; j++) { // 遍历所有属性
                                    String name = field[j].getName(); // 获取属性的名字
                                    field[j].setAccessible(true);
                                    String type = field[j].getGenericType().toString(); // 获取属性的类型

                                    if (j < 2) {

                                        Object myhaha = field[j].get(obj[v]);

                                        log("第" + j + "个的类型：" + myhaha.getClass().getName());
                                        Class haha = myhaha.getClass();
                                        Field[] hahafields = haha.getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
                                        log("子类数量：" + hahafields.length);

                                        if (hahafields.length >= 6) {

                                            if (hahafields[0].getGenericType().equals(double.class)
                                                    && hahafields[1].getGenericType().equals(double.class)
                                                    && hahafields[2].getGenericType().equals(double.class)
                                                    && hahafields[3].getGenericType().equals(float.class)
                                                    && hahafields[4].getGenericType().equals(String.class)
                                                    && hahafields[5].getGenericType().equals(String.class)) {
                                                log("gps hook1");
                                                updateLocation();
                                                hahafields[0].setAccessible(true);
                                                hahafields[0].set(myhaha, gpsX);

                                                hahafields[1].setAccessible(true);
                                                hahafields[1].set(myhaha, gpsY);

                                                hahafields[4].setAccessible(true);
                                                hahafields[4].set(myhaha, "");

                                                hahafields[5].setAccessible(true);
                                                hahafields[5].set(myhaha, "");

                                                // XposedBridge.log("重定向到新坐标：" + gpsX + " " + gpsY);
                                            } else if (hahafields[0].getGenericType().equals(String.class)
                                                    && hahafields[1].getGenericType().equals(String.class)
                                                    && hahafields[2].getGenericType().equals(double.class)
                                                    && hahafields[3].getGenericType().equals(double.class)
                                                    && hahafields[4].getGenericType().equals(double.class)
                                                    && hahafields[5].getGenericType().equals(float.class)) {
                                                log("gps hook2");
                                                updateLocation();
                                                hahafields[0].setAccessible(true);
                                                hahafields[0].set(myhaha, "");

                                                hahafields[1].setAccessible(true);
                                                hahafields[1].set(myhaha, "");


                                                hahafields[3].setAccessible(true);
                                                hahafields[3].set(myhaha, gpsX);

                                                hahafields[4].setAccessible(true);
                                                hahafields[4].set(myhaha, gpsY);

                                                if (hahafields[2].getName().equals("a")) {
                                                    hahafields[2].setAccessible(true);
                                                    hahafields[2].set(myhaha, gpsX);

                                                    hahafields[3].setAccessible(true);
                                                    hahafields[3].set(myhaha, gpsY);

                                                    hahafields[4].setAccessible(true);
                                                    hahafields[4].set(myhaha, 0.0);
                                                }


                                                // XposedBridge.log("重定向到新坐标：" + gpsX + " " + gpsY);
                                            } else {
                                                log("gps hook3");
                                            }
                                        }


                                        for (int g = 0; g < hahafields.length; g++) {
                                            hahafields[g].setAccessible(true);
                                            updateLocation();
                                            String nam2e = hahafields[g].getName();

                                            String type2 = hahafields[g].getGenericType().toString(); // 获取属性的类型
                                            log(haha.getName() + " GG参数名[" + g + "]：" + haha.getName() + " 属性名：" + nam2e + " 类型:" + type2 + " 值：" + hahafields[g].get(myhaha));
                                        }
                                        field[j].set(obj[v], myhaha);

                                    }
                                    log("参数名[" + v + "|" + j + "]：" + mc.getName() + " 属性名：" + name + " 类型:" + type + " 值：" + field[j].get(obj[v]));
                                }
                            } catch (Exception e) {

                                log(e.getMessage());
                            }
                        }

                    }
                }
            }
        }
    };

    private void updateLocation() {


        String[] gps = MyHelper.GetString("gps", "39.8880261,116.4109518").split(",");



//        gpsX = Double.valueOf(gps[0].trim()).doubleValue();
//        gpsY = Double.valueOf(gps[1].trim()).doubleValue();

    }








    private void hooksendmoments(LoadPackageParam lpparam) {

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.sns.ui.SnsUploadUI", lpparam.classLoader, "onCreate", Bundle.class,new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                if(flagclickbegin){
                    Activity snsuploadui =(Activity) param.thisObject;
                    DisplayMetrics dm = snsuploadui.getResources().getDisplayMetrics();
                    float heigth = (float) (dm.heightPixels * 0.0744);
                    float width = (float) (dm.widthPixels * 0.925);

                    Toast.makeText(snsuploadui,"height :" + heigth +",width :" +width ,Toast.LENGTH_SHORT).show();

                    new ThreadClass(width,heigth).start();
                    flagclickbegin = false;
                }

            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.sns.ui.ah", lpparam.classLoader, "a", int.class, int.class, XposedHelpers.findClass("org.b.d.i",lpparam.classLoader), String.class, List.class,
                XposedHelpers.findClass("com.tencent.mm.protocal.c.apt",lpparam.classLoader), int.class, boolean.class, List.class, XposedHelpers.findClass("com.tencent.mm.pointers.PInt",lpparam.classLoader), String.class, int.class, int.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        for(int i = 0;i<param.args.length;i++){
                            Log.d("yuzhao","haha" + param.args[i]+"\n");

                        }



                        Object a1 = param.args[0];
                        Object a2 = param.args[1];
                        Object a3 = param.args[2];
                        Object a4 = param.args[3];
                        Object a5 = param.args[4];
                        Log.d("yuzhao",""+a1+a2+a3+a4+Arrays.asList(a5));

                        Object a6 = param.args[5];
                        Object a7 = param.args[6];
                        Object a8 = param.args[7];
                        Object a9 = param.args[8];
                        Object a10 = param.args[9];
                        Log.d("yuzhao","222"+a6+a7+a8+Arrays.asList(a9)+a10);





                    }
                });



    }

    private void hookqfrelate(LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.base.h", lpparam.classLoader, "a", Context.class, String.class, String.class, DialogInterface.OnClickListener.class, DialogInterface.OnCancelListener.class,new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                if (param.args != null) {
                    for (int i = 0; i < param.args.length; i++) {

                        Log.d("message", "com.tencent.mm.ui.base.h:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");



                    }
                }

            }
        });

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.t", lpparam.classLoader, "a",Activity.class, int.class, int.class, Intent.class, String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                log("error");
                int type = (int)param.args[2];
                if(type == -100){
                    log("loggin error");
                }
            }
        });




    }

    private void hookfriendshared(LoadPackageParam lpparam) {

        /*

        XposedHelpers.findAndHookConstructor("com.tencent.mm.plugin.sns.model.y",lpparam.classLoader,int.class,String.class,boolean.class,int.class,new XC_MethodHook(){
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Object a1 = param.args[0];
                Object a2 =param.args[1];
                Object a3 =param.args[2];
                Object a4 =param.args[3];
                Log.d("message", "com.tencent.mm.modelmulti.i" + a1 + "------------------param2：" + a2+"------------------param3："+a3+"------------------param4："+a4);
            }
        });
        XposedHelpers.findAndHookConstructor("com.tencent.mm.plugin.sns.model.r",lpparam.classLoader,String.class, int.class, int.class, List.class, XposedHelpers.findClass("com.tencent.mm.protocal.c.bpu",lpparam.classLoader), int.class, String.class, int.class, LinkedList.class, int.class,XposedHelpers.findClass("com.tencent.mm.protocal.c.aro",lpparam.classLoader), boolean.class, LinkedList.class, XposedHelpers.findClass("com.tencent.mm.protocal.c.bmi",lpparam.classLoader), XposedHelpers.findClass("com.tencent.mm.bl.b",lpparam.classLoader) , String.class,new XC_MethodHook(){
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);

                if (param.args != null) {
                    for (int i = 0; i < param.args.length; i++) {

                        Log.d("message", "com.tencent.mm.plugin.sns.model.r index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");



                    }
                }
//                Object a1 = param.args[0];
//                Object a2 =param.args[1];
//                Object a3 =param.args[2];
//                Object a4 =param.args[3];
//                Object a5 =param.args[4];
//                Log.d("message", "com.tencent.mm.plugin.sns.model.r" + a1 + "------------------param2：" + a2+"------------------param3："+a3+"------------------param4："+a4+"------------------param5："+ a5);
//                Object a6 = param.args[0];
//                Object a7 =param.args[1];
//                Object a8 =param.args[2];
//                Object a9 =param.args[3];
//                Object a10 =param.args[4];
            }
        });
        */

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.sns.ui.SnsUploadUI", lpparam.classLoader, "onResume", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object snsUploadUIAct = param.thisObject;
                Object mController = XposedHelpers.getObjectField(snsUploadUIAct,"mController");
                LinkedList menuItens=(LinkedList) XposedHelpers.getObjectField(mController,"ypB");
                XposedBridge.log("menuItems size "+menuItens.size());
                Object mentem = menuItens.get(0);
                View v = (View) XposedHelpers.getObjectField(mentem,"yqg");
                v.performClick();

            }
        });

//        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.p", lpparam.classLoader, "onCreateOptionsMenu", Menu.class,new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                Menu meun = (Menu)param.args[0];
//                meun.getItem(0).setOnMenuItemClickListener()
//            }
//        });


    }

    private void hooklauchui(final LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                log("com.tencent.mm.ui.LauncherUI onCreated" + "\n");
                launcherUiActivity = (Activity) param.thisObject;
                Object  a = XposedHelpers.getStaticObjectField(XposedHelpers.findClass("com.tencent.mm.z.at",lpparam.classLoader),"glC");
                log("loginname :"+ XposedHelpers.callMethod(a,"I","login_user_name",""));

            }
        });
        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onResume", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                log("com.tencent.mm.ui.LauncherUI onResume" + "\n");
//                launcherUiActivity = (Activity) param.thisObject;
                Object  a = XposedHelpers.getStaticObjectField(XposedHelpers.findClass("com.tencent.mm.z.at",lpparam.classLoader),"glC");
                log("loginname 2:"+ XposedHelpers.callMethod(a,"I","login_user_name",""));

            }
        });
    }

    private void batchsend(LoadPackageParam lpparam) {
        Object roomSend = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.masssend.a.a",lpparam.classLoader));
        String rommNumber = "wxid_6uq54h678wbs22;wxid_k2qvvyeecbhf21";
        XposedHelpers.setObjectField(roomSend,"oJg",rommNumber);
        XposedHelpers.setIntField(roomSend,"oJh",2);
        XposedHelpers.setObjectField(roomSend,"filename","hello");
        XposedHelpers.setIntField(roomSend,"msgType",1);
        Object send = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.masssend.a.f",lpparam.classLoader),roomSend,true);

        Object sender = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.z.au", lpparam.classLoader), "Dv");
        XposedHelpers.callMethod(sender, "a", send, 0);

        log("createtime"+XposedHelpers.getLongField(roomSend,"createTime"));




        //群发图片

        Object roomSendpic = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.plugin.masssend.a.b",lpparam.classLoader),"j","/storage/emulated/0/DCIM/1528426443248.jpg",rommNumber,2,1);
        Object send2 = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.masssend.a.f",lpparam.classLoader),roomSendpic,true,1);
        XposedHelpers.callMethod(sender, "a", send2, 0);


        log("createtime"+XposedHelpers.getLongField(roomSendpic,"createTime"));
    }

    private void hookDatebasePassword(LoadPackageParam lpparam) {


        Class mSQLiteDatabase = XposedHelpers.findClass("com.tencent.wcdb.database.SQLiteDatabase",lpparam.classLoader);
        XposedBridge.log(""+"-->"+"Class mSQLiteDatabase == " + mSQLiteDatabase);

        Class mSQLiteDatabaseConfiguration = XposedHelpers.findClass("com.tencent.wcdb.database.SQLiteDatabaseConfiguration",                    lpparam.classLoader            );
        XposedBridge.log(""+"-->"+"Class mSQLiteDatabaseConfiguration == " + mSQLiteDatabaseConfiguration);

        final Class mSQLiteCipherSpec = XposedHelpers.findClass(
                "com.tencent.wcdb.database.SQLiteCipherSpec",
                lpparam.classLoader
        );
        XposedBridge.log(""+"-->"+"Class mSQLiteCipherSpec == " + mSQLiteCipherSpec);

        XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteConnectionPool",
                lpparam.classLoader,
                "open",
                mSQLiteDatabase,
                mSQLiteDatabaseConfiguration,
                byte[].class,
                mSQLiteCipherSpec,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        if(param.args[0]==null) return;
                        if(param.args[2]==null) return;

                        byte[] array = (byte[]) param.args[2];

                        //数据库
                        String dataBase= param.args[0].toString();

                        //密码
                        String pass=""+(new String(array));
                        Log.d("XposedTag","array => " + pass);
                        XposedBridge.log(""+"-->"+"Class mSQLiteCipherSpec == " + mSQLiteCipherSpec);



                        //复制数据库
//                        File file=new File(dataBase.replace("SQLiteDatabase:",""));
//                        XposedBridge.log("file:"+file.getPath());



                        //FileCopy(file,desfile);
                    }
                });
    }


    private void HookSendObject(LoadPackageParam lpparam) {
                final Class<?> myclass2 = XposedHelpers.findClass("com.tencent.mm.ac.o", lpparam.classLoader);

        final Method[] ms2 = myclass2.getDeclaredMethods();
        Log.d("my2log", "count " + ms2.length + "");
        for (int i = 0; i < ms2.length; i++) {
            Log.d("my2log", "hook name:" + ms2[i].getName());
            try {
                final int finalI = i;
                Log.d("my2log", "hook name:" + ms2[i].getName());
                XposedBridge.hookMethod(ms2[i], new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        String mename = ms2[finalI].getName();
                        Log.d("my2log", "name:" + mename);
                        if (param.args != null) {
                            for (int i = 0; i < param.args.length; i++) {

                                Log.d("my2log", "index:" + (i + 1) + "  value:" + param.args[i]!=null?param.args[i].toString():"null");



                            }
                        }


                        if (param.getResult() != null) {
                            if(ms2[finalI].getName().equals("RL")){


                            }
                            Log.d("my2log", "return :" + param.getResult().toString());

                        }


                    }
                });
            } catch (Exception err) {

                log("error：" + err.getMessage());
            }
        }



    }


    private void printTableInfo(MethodHookParam param) {

        Object tableName = param.args[0];
        Object obj = param.args[1];
        ContentValues val = (ContentValues)param.args[2];







        Log.d("yuzhao","scc:tableName是:"+(tableName == null ? "null":tableName)+"下标为1的是："+(obj == null ? "null":obj ));
        for(String key: val.keySet()) {
            Log.d("yuzhao","scc:value的key是:" + key + "，value的值是toString:" + val.get(key).toString() + "=======");
        }



    }

    private void printTableInfo1(MethodHookParam param) {

        Object tableName = param.args[0];
        Object obj = param.args[2];
        ContentValues val = (ContentValues)param.args[1];

        Object Strings[] = (String[])param.args[3];


        Log.d("yuzhao","scc:tableName是:"+tableName+"下标为1的是："+obj + "数组是： "+ Arrays.toString(Strings));
        for(String key: val.keySet()) {
            Log.d("yuzhao","scc:value的key是:" + key + "，value的值是toString:" + val.get(key).toString() + "=======");
        }



    }

    public void HookWeiXinLog(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        String c =  "com.tencent.mm.sdk.platformtools.w";


        findAndHookMethod(c, loadPackageParam.classLoader, "d", String.class, String.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {


                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString());

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "e", String.class, String.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {

                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString());

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "f", String.class, String.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {

                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString());

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "i", String.class, String.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {

                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString());

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "v", String.class, String.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {

                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString());

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "w", String.class, String.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {
                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString());
//                CheckLog(param.args[0].toString());

            }
        });
        //=================================================================================================================================//

        findAndHookMethod(c, loadPackageParam.classLoader, "d", String.class, String.class,Object[].class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {


                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString()+"--" + Arrays.asList((Object[]) param.args[2]));;

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "e", String.class, String.class,Object[].class,  new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {

                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString()+"--" + Arrays.asList((Object[]) param.args[2]));;

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "f", String.class, String.class, Object[].class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {

                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString()+"--" + Arrays.asList((Object[]) param.args[2]));;

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "i", String.class, String.class,Object[].class,  new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {

                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString()+"--" + Arrays.asList((Object[]) param.args[2]));;

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "v", String.class, String.class,Object[].class,  new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {

                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString()+"--" + Arrays.asList((Object[]) param.args[2]));;

            }
        });
        findAndHookMethod(c, loadPackageParam.classLoader, "w", String.class, String.class, Object[].class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {
                Log.d("yuzhao", param.args[0].toString() + "--" + param.args[1].toString()+"--" + Arrays.asList((Object[]) param.args[2]));;
//                CheckLog(param.args[0].toString());

            }
        });



    }




//    }

//    private void hook(ClassLoader appClassLoader, Context context) {
//        Class<?> securityCheckClazz = XposedHelpers.findClass("com.alipay.mobile.base.security.CI", appClassLoader);
//        XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new Object[]{String.class, String.class, String.class, new XC_MethodHook() {
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                log(" fdafkadjfajdlf1a  fjadsfjalj LauncherActivity onCreated" + "\n");
//                Object object = param.getResult();
//                XposedHelpers.setBooleanField(object, "a", false);
//                param.setResult(object);
//                super.afterHookedMethod(param);
//            }
//        }});
//
//        XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new Object[]{Class.class, String.class, String.class, new XC_MethodReplacement() {
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log(" fdafkadjfajdlfa2  fjadsfjalj LauncherActivity onCreated" + "\n");
//                return Byte.valueOf((byte) 1);
//            }
//        }});
//        XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new Object[]{ClassLoader.class, String.class, new XC_MethodReplacement() {
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log(" fdafkadjfajdlfa3  fjadsfjalj LauncherActivity onCreated" + "\n");
//                return Byte.valueOf((byte) 1);
//            }
//        }});
//        XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new Object[]{new XC_MethodReplacement() {
//            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                log(" fdafkadjfajdlfa4  fjadsfjalj LauncherActivity onCreated" + "\n");
//                return Boolean.valueOf(false);
//            }
//        }});
//
//        Class<?> classappy1 = XposedHelpers.findClass("com.alipay.mobile.quinox.LauncherActivity",appClassLoader);
//        XposedHelpers.findAndHookMethod(classappy1, "onCreate", Bundle.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log(" fdafkadjfajdlfa  fjadsfjalj LauncherActivity onCreated" + "\n");
//            }
//
//        });
//    }
}
