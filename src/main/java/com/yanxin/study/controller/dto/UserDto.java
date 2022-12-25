package com.yanxin.study.controller.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author ：颜鑫
 * @Description ：user接收对象的javabean
 * @Date ：2022/12/24 18:52
 * @Param
 * @return
 **/
@Data

/*mybatisplus 必须加上这个注解，才能找到表名 同时主键加上 @ID*/

public class UserDto {
    private static final long serialVersionUID = 1L;

    /* 和swagger jar包使用
      ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改
       value–字段说明
      name–重写属性名字 ，
     dataType–重写属性类型
     required–是否必填
     example–举例说明
     hidden–隐藏*/


    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
    private String role;

}
