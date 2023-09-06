package nuc.edu.springmvc.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongjun
 * @create 2020-07-23 14:37
 */
@Data
@AllArgsConstructor //有参构造函数
@NoArgsConstructor //无参构造函数
public class ZdException extends RuntimeException {

    private Integer code; //状态码

    private String msg; //异常信息
}
