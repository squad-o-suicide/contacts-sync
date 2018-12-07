package org.shoper.contacts.service;

import org.shoper.contacts.bean.Contact;
import org.shoper.contacts.bean.OtherInfo;
import org.shoper.contacts.bean.Phone;
import org.shoper.contacts.bean.User;
import org.shoper.contacts.repo.ContactMapper;
import org.shoper.contacts.web.request.ContactsReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactMapper contactMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public void uploadContact(User user, ContactsReq contactsReq) {
        Contact contact = contactsReq.getContact();
        Contact contactInDb = contactMapper.findContactByName(user.getId(), contact.getLastName(), contact.getFirstName());
        if (contactInDb.getToken().equals(contact.getToken())) {
            //如果与之前相等那么，使用上传数据。
            //那么删除云数据,保存上传数据
            contactMapper.deleteContact(user.getId(), contactInDb.getId());
            contactMapper.deletePhone(contactInDb.getId());
            contactMapper.deleteOther(contactInDb.getId());
            if (contactMapper.saveContact(contact) == 1) {
                //保存电话信息以及其他信息
                List<Phone> phones = contact.getPhones();
                for (Phone phone : phones) {
                    phone.setCid(contact.getId());
                    contactMapper.savePhone(phone);
                }
                //保存其他信息
                List<OtherInfo> others = contact.getOthers();
                for (OtherInfo other : others) {
                    contactMapper.saveOtherInfo(contact.getId(), other.getName(), other.getValue());
                }
            } else
                throw new RuntimeException("上传联系人失败.");
        } else {
            //使用云数据
            //do noting
        }
    }

    public List<Contact> getContactByUser(User user) {
        List<Contact> contactByUser = contactMapper.getContactByUser(user);
        for (Contact contact : contactByUser) {
            contact.setPhones(contactMapper.findPhoneByUserAndContact(contact.getId()));
            contact.setOthers(contactMapper.findOtherByUserAndContact(contact.getId()));
        }
        return contactByUser;
    }
}
