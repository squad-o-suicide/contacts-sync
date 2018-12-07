package org.shoper.contacts.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Contact {
    private Integer id;
    //头像
    private String avatar;
    //姓氏
    private String lastName;
    //名
    private String firstName;
    //电话
    private List<Phone> phones;
    //其他信息
    Map<String, String> others;
    private Integer uid;
    private Boolean delete;
    private String token;
}
