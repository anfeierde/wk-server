package com.cz1.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by wkchen on 2017/3/21.
 */
public class Todo {

    @Id
    private String id;

    private String desc;

    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
