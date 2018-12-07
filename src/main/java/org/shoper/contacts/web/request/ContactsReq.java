package org.shoper.contacts.web.request;

import lombok.Data;
import org.shoper.contacts.bean.Contact;

@Data
public class ContactsReq {
    private Contact contact;
    //上传凭证.
    private String token;
}
