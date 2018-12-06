package org.shoper.contacts.service;

import org.shoper.contacts.bean.User;
import org.shoper.contacts.filter.UserSession;
import org.shoper.contacts.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSession userSession;

    public User userInfo(User user) {
        User userByName = userRepository.findUserByName(user);
        return userByName;
    }

    public User login(User user) {
        User userByUsernameAndPassword = userRepository.findUserByUsernameAndPassword(user);
        if (Objects.isNull(userByUsernameAndPassword)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        userByUsernameAndPassword.setToken(token);
        userSession.setUserSession(token, userByUsernameAndPassword);
        return userByUsernameAndPassword;
    }
}
