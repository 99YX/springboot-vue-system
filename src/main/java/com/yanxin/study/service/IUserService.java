package com.yanxin.study.service;

import com.yanxin.study.controller.dto.UserDto;
import com.yanxin.study.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 颜鑫
 * @since 2022-12-22
 */
public interface IUserService extends IService<User> {

    boolean login(UserDto userDto);
}
