package com.yf.userservice.service;

import com.yf.userservice.model.TbUser;

import java.util.Map;

public interface UserService {

    Integer saveUser(TbUser tbUser);


    TbUser findUserById(Long id);


    TbUser  findUserByPhone(Map<String,Object> param);

}
