package com.dsp.bootdemo.repository;


import com.dsp.bootdemo.entity.User;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        userRepository.save(new User("aa", "aa", "aa@126.com", "aa123456",System.currentTimeMillis()));

    }

    @Test
    public void readTest() throws Exception {
        Gson gson = new Gson();
        System.out.println("xxxxx" + gson.toJson(userRepository.findByUserName("aa")));
        Assert.assertEquals("aa@126.com",userRepository.findByUserName("aa").getEmail());
        Assert.assertEquals("aa@126.com",userRepository.findByUserNameOrEmail("","aa@126.com").getEmail());
        System.out.println("xxxxx" + gson.toJson(userRepository.findByUserNameOrEmail("","aa@126.com")));
    }

    @Test
    public void insertUpdateTest() throws Exception {
        User user = userRepository.findByUserName("aa");
        if(user != null ) {
            user.setEmail("aaupdate@126.com");
            userRepository.save(user);
        }

    }
}
