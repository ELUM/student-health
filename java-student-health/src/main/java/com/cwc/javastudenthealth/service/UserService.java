package com.cwc.javastudenthealth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cwc.javastudenthealth.entity.User;

public interface UserService {
    int addUser(User user);

    Page<User> getUsers(int pageNum, int pageNumber, User user);

    int updateUser(User user);

    int deleteUserById(Integer id);

}
