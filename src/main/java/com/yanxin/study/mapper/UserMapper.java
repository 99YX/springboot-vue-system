package com.yanxin.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanxin.study.entity.User;
import org.apache.ibatis.annotations.*;


import java.util.List;
/*@Mapper*/
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from sys_user where username like #{username}")
    //查询数据库当中user表信息
    List <User> findAll(String username);
    //从user表插入数据
    @Insert("INSERT into sys_user(username, password,nickname,email,phone,address) VALUES (#{username}, #{password}," +
            " #{nickname}, #{email},#{phone}, #{address})")
    int  insert(User user);

    //更新数据
    int update(User user);

    //删除操作 http://localhost:8083/User/41
    @Delete("delete from sys_user where id=#{id};")
    Integer removeById(@Param("id") Integer id);

    //分页查询
    @Select("select * from sys_user where username like #{username} limit #{pageNum},#{pageSize};")

    List<User> selectPage(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize,@Param("username")String username);
}
