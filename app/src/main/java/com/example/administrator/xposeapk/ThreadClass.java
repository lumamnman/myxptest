package com.example.administrator.xposeapk;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class ThreadClass extends Thread {
    private float x, y;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //TODO Auto-generated catch block				e.printStackTrace();
        }

        try {
            Instrumentation inst = new Instrumentation();
            inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_DOWN, x, y, 0));
            inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_UP, x, y, 0));
            Log.d("点击位置", x + "," + y);
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }            //线程睡眠3s
    }


    public ThreadClass(float x, float y) {
        this.x = x;

        this.y = y;
    }

    public static String j(File file) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        long j = 102400;
        if (file == null || !file.exists()) {
            return null;
        }
        FileInputStream fileInputStream2;
        try {
            fileInputStream2 = new FileInputStream(file);
            try {
                if (102400 > file.length()) {
                    j = file.length();
                }
                String b = b(fileInputStream2, (int) j);
                fileInputStream2.close();
                try {
                    fileInputStream2.close();
                    return b;
                } catch (IOException e) {
                    return b;
                }
            } catch (Exception e2) {
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    public static final String b(InputStream inputStream, int i) {
        String str = null;
        if (inputStream != null && i > 0) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                StringBuilder stringBuilder = new StringBuilder(32);
                byte[] bArr = new byte[i];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                byte[] digest = instance.digest();
                for (byte b : digest) {
                    stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
                }
                str = stringBuilder.toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static String cg(String str) throws Throwable {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            return j(file);
        }
        return null;
    }





}
