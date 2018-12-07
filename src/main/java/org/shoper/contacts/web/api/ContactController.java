package org.shoper.contacts.web.api;

import org.shoper.commons.responseentity.BaseResponse;
import org.shoper.commons.responseentity.ResponseBuilder;
import org.shoper.contacts.Authrized;
import org.shoper.contacts.bean.User;
import org.shoper.contacts.bean.UserType;
import org.shoper.contacts.filter.UploadToken;
import org.shoper.contacts.service.ContactService;
import org.shoper.contacts.web.request.ContactsReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
public class ContactController {
    @Autowired
    ContactService contactService;
    @Autowired
    UploadToken uploadToken;

    @Authrized(UserType.USER)
    @PostMapping("/uploadContact")
    public BaseResponse uploadContact(@SessionAttribute(value = "userInfo", required = false) User user, @RequestBody ContactsReq contactsReq) {
        ResponseBuilder custom = ResponseBuilder.custom();
        if (StringUtils.isEmpty(contactsReq.getToken()) || uploadToken.checkToken(user.getToken(), contactsReq.getToken())) {
            contactService.uploadContact(user,contactsReq);
        } else {
            throw new RuntimeException("token error.");
        }
        return custom.build();
    }

    @Authrized(UserType.USER)
    @PostMapping("/getUploadToken")
    public BaseResponse getUploadToken(@SessionAttribute(value = "userInfo", required = false) User user) {
        return ResponseBuilder.custom().data(uploadToken.createToken(user.getToken())).build();
    }

}
