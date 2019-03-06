package bitter.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //所有控制器的异常处理方法
public class AppWideExceptionHandler {

    @ExceptionHandler(DuplicateBittleException.class)
    public String duplicateBittleHandler(Model model) {
        System.out.println("DuplicateBittleException");
        model.addAttribute("errorType","DuplicateBittleException");
        return "error/duplicate";
    }

    @ExceptionHandler(DuplicateBitterException.class)
    public String duplicateBitterHandler(Model model) {
        System.out.println("DuplicateBitterException");
        model.addAttribute("errorType","DuplicateBitterException");
        return "error/duplicate";
    }
//    @ExceptionHandler(BittleNotFoundException.class)

}
