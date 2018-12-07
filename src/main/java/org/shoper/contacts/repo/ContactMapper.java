package org.shoper.contacts.repo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.shoper.contacts.bean.Contact;
import org.shoper.contacts.bean.Phone;

@Mapper
public interface ContactMapper {
    int saveContact(Contact contact);

    int savePhone(Phone phone);

    int saveOtherInfo(@Param("cid") Integer cid, @Param("key") String key, @Param("value") String value);

    Contact findContactByName(@Param("uid") Integer uid, @Param("lastName") String lastName, @Param("firstName") String firstName);

    int deleteContact(@Param("uid") Integer uid, @Param("cid") Integer cid);
}
