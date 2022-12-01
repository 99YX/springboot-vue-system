package com.yanxin.study.controller;

import com.yanxin.study.entity.User;
import com.yanxin.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    /*学习git*/
    //数据库插入操作
    // 新增或者更新
    @PostMapping("/user")
    /*将前台传过来的数据进行处理*/
    public  boolean save(@RequestBody User user) {
      /*@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；GET方式无请求体，所以使用@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交。在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用，@RequestBody最多只能有一个，而@RequestParam()可以有多个。*/
        return userService.saveUser(user);
    }


    @GetMapping("/select")
    public List<User> setTest(String username)
    {
        return userService.list();
//        return  userService.findAll(username);

    }

    /*动态绑定 /{id}和参数id要保持一致*/
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {

        return userService.removeById(id);

        //  返回Integer
//        return userService.removeById(id);
    }

    //分页 http://localhost:8083/User/page?pageNum=1&pageSize=3
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,  @RequestParam Integer pageSize, @RequestParam String username) {

        //模糊查询添加%%

        username = "%" + username + "%";

        //查询总条数
        List<User> all = userService.findAll(username);
        Integer total=0;
        for (int i = 0; i <all.size() ; i++) {

            total++;

        }
        System.out.println("页数="+total);

        //分页公式
        pageNum = (pageNum - 1) * pageSize;


        List<User> data = userService.selectPage(pageNum, pageSize, username);

        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);

        System.out.println("<---------分隔符-------->"+res);
        return res;


    }


    //分页查询使用mybatisplus方式

}
