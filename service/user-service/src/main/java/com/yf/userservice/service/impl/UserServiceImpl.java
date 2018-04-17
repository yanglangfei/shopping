package com.yf.userservice.service.impl;

import com.yf.userservice.mapper.TbUserMapper;
import com.yf.userservice.model.TbUser;
import com.yf.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Integer saveUser(TbUser tbUser) {
        return tbUserMapper.save(tbUser);
    }

    @Override
    public TbUser findUserById(Long id) {
        return tbUserMapper.findOne(id);
    }

    @Override
    public TbUser findUserByPhone(Map<String, Object> param) {
        List<TbUser> tbUserList = tbUserMapper.findList(param);
        return !tbUserList.isEmpty() ? tbUserList.get(0) : null;
    }
}
