package com.yanxin.study.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanxin.study.controller.dto.UserDto;
import com.yanxin.study.entity.User;
import com.yanxin.study.mapper.UserMapper;
import com.yanxin.study.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.Queue;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 颜鑫
 * @since 2022-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();
    @Override
    public boolean login(UserDto userDto) {
        //使用mybatisplus对象查询
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        //查询数据库的数据
        userQueryWrapper.eq("username",userDto.getUsername());

        userQueryWrapper.eq("password",userDto.getPassword());



        /*get one 只能返回一条数据,如果多个会有异常*/
        try {
            User one = getOne(userQueryWrapper);

            System.out.println(one);
            return one != null;

        }catch (Exception e)
        {
            //日志
           LOG.error(e);
            return false;

        }




    }
}
