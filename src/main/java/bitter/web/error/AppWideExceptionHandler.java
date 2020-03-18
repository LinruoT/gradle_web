package bitter.web.error;

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

    @ExceptionHandler(ImageUploadException.class)
    public ModelAndView ImageUploadHandler() {
        System.out.println("ImageUploadException");
        ModelAndView modelAndView = new ModelAndView("error/duplicate");
        modelAndView.addObject("errorType","ImageUploadException");
        return modelAndView;
    }

    @ExceptionHandler(BitterNotFoundException.class)
    public ModelAndView BitterNotFoundHandler() {
        System.out.println("BitterNotFoundException");
        ModelAndView modelAndView = new ModelAndView("error/notFound");
        modelAndView.addObject("errorType","BitterNotFoundException");
        return modelAndView;
    }

    @ExceptionHandler(BittleNotFoundException.class)
    public ModelAndView BittleNotFoundHandler() {
        System.out.println("BittleNotFoundException");
        ModelAndView modelAndView = new ModelAndView("error/notFound");
        modelAndView.addObject("errorType","BittleNotFoundException");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView OtherExceptionHandler(Exception e) {
        System.out.println("OtherException: "+e.getClass().getName());
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("error/other");
        modelAndView.addObject("errorType",e.getClass().getName());
        return modelAndView;
    }
//    @ExceptionHandler(BittleNotFoundException.class)

}
