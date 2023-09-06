package nuc.edu.springmvc.entity;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * 中北大学软件学院王袭明版权声明(c) 2022/10/16
 */
@Data
public class Books {
    @NotNull(message = "用户id不能为空！")
    private Integer id;

    private String name;
    private Double price;

}
