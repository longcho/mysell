package cn.longzzai.VO;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 *
 * @author longcho
 * 2017-08-26-10:39
 */
public class ResultVO <T> implements Serializable{

    private static final long serialVersionUID = 6050800680212679517L;
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
