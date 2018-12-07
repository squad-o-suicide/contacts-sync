package org.shoper.contacts.repo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.shoper.contacts.bean.Contact;
import org.shoper.contacts.bean.OtherInfo;
import org.shoper.contacts.bean.Phone;
import org.shoper.contacts.bean.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContactMapper {
    int saveContact(Contact contact);

    int savePhone(Phone phone);

    int saveOtherInfo(@Param("cid") Integer cid, @Param("key") String key, @Param("value") String value);

    Contact findContactByName(@Param("uid") Integer uid, @Param("lastName") String lastName, @Param("firstName") String firstName);

    int deleteContact(@Param("uid") Integer uid, @Param("cid") Integer cid);

    int deletePhone(@Param("cid") Integer cid);

    int deleteOther(@Param("cid") Integer id);

    List<Contact> getContactByUser(User user);

    List<Phone> findPhoneByUserAndContact(@Param("cid") Integer cid);

    List<OtherInfo> findOtherByUserAndContact(@Param("cid") Integer cid);
}
