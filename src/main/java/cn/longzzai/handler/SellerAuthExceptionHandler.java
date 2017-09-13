package cn.longzzai.handler;

import cn.longzzai.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author longcho
 * 2017-09-11-9:02
 */
@ControllerAdvice
public class SellerAuthExceptionHandler {

    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handAuth(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/common/login");
        return mv;
    }
}
