package com.dsp.bootdemo.repository;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
使用该类来模拟数据库DAO
 */
@Component
public class MockUserRepo {

    public String getPasswdByName(String username) {
        switch (username) {
            case "zhangsan":
                return "zhangsan";
            case "lisi":
                return "lisi";
        }
        return null;
    }

    public Set<String> getRolesByUsername(String username) {
        Set<String> roles = new HashSet<>();
        switch (username) {
            case "zhangsan":
                roles.add("admin");
                break;
            case "lisi" :
                roles.add("guest");
                break;
        }
        return roles;
    }

    public Set<String> getPermissionByRole(String role) {
        Set<String> permission = new HashSet<>();
        switch (role) {
            case "admin":
                permission.add("read");
                permission.add("write");
                break;
            case "guest" :
                permission.add("read");
                break;
        }
        return permission;
    }
}
