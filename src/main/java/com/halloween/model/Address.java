package com.halloween.model;

import java.io.Serializable;

public class Address implements Serializable {
    private String type;
    private String key;
    private String name;

    public Address() {
    }

    public Address(String type, String key, String name) {
        this.type = type;
        this.key = key;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
