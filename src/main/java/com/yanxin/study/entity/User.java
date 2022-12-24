package com.yanxin.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
/*无参构造*/
@NoArgsConstructor
/*有参构造*/
@AllArgsConstructor
/*ToString方法*/
@ToString
/*mybatisplus 必须加上这个注解，才能找到表名 同时主键加上 @ID*/
@TableName(value = "sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /* 和swagger jar包使用
      ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改
       value–字段说明
      name–重写属性名字 ，
     dataType–重写属性类型
     required–是否必填
     example–举例说明
     hidden–隐藏*/
    @ApiModelProperty("id")
    /*@TableId注解
 @TableId注解是专门用在主键上的注解，如果数据库中的主键字段名和实体中的属性名，不一样且不是驼峰之，
  可以在实体中表示主键的属性上加@Tableid注解，
  并指定@Tableid注解的value属性值为表中主键的字段名既可以对应上。*/
    /*mybatis-plus-boot依赖:@TableId*/
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色")
    private String role;

  /*  @TableField(exist = false)
    private List<Course> courses;

    @TableField(exist = false)
    private List<Course> stuCourses;*/


}
