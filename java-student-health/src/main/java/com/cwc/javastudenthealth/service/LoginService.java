package com.cwc.javastudenthealth.service;

import com.cwc.javastudenthealth.entity.User;

public interface LoginService {
    String login(String username, String password);

    User getUserInfo(String username, String password);
}
