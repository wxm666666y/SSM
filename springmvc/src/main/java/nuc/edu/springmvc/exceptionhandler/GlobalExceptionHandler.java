package nuc.edu.springmvc.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import nuc.edu.springmvc.util.Res;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hongjun
 * @create 2020-07-23 14:15
 *
 * @ControllerAdvice是在类上声明的注解，其用法主要有三点：
 *
 * @ExceptionHandler注解标注的方法：用于捕获Controller中抛出的不同类型的异常，从而达到异常全局处理的目的；
 * @InitBinder注解标注的方法：用于请求中注册自定义参数的解析，从而达到自定义请求参数格式的目的；
 * @ModelAttribute注解标注的方法：表示此方法会在执行目标Controller方法之前执行 。
 *
 */

@ControllerAdvice
//全局一场处理
@Slf4j
public class GlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回json数据
    public Res error(Exception e){
        e.printStackTrace();
        return Res.error().message("执行了全局异常处理...");
    }
    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //返回json数据
    public Res error(ArithmeticException e){
        e.printStackTrace();
        return Res.error().message("执行了特定异常处理...");
    }
    //自定义异常
    @ExceptionHandler(ZdException.class)
    @ResponseBody //返回json数据
    public Res error(ZdException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Res.error().code(e.getCode()).message(e.getMsg());
    }
}
