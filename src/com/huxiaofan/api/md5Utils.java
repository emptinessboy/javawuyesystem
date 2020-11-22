package com.huxiaofan.api;

import java.security.MessageDigest;

/**
 * 这个md5我真的不会，只能从网上找了一个现成的
 * 来源地址
 * https://blog.csdn.net/junmoxi/article/details/80841555
 *
 * MD5加密工具类
 * @author pibigstar
 */
public class md5Utils {
    //盐，用于混交md5
    private static final String salt = "huxiaofan";
    public static String newMD5(String dataStr) {
        try {
            dataStr = salt + dataStr;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
