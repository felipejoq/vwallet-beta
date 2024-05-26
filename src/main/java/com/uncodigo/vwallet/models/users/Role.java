package com.uncodigo.vwallet.models.users;

import java.util.List;

public class Role {

    private Long id;
    private String name;

    // One-To-Many relationship with User
    private List<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
