package cn.longzzai.exception;

import cn.longzzai.enums.ResultEnum;

/**
 * 自定义买卖异常
 *
 * @author longcho
 * 2017-08-30-17:58
 */
public class SellException extends RuntimeException{
    private int code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
