package com.yanxin.study.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanxin.study.entity.User;
import com.yanxin.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
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
    public boolean save(@RequestBody User user) {
        /*@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；GET方式无请求体，所以使用@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交。在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用，@RequestBody最多只能有一个，而@RequestParam()可以有多个。*/
        return userService.saveUser(user);
    }


    @GetMapping("/select")
    public List<User> setTest(String username) {
        return userService.list();
//        return  userService.findAll(username);

    }

    /*批量删除的接口*/

    @PostMapping("/batch/delete")
    /*批量删除的id 使用list集合进行接受*/
    public Boolean BatchDelete(@RequestBody List<Integer> ids) {

        /*多个id的值,通过removeByIds 进行删除*/
        return userService.removeByIds(ids);

        //  返回Integer
//        return userService.removeById(id);
    }


    /*动态绑定 /{id}和参数id要保持一致*/
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {

        return userService.removeById(id);

        //  返回Integer
//        return userService.removeById(id);
    }

    //分页 http://localhost:8083/User/page?pageNum=1&pageSize=3
    @GetMapping("/pages")
    public Map<String, Object> findPageOne(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username) {

        //模糊查询添加%%

        username = "%" + username + "%";

        //查询总条数
        List<User> all = userService.findAll(username);
        Integer total = 0;
        for (int i = 0; i < all.size(); i++) {

            total++;

        }
        System.out.println("页数=" + total);

        //分页公式
        pageNum = (pageNum - 1) * pageSize;


        List<User> data = userService.selectPages(pageNum, pageSize, username);

        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);

        System.out.println("<---------分隔符-------->" + res);
        return res;


    }


    //分页查询使用mybatisplus方式
    /*@RequestParam (defaultValue ="") 默认值*/
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam(defaultValue = "") Integer pageNum, @RequestParam(defaultValue = "") Integer pageSize, @RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String email, @RequestParam(defaultValue = "") String address) {


        //
        IPage<User> page = new Page<>(pageNum, pageSize);
        //
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

        //根据id倒序

        /*  queryWrapper.orderByDesc("id");*/

        return userService.page(page, queryWrapper);


    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("avatarUrl", "头像");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
     * excel 导入
     *
     * @param file
     * @throws Exception
     */
    @PostMapping("")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            user.setAvatarUrl(row.get(6).toString());
            users.add(user);
        }

        boolean b = userService.saveBatch(users);
        // return Result.success(true);

        return b;


    }

}

