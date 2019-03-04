package bitter.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //所有控制器的异常处理方法
public class AppWideExceptionHandler {

    @ExceptionHandler(DuplicateBittleException.class)
    public String duplicateBittleHandler() {
        return "error/duplicate";
    }

//    @ExceptionHandler(BittleNotFoundException.class)

}
