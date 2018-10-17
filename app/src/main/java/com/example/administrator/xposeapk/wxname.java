package com.example.administrator.xposeapk;

/**
 * Created by ckerman on 2018/9/11.
 */

public class wxname {



    /**
     * Created by ncla on 2017/12/13.
     */

    public class WxNames {
        public String Version;

        //获取类型
        public WxNames(String version) {
            if (version.equals("6.5.3")) {
                Init653();
                Version = "6.5.3";
            } else if (version.equals("6.5.8")) {
                Init8();
                Version = "6.5.23";
            } else if (version.equals("6.5.23")) {
                Init6523();
                Version = "6.5.23";
            }
        }

        //是否支持当前版本
        public boolean isSupported() {
            return Version != null;
        }


        private void Init6523() {
            sqliteDatabaseClass = "com.tencent.wcdb.database.SQLiteDatabase";

            mmCoreStaticClass = "com.tencent.mn.y.at";
            mmCoreStaticReturnNetSceneSend = "wY";

            modelMultiText = "com.tencent.mn.modelmulti.j";

            massSendAAField = new String[]{"dac", "status", "dhH", "dhI", "filename", "hjp", "hjq", "hjr", "msgType", "hjs", "hjt", "aPV", "dhF", "hju", "hjv", "hjw", "cuV", "cuW"};

            netSceneBaseClass = "com.tencent.mn.ad.k";

            massSendABMethod = "j";
        }

        private void Init8() {

            //日志相关
            xlogClass = "com.tencent.mars.xlog.Xlog";
            setConsoleLogOpen = "setConsoleLogOpen";
            setLogLevel = "setLogLevel";

            //sql数据库
            sqliteDatabaseClass = "com.tencent.wcdb.database.SQLiteDatabase";

            //获取发送对象
            mmCoreStaticClass = "com.tencent.mn.model.an";
            mmCoreStaticReturnNetSceneSend = "uC";
            sendMethod_d = "d";
            sendMethod_a = "a";

            //文本消息模型
            modelMultiText = "com.tencent.mn.modelmulti.k";

            //添加好友模型
            modelNetSceneVerifyUser = "com.tencent.mn.pluginsdk.model.m"; //This NetSceneVerifyUser init MUST use opcode == MM_VERIFYUSER_VERIFYOK

            //群发相关
            massSendAA = "com.tencent.mn.plugin.masssend.a.a";//文本群发
            massSendAAField = new String[]{"gYt", "status", "heU", "heV", "filename", "nhg", "nhh", "nhi", "msgType", "nhj", "nhk", "etm", "heS", "nhl", "nhm", "nhn", "gri", "grj"};//文本群发模型构造

            massSendAB = "com.tencent.mn.plugin.masssend.a.b";//图片群发类
            massSendABMethod = "e";//图片群发构造方法 insert: compressed bigImgPath =

            massSendAF = "com.tencent.mn.plugin.masssend.a.f";////MasSendInfo.fileName is null


            //消息模型基类
            netSceneBaseClass = "com.tencent.mn.w.k"; //initilized security limit count to


        }

        private void Init653() {
            //日志相关
            xlogClass = "com.tencent.mars.xlog.Xlog";
            setConsoleLogOpen = "setConsoleLogOpen";
            setLogLevel = "setLogLevel";

            //sql数据库
            sqliteDatabaseClass = "com.tencent.mndb.database.SQLiteDatabase";

            //获取发送对象
            mmCoreStaticClass = "com.tencent.mn.model.ak";
            mmCoreStaticReturnNetSceneSend = "vy";
            sendMethod_d = "d";
            sendMethod_a = "a";

            //文本消息模型
            modelMultiText = "com.tencent.mn.modelmulti.i";


            //群发相关
            massSendAA = "com.tencent.mn.plugin.masssend.a.a";
            massSendAB = "com.tencent.mn.plugin.masssend.a.b";
            massSendABMethod = "f";
            massSendAF = "com.tencent.mn.plugin.masssend.a.f";
            massSendAAField = new String[]{"dac", "status", "dhH", "dhI", "filename", "hjp", "hjq", "hjr", "msgType", "hjs", "hjt", "aPV", "dhF", "hju", "hjv", "hjw", "cuV", "cuW"};

            //消息模型基类
            netSceneBaseClass = "com.tencent.mn.v.k";
        }

        //region 属性

        //日志相关
        public String xlogClass = "com.tencent.mars.xlog.Xlog";
        public String setConsoleLogOpen = "setConsoleLogOpen";
        public String setLogLevel = "setLogLevel";

        //sql数据库
        public String sqliteDatabaseClass = "com.tencent.mndb.database.SQLiteDatabase";

        //获取发送对象
        public String mmCoreStaticClass = "com.tencent.mn.model.ak";
        public String mmCoreStaticReturnNetSceneSend = "vy";
        public String sendMethod_d = "d";
        public String sendMethod_a = "a";

        //文本消息模型
        public String modelMultiText = "com.tencent.mn.modelmulti.i";

        //加好友模型
        public String modelNetSceneVerifyUser = "com.tencent.mn.pluginsdk.model.m";


        //群发相关
        public String massSendAA = "com.tencent.mn.plugin.masssend.a.a";
        public String massSendAB = "com.tencent.mn.plugin.masssend.a.b";
        public String massSendABMethod = "f";
        public String massSendAF = "com.tencent.mn.plugin.masssend.a.f";//MasSendInfo.fileName is null
        public String[] massSendAAField = new String[]{"dac", "status", "dhH", "dhI", "filename", "hjp", "hjq", "hjr", "msgType", "hjs", "hjt", "aPV", "dhF", "hju", "hjv", "hjw", "cuV", "cuW"};

        //消息模型基类
        public String netSceneBaseClass = "com.tencent.mn.v.k";

        //endregion
    }
}
