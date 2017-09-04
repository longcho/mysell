package cn.longzzai.utils;

import cn.longzzai.VO.ResultVO;
import cn.longzzai.enums.ResultEnum;

/**
 * 返回的最外层快捷工具
 *
 * @author longcho
 * 2017-08-26-16:52
 */
public class ResultVOUtil{

    public static <T> ResultVO<T> success(T data){
        ResultVO resultVO = new ResultVO<T>();
        resultVO.setData(data);
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    /**
     *
     * @param code 错误码
     * @param msg 提示信息
     * @return
     */
    public static ResultVO error(int code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    /**
     *
     * @return 默认返回code:1 ,msg:error
     */
    public static ResultVO error(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.ERROR.getCode());
        resultVO.setMsg(ResultEnum.ERROR.getMsg());
        return resultVO;
    }
}
