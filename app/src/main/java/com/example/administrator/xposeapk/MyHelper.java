package com.example.administrator.xposeapk;


import android.database.Cursor;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;


public class MyHelper {

    public static String getTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        throwable.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
    public static Hashtable<String, ArrayList<Object>> GetTable(Cursor curr) {
        Hashtable<String, ArrayList<Object>> table = new Hashtable<String, ArrayList<Object>>();
        try {


            Object v0_1;

            String[] v2 = curr.getColumnNames();
            int v0 = curr.getPosition();
            curr.moveToPosition(-1);
            while (curr.moveToNext()) {

                int v3 = v2.length;
                int v1;
                for (v1 = 0; v1 < v3; ++v1) {
                    String c = v2[v1];
                    if (c.equals("roomdata") || c.equals("lvbuff") || c.equals("attrBuf") || c.equals("content")) {
                        v0_1 = curr.getBlob(v1);

                    } else {
                        v0_1 = curr.getString(v1);
                    }

                    Object v = v0_1;

                    if (!table.containsKey(c)) {
                        ArrayList<Object> arr = new ArrayList<Object>();
                        arr.add(v);
                        table.put(c, arr);

                    } else {

                        table.get(c).add(v);
                    }


                }


            }

            curr.moveToPosition(v0);
        } catch (Exception error) {

        }
        return table;
    }
    //region 读写共享值
    public static String GetString(String key, String DefaultString) {
        try {
            String encoding = "UTF-8";
            File file = new File("/sdcard/" + key);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTxt = bufferedReader.readLine();

                read.close();
                return lineTxt;
            } else {
                return DefaultString;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DefaultString;
        }
    }

    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    public static void SetString(String key, String value) {
        BufferedWriter fw = null;
        try {
            File file = new File("/sdcard/" + key);
            fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
            fw.write(value);
            fw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //endregion

    //发送消息
    public static void SendMsgToServer(final String key, final String value) {


        new Thread() {
            @Override
            public void run() {

                File file = new File("/sdcard/share_" + UUID.randomUUID());

                BufferedWriter fw = null;
                try {
                    fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
                    fw.write(key + "\n" + StringToBase64(value));
                    fw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fw != null) {
                        try {
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }.start();
    }

    public static byte[] ReadFile(String filename) {
        byte[] data = new byte[0];
        File f = new File(filename);
        if (!f.exists()) {
            return data;
        }
        try {
            FileChannel channel = null;
            FileInputStream fs = null;
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            data = byteBuffer.array();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //region base64转换
    public static String StringToBase64(String str) throws UnsupportedEncodingException {
        String strBase64 = new String(Base64.encode(str.getBytes("UTF-8"), Base64.DEFAULT));
        return strBase64.replace("\r", "").replace("\n", "");
    }

    public static String Base64ToString(String strBase64) throws UnsupportedEncodingException {
        return new String(Base64.decode(strBase64.getBytes(), Base64.DEFAULT), "UTF-8");
    }
    //endregion
}
