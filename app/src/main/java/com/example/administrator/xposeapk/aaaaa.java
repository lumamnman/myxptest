package com.example.administrator.xposeapk;

import java.lang.reflect.Constructor;

/**
 * Created by ckerman on 2018/9/11.
 */

public class aaaaa {
    /*


    //群发文本
    public static void masssend(final String ids, final String message) {
        if (message.length() == 0) {
            Log("群发内容为空");
            return;
        }

        MainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log(ids);
                Log(message);

                Object sender = callStaticMethod(wxNames.mmCoreStaticClass, wxNames.mmCoreStaticReturnNetSceneSend, new Object[]{});

                Class aVarClass = findClass(MainActivity.getClassLoader(), wxNames.massSendAA);
                Object aVar = null;

                try {
                    aVar = aVarClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                if (aVar == null) {
                    Log("找不到aVar");
                }

                setFieldValue(aVar, wxNames.massSendAAField[0], "");//clientid
                setFieldValue(aVar, wxNames.massSendAAField[1], 199);//status
                setFieldValue(aVar, wxNames.massSendAAField[2], (long) 1513692062);//lastmodifytime
                setFieldValue(aVar, wxNames.massSendAAField[3], (long) 1513692060);//createtime
                setFieldValue(aVar, wxNames.massSendAAField[4], message);//消息体
                setFieldValue(aVar, wxNames.massSendAAField[5], "");//thumfilename
                setFieldValue(aVar, wxNames.massSendAAField[6], ids);//发送对象
                setFieldValue(aVar, wxNames.massSendAAField[7], ids.split(";").length);//人数
                setFieldValue(aVar, wxNames.massSendAAField[8], 1);//类型
                setFieldValue(aVar, wxNames.massSendAAField[9], 0);//mediatime
                setFieldValue(aVar, wxNames.massSendAAField[10], message.getBytes(Charset.forName("UTF8")).length);//datanetoffset  长度？
                setFieldValue(aVar, wxNames.massSendAAField[11], message.getBytes(Charset.forName("UTF8")).length); //datalen 长度？
                setFieldValue(aVar, wxNames.massSendAAField[12], 0);//thumbnetoffset
                setFieldValue(aVar, wxNames.massSendAAField[13], 0);//thumbnetoffset len
                setFieldValue(aVar, wxNames.massSendAAField[14], 0);//reserved1
                setFieldValue(aVar, wxNames.massSendAAField[15], 0);//reservd2
                setFieldValue(aVar, wxNames.massSendAAField[16], "");//reservd3
                setFieldValue(aVar, wxNames.massSendAAField[17], "");//reservd4


                Class fVarClass = findClass(MainActivity.getClassLoader(), wxNames.massSendAF);
                if (fVarClass == null) {
                    Log("找不到fVarClass");
                }
                Constructor c = null;
                try {
                    c = fVarClass.getDeclaredConstructor(findClass(MainActivity.getClassLoader(), wxNames.massSendAA), boolean.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                c.setAccessible(true);
                Object fVar = null;
                if (c == null) {
                    Log("找不到c");
                }

                try {
                    fVar = c.newInstance(aVar, false);
                } catch (Throwable t) {
                    Log("实例化出错：" + t);
                }

                if (fVar == null) {
                    Log("找不到fVar");
                }

                Object result = callMethod(sender, findMethod(sender.getClass(), wxNames.sendMethod_a, new Class[]{findClass(MainActivity.getClassLoader(), wxNames.netSceneBaseClass), int.class}), new Object[]{fVar, 0});

                Log("群发结果:" + result.toString());
            }

        });


    }

    //群发图片
    public static void masssendImage(String ids, byte[] data) {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tencent/MicroMsg/WeiXin/xx" + System.currentTimeMillis() + ".jpg";

        Log("路径：" + path);

        if (!FileHelper.WriteFile(path, data)) {
            Log("写入图片文件时失败，群发失败");
            return;
        }

        Object sender = callStaticMethod(wxNames.mmCoreStaticClass, wxNames.mmCoreStaticReturnNetSceneSend, new Object[]{});


        Method jj = findMethod(findClass(MainActivity.getClassLoader(), wxNames.massSendAB), wxNames.massSendABMethod, String.class, String.class, int.class, int.class);

        Object aVar = callStaticMethod(jj, new Object[]{path, ids, ids.split(";").length, 0});

        Class fVarClass = findClass(MainActivity.getClassLoader(), wxNames.massSendAF);
        if (fVarClass == null)

        {
            Log("找不到fVarClass");
        }

        Constructor[] cs = fVarClass.getDeclaredConstructors();

        Constructor c = null;
        try

        {
            c = fVarClass.getDeclaredConstructor(findClass(MainActivity.getClassLoader(), wxNames.massSendAA), boolean.class);
        } catch (
                NoSuchMethodException e)

        {
            e.printStackTrace();
        }
        c.setAccessible(true);
        Object fVar = null;
        if (c == null)

        {
            Log("找不到c");
        }

        try

        {
            fVar = c.newInstance(aVar, false);
        } catch (
                Throwable t)

        {
            Log("实例化出错：" + t);
        }

        if (fVar == null)

        {
            Log("找不到fVar");
        }

        Object result = callMethod(sender, findMethod(sender.getClass(), wxNames.sendMethod_a, new Class[]{findClass(MainActivity.getClassLoader(), wxNames.netSceneBaseClass), int.class}), new Object[]{fVar, 0});

        Log("群发结果:" + result.toString());
    }
    */
}
