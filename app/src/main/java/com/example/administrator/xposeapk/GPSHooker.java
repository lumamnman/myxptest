package com.example.administrator.xposeapk;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class GPSHooker implements IXposedHookLoadPackage {

    private final String TAG = "Xposed";
    private LoadPackageParam mLpp;

    public void log(String s){
        Log.d(TAG, s);
        XposedBridge.log(s);
    }

    //不带参数的方法拦截
    private void hook_method(String className, ClassLoader classLoader, String methodName,
            Object... parameterTypesAndCallback){
        try {
            XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypesAndCallback);
        } catch (Exception e) {
            XposedBridge.log(e);
        }
    }

    //带参数的方法拦截
    private void hook_methods(String className, String methodName, XC_MethodHook xmh){
        try {
            Class<?> clazz = Class.forName(className);
            for (Method method : clazz.getDeclaredMethods())
                if (method.getName().equals(methodName)
                        && !Modifier.isAbstract(method.getModifiers())
                        && Modifier.isPublic(method.getModifiers())) {
                    XposedBridge.hookMethod(method, xmh);
                }
        } catch (Exception e) {
            XposedBridge.log(e);
        }
    }


    @Override
    public void handleLoadPackage(LoadPackageParam lpp) throws Throwable {
        mLpp = lpp;

        hook_method("android.net.wifi.WifiManager", mLpp.classLoader, "getScanResults",
                new XC_MethodHook(){
            /**
             * Android提供了基于网络的定位服务和基于卫星的定位服务两种
             * android.net.wifi.WifiManager的getScanResults方法
             * Return the results of the latest access point scan.
             * @return the list of access points found in the most recent scan.
             */
            @Override
            protected void afterHookedMethod(MethodHookParam param)
                    throws Throwable {
            	//返回空，就强制让apps使用gps定位信息
                param.setResult(null);
            }
        });

        hook_method("android.telephony.TelephonyManager", mLpp.classLoader, "getCellLocation",
                new XC_MethodHook(){
            /**
             * android.telephony.TelephonyManager的getCellLocation方法
             * Returns the current location of the device.
             * Return null if current location is not available.
             */
            @Override
            protected void afterHookedMethod(MethodHookParam param)
                    throws Throwable {
                param.setResult(null);
            }
        });

        hook_method("android.telephony.TelephonyManager", mLpp.classLoader, "getNeighboringCellInfo",
                new XC_MethodHook(){
            /**
             * android.telephony.TelephonyManager类的getNeighboringCellInfo方法
             * Returns the neighboring cell information of the device.
             */
            @Override
            protected void afterHookedMethod(MethodHookParam param)
                    throws Throwable {
                param.setResult(null);
            }
        });

        hook_methods("android.location.LocationManager", "requestLocationUpdates",
                new XC_MethodHook() {
            /**
             * android.location.LocationManager类的requestLocationUpdates方法
             * 其参数有4个：
             * String provider, long minTime, float minDistance,LocationListener listener
             * Register for location updates using the named provider, and a pending intent
             */
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                if (param.args.length == 4 && (param.args[0] instanceof String)) {
                    //位置监听器,当位置改变时会触发onLocationChanged方法
                    LocationListener ll = (LocationListener)param.args[3];

                    Class<?> clazz = LocationListener.class;
                    Method m = null;
                    for (Method method : clazz.getDeclaredMethods()) {
                        if (method.getName().equals("onLocationChanged")) {
                            m = method;
                            break;
                        }
                    }

                    try {
                        if (m != null) {
                            Object[] args = new Object[1];
                            Location l = new Location(LocationManager.GPS_PROVIDER);
                            //台北经纬度:121.53407,25.077796
                            double la=121.53407;
                            double lo=25.077796;
                            l.setLatitude(la); 
                            l.setLongitude(lo);
                            args[0] = l;
                            m.invoke(ll, args);
                            XposedBridge.log("fake location: " + la + ", " + lo);
                        }
                    } catch (Exception e) {
                        XposedBridge.log(e);
                    }
                }
            }
        });


        hook_methods("android.location.LocationManager", "getGpsStatus",
                new XC_MethodHook(){
            /**
             * android.location.LocationManager类的getGpsStatus方法
             * 其参数只有1个：GpsStatus status
             * Retrieves information about the current status of the GPS engine.
             * This should only be called from the {@link GpsStatus.Listener#onGpsStatusChanged}
             * callback to ensure that the data is copied atomically.
             * 
             */
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                GpsStatus gss = (GpsStatus)param.getResult();
                if (gss == null)
                    return;

                Class<?> clazz = GpsStatus.class;
                Method m = null;
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.getName().equals("setStatus")) {
                        if (method.getParameterTypes().length > 1) {
                            m = method;
                            break;
                        }
                    }
                }
                m.setAccessible(true);
                //make the apps belive GPS works fine now
                int svCount = 5;
                int[] prns = {1, 2, 3, 4, 5};
                float[] snrs = {0, 0, 0, 0, 0};
                float[] elevations = {0, 0, 0, 0, 0};
                float[] azimuths = {0, 0, 0, 0, 0};
                int ephemerisMask = 0x1f;
                int almanacMask = 0x1f;
                //5 satellites are fixed
                int usedInFixMask = 0x1f;
                try {
                    if (m != null) {
                        m.invoke(gss,svCount, prns, snrs, elevations, azimuths, ephemerisMask, almanacMask, usedInFixMask);
                        param.setResult(gss);
                    }
                } catch (Exception e) {
                    XposedBridge.log(e);
                }
            }
        });
    }

}
