package org.shoper.contacts.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private UserType type;
}
