package cn.longzzai.utils;

import cn.longzzai.enums.CodeEnum;

/**
 * @author longcho
 * 2017-09-07-20:34
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code ,Class<T> enumClass){
        for (T t : enumClass.getEnumConstants()) {
            if (t.getCode() == code ){
                return t;
            }
        }
        return null;
    }
}
