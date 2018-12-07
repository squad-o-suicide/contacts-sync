package org.shoper.contacts.repo;

import org.apache.ibatis.annotations.Mapper;
import org.shoper.contacts.bean.User;

@Mapper
public interface UserRepository {
    User findUserByName(User user);
    User findUserByUsernameAndPassword(User user);
    int saveUser(User user);
}
