package com.cwc.javastudenthealth.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cwc.javastudenthealth.entity.Student;
import com.cwc.javastudenthealth.entity.User;
import com.cwc.javastudenthealth.mapper.StudentMapper;
import com.cwc.javastudenthealth.mapper.UserMapper;
import com.cwc.javastudenthealth.service.LoginService;
import com.cwc.javastudenthealth.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username)
                .eq("password", password)
        );

        if (!ObjectUtils.isEmpty(user)) {
            return JwtUtils.createToken(user);
        }
        return null;
    }

    @Override
    public User getUserInfo(String username, String password) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username)
                .eq("password", password)
        );
    }
}
