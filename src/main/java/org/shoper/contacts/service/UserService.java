package org.shoper.contacts.service;

import org.shoper.contacts.bean.User;
import org.shoper.contacts.bean.UserInfo;
import org.shoper.contacts.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserInfo userInfo(User user) {
        User userByName = userRepository.findUserByName(user);
        return null;
    }
}
