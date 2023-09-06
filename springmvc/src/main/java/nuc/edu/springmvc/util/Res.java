package nuc.edu.springmvc.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hongjun
 * @create 2020-07-22 11:20
 */
//统一返回结果类
@Data
public class Res<T> {
/* @ApiModelProperty(value = "是否成功")
 private boolean success;*/

 @ApiModelProperty(value = "返回码")
 private Integer code;
 @ApiModelProperty(value = "数据长度")
 private long count;
 @ApiModelProperty(value = "返回消息")
 private String msg;
 @ApiModelProperty(value = "返回数据")
 private List<T> data;
public static Res ok(){
    Res res =new Res();
    res.setCode(ResultCode.SUCCESS);
    res.setMsg("ok");
    return res;
}
    public static Res error(){
        Res res =new Res();
        res.setCode(ResultCode.ERROR);
        res.setMsg("error");
        return res;
    }
   /* public Res success(Boolean success){
        this.setSuccess(success);
        return this;
    }*/

    public Res message(String message){
        this.setMsg(message);
        return this;
    }

    public Res code(Integer code){
        this.setCode(code);
        return this;
    }

    public Res count(Integer count){
        this.setCount(count);
        return this;
    }

  /*  public Res data(String key, Object value){
        this.data.put(key, value);
        return this;
    }*/

   /* public Res data(Map<String, Object> map){
        this.setData(map);
        return this;
    }*/
    public Res data(List<T> list){
        this.setData(list);
        return this;
    }

}
