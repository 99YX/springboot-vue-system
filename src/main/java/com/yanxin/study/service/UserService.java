package com.yanxin.study.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanxin.study.entity.User;
import com.yanxin.study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
/*mybatisplus写法:*/
public class UserService extends ServiceImpl<UserMapper,User> {
    public Boolean saveUser(User user){
        // ServiceImpl 提供的方法  新增或者插入

        if (user.getId()== null ) {
            // 如果前端user对象没有传id字段，则表示是新增
            return save(user);
        }
        else { // 否则为更新
            return updateById(user);
        }


    }




    @Autowired
    private UserMapper userMapper;

   //*事务管理*/
/*

    public int save(User user) {

        if (user.getId()== null ) {
            // 如果前端user对象没有传id字段，则表示是新增
            return userMapper.insert(user);
        }
        else { // 否则为更新
          return userMapper.update(user);
        }


    }
*/


    public List<User> findAll(String username) {
       return userMapper.findAll(username);
    }
/*
    public Integer removeById(Integer id) {

        return userMapper.removeById(id);
    }*/

    public List<User> selectPage(Integer pageNum, Integer pageSize ,String username) {
        return userMapper.selectPage(pageNum,pageSize,username);
    }
}
