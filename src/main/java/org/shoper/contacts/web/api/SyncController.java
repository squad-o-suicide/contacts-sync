package org.shoper.contacts.web.api;

import org.shoper.commons.responseentity.BaseResponse;
import org.shoper.commons.responseentity.ResponseBuilder;
import org.shoper.contacts.bean.User;
import org.shoper.contacts.web.request.ContactsReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
public class SyncController {
    public BaseResponse updateController(@SessionAttribute("userInfo") User user, @RequestBody ContactsReq contactsReq) {
        ResponseBuilder custom = ResponseBuilder.custom();
        return custom.build();
    }
}
