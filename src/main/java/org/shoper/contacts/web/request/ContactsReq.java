package org.shoper.contacts.web.request;

import lombok.Data;
import org.shoper.contacts.bean.Cantact;

import java.util.List;

@Data
public class ContactsReq {
    private List<Cantact> cantacts;
}
