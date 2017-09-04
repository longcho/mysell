package cn.longzzai.utils;

import java.util.Random;

/**
 * 生成随机key
 *
 * @author longcho
 * 2017-08-30-18:22
 */
public class KeyUtil {
    public static synchronized String getEasyKey(){
        Random random = new Random();
        int number = random.nextInt(9000000) +1000000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
