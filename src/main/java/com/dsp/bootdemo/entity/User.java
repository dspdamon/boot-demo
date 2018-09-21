package com.dsp.bootdemo.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false, unique = true)
        private String userName;
        @Column(nullable = false)
        private String passWord;
        @Column(nullable = false, unique = true)
        private String email;
        @Column(nullable = true, unique = true)
        private String nickName;
        @Column(nullable = false)
        private Long regTime;

    public User() {
    }

    public User(String userName, String passWord, String email, String nickName, long regTime) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.nickName = nickName;
        this.regTime = regTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }
}
