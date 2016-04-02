package com.smapley.neibu.util;


import android.text.format.Time;

/**
 * Created by smapley on 2015/5/20.
 */
public class MyData {

    public static String UserName;
    public static String PassWord;
    public static boolean Login = false;

    private static final String BASE_URL = "http://120.25.208.188/neibu/";

    public final static String URL_reg=BASE_URL+"reg.php";
    public final static String URL_reg1=BASE_URL+"reg1.php";
    public final static String URL_regzhuce = BASE_URL + "regzhuce.php";
    public final static String URL_getGzhanghao = BASE_URL + "getGzhanghao.php";
    public final static String URL_getMingxi = BASE_URL + "getMingxi.php";

    public final static String URL_getDysz = BASE_URL + "getZiliao.php";
    public final static String URL_updateDysz = BASE_URL + "updateZiliao1.php";
    public final static String URL_reg2 = BASE_URL + "reg2.php";
    public final static String URL_gengxin = BASE_URL + "gengxin.php";
    public final static String URL_reggaimi = BASE_URL + "reggaimi.php";
    public final static String URL_xiazai = "http://www.newera98.com/guanliduan.apk";

    /**
     * 获取服务器加密码
     * key
     *
     * @return
     */
    public static int getKey() {
        int key = 0;
        key = 1 + (int) (Math.random() * 999);
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int date = t.monthDay;
        return key * 789 * date;
    }


}
