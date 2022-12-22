package com.yanxin.study.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanxin.study.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.yanxin.study.service.IUserService;

import com.yanxin.study.entity.User;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 颜鑫
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/User")
public class UserController {


    @Autowired
    private IUserService userService;

    /*学习git*/
    //数据库插入操作
    // 新增或者更新


    @PostMapping("/user")
    /*@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；GET方式无请求体，所以使用@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交。在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用，@RequestBody最多只能有一个，而@RequestParam()可以有多个。*/

    public Result save(@RequestBody User user) {
      userService.saveOrUpdate(user);
        return Result.success();
        }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.removeById(id);
            return Result.success();
            }


    @PostMapping("/batch/delete")

    public Result deleteBatch(@RequestBody List<Integer> ids) {

    userService.removeByIds(ids);
            return Result.success();
            }

    @GetMapping
    public Result findAll() {
            return Result.success(userService.list());
            }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
            return Result.success(userService.getById(id));
            }

    @GetMapping("/page")
    public IPage<User>findPage(@RequestParam (defaultValue ="") Integer pageNum,  @RequestParam (defaultValue ="")Integer pageSize, @RequestParam (defaultValue ="") String username,@RequestParam (defaultValue ="") String email,@RequestParam (defaultValue ="") String address) {

        //
            IPage<User> page=new Page<>(pageNum,pageSize);

            QueryWrapper<User> queryWrapper = new QueryWrapper<>();



            //只要不为空就进行判断
            if (!"".equals(username)) {
                queryWrapper.like("username", username);
            }
            if (!"".equals(email)) {
                queryWrapper.like("email", email);
            }
            if (!"".equals(address)) {
                queryWrapper.like("address", address);
            }


        queryWrapper.orderByDesc("id");
            return userService.page(new Page<>(pageNum, pageSize), queryWrapper);
                    //return Result.success(userService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }





}


