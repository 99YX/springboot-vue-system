package com.yanxin.study.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanxin.study.common.Constants;
import com.yanxin.study.controller.dto.UserDto;
import com.yanxin.study.entity.User;
import com.yanxin.study.exception.ServiceException;
import com.yanxin.study.mapper.UserMapper;
import com.yanxin.study.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


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
    public UserDto login(UserDto userDto) {
        //使用mybatisplus对象查询
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //查询数据库的数据
        userQueryWrapper.eq("username", userDto.getUsername());

        userQueryWrapper.eq("password", userDto.getPassword());

        User one = null;

        /*get one 只能返回一条数据,如果多个会有异常*/
        try {
            one = getOne(userQueryWrapper);

            System.out.println(one);

        } catch (Exception e) {
            //日志
            LOG.error(e);

            //失败
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }

        //使用hutool工具将从数据库查询的数据(User对象javabean)复制到UserDto

        if (one != null) {
            //true :忽略大小写

            BeanUtil.copyProperties(one, userDto, true);

            return userDto;
        } else {
            //失败
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }


    }

    @Override
    public User register(UserDto userDTO) {
        //使用mybatisplus对象查询
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //查询数据库的数据
        userQueryWrapper.eq("username", userDTO.getUsername());

        userQueryWrapper.eq("password", userDTO.getPassword());

        User one = getOne(userQueryWrapper);

        if(one==null){
            //new 一个user对象将前端传过来的userDTO赋值给user
            one=new User();

            BeanUtil.copyProperties(userDTO ,one, true);

            //插入数据库
            save(one);
            return one;
        }
        else {
             throw new ServiceException(Constants.CODE_600, "用户已经存在");
        }



        }
}
