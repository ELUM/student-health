package com.cwc.javastudenthealth.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.User;
import com.cwc.javastudenthealth.mapper.UserMapper;
import com.cwc.javastudenthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Page<User> getUsers(int pageNum, int pageSize, User user) {
        Page<User> page = new Page<>(pageNum, pageSize);
        if (!ObjectUtils.isEmpty(user)) {
            return userMapper.selectPage(page, new QueryWrapper<User>()
                    .like("username", user.getUsername())
            );
        }
        return userMapper.selectPage(page, null);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }
}
