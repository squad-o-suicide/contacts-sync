package org.shoper.contacts.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class Cantact {
    private String name;
    private List<Phone> phones;
    Map<String, String> others;
}
