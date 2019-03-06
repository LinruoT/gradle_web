package bitter.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //所有控制器的异常处理方法
public class AppWideExceptionHandler {

    //处理数据库存储异常
    @ExceptionHandler(DuplicateBittleException.class)
    public ModelAndView duplicateBittleHandler() {
        System.out.println("DuplicateBittleException");
        ModelAndView modelAndView = new ModelAndView("error/duplicate");
        modelAndView.addObject("errorType","DuplicateBittleException");
        return modelAndView;
    }

    @ExceptionHandler(DuplicateBitterException.class)
    public ModelAndView duplicateBitterHandler() {
        System.out.println("DuplicateBitterException");
        ModelAndView modelAndView = new ModelAndView("error/duplicate");
        modelAndView.addObject("errorType","DuplicateBitterException");
        return modelAndView;
    }
//    @ExceptionHandler(BittleNotFoundException.class)

}
