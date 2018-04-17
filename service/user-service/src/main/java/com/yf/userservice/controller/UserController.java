package com.yf.userservice.controller;

import com.yf.userservice.model.TbUser;
import com.yf.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public  TbUser  saveUser(String name){
        TbUser tbUser=new TbUser();
        tbUser.setId(1L);
        tbUser.setUserName(name);
         userService.saveUser(tbUser);
        return tbUser;
    }

}
