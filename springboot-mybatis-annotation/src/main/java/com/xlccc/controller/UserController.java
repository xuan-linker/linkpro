package com.xlccc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xlccc.mapper.UserMapper;
import com.xlccc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Linker
 * @Date 2020/1/15 3:55 PM
 **/
@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/listUsers")

    public String listAllUsers() {
        List<User> users = userMapper.findAll();
        return users.toString();
    }

    @RequestMapping("/addUser")
    public String addUser(User u) throws Exception {
        userMapper.save(u);
        return "redirect:listUsers";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(User c) throws Exception {
        userMapper.delete(c.getId());
        return "redirect:listUsers";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User c) throws Exception {
        userMapper.update(c);
        return "redirect:listUsers";
    }

    @RequestMapping("/editUser")
    public String listUser(int id) throws Exception {
        User u = userMapper.get(id);
        return u.toString();
    }

    @RequestMapping("/listUser")
    public String listUser(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start, size, "id desc");
        List<User> cs = userMapper.findAll();
        PageInfo<User> page = new PageInfo<>(cs);
        return page.toString();
    }


}
