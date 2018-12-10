package org.shoper.contacts.bean;

import lombok.Data;

import java.util.List;

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
    private List<Phone> phone;
    //其他信息
    List<OtherInfo> others;
    private Integer uid;
    private String token;
}
