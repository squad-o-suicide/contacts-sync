package org.shoper.contacts.service;

import org.shoper.contacts.bean.Contact;
import org.shoper.contacts.bean.Phone;
import org.shoper.contacts.bean.User;
import org.shoper.contacts.repo.ContactMapper;
import org.shoper.contacts.web.request.ContactsReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ContactService {
    @Autowired
    ContactMapper contactMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public void uploadContact(User user,ContactsReq contactsReq) {
        Contact contact = contactsReq.getContact();
        Contact contactInDb = contactMapper.findContactByName(user.getId(), contact.getLastName(), contact.getFirstName());
        if(contactInDb.getToken().equals(contact.getToken())){
            //如果与之前相等那么，使用上传数据。
            //那么删除本地云数据,更新上传数据
            contactMapper.deleteContact(user.getId(),contactInDb.getId());

        }else{
            //使用云数据
        }
        if (contactMapper.saveContact(contact) == 1) {
            //保存电话信息以及其他信息
            List<Phone> phones = contact.getPhones();
            for (Phone phone : phones) {
                phone.setCid(contact.getId());
                contactMapper.savePhone(phone);
            }
            //保存其他信息
            Map<String, String> others = contact.getOthers();
            for (String key : others.keySet()) {
                contactMapper.saveOtherInfo(contact.getId(), key, others.get(key));
            }
        } else
            throw new RuntimeException("上传联系人失败.");
//
    }
}
