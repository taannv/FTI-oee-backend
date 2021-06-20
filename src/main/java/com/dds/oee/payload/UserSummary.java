package com.dds.oee.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Getter
@Setter
public class UserSummary {

    private Long id;
    private String username;
    private String name;
    private String email;
    private List<String> roles;

    private UserSummary() {
    }

    private UserSummary(Long id, String username, String name, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.roles = roles;
        this.email = email;
    }

    public static UserSummary create(Long id, String username, String name, String email, List<String> roles) {
        return new UserSummary(id, username, name, email, roles);
    }

    public static UserSummary create(Long id, String username, String name, String email) {
        return create(id, username, name, email, new ArrayList<>(0));
    }
}
