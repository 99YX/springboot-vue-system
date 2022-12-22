package com.yanxin.study.service.impl;

import com.yanxin.study.entity.User;
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

}
