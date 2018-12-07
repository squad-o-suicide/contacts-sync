package org.shoper.contacts.web.api;

import org.shoper.contacts.bean.User;
import org.shoper.contacts.service.UserService;
import org.shoper.commons.responseentity.BaseResponse;
import org.shoper.commons.responseentity.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping({"/user/login"})
    public BaseResponse userLogin(@RequestBody User user) {
        ResponseBuilder custom = ResponseBuilder.custom();
        return custom.data(userService.login(user)).build();
    }

    @PostMapping({"/user/registry"})
    public BaseResponse userRegistry(@RequestBody User user) {
        ResponseBuilder custom = ResponseBuilder.custom();
        return custom.data(userService.userRegistry(user)).build();
    }

    @PostMapping({"/admin/registry"})
    public BaseResponse adminRegistry(@RequestBody User user) {
        ResponseBuilder custom = ResponseBuilder.custom();
        return custom.data(userService.userRegistry(user)).build();
    }

    @PostMapping({"/admin/login"})
    public BaseResponse adminLogin(@RequestBody User user) {
        ResponseBuilder custom = ResponseBuilder.custom();
        return custom.data(userService.login(user)).build();
    }

}
