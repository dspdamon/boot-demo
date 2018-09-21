package com.dsp.bootdemo.controller;

import com.dsp.bootdemo.entity.User;
import com.dsp.bootdemo.model.Response;
import com.dsp.bootdemo.model.RspData;
import com.dsp.bootdemo.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebController {

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    @RequiresRoles("admin")
    public Response index() {
        return new Response(0,"this is admin test");
    }

    @GetMapping("/guest")
    @RequiresRoles("guest")
    public Response index2() {
        System.out.println(SecurityUtils.getSubject().getSession().getAttribute("currentUserId"));
        return new Response(0,"this is guest test");
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/sign-up",method = RequestMethod.POST)
    public Response createUser(@RequestBody User user) {
        User checkUser = userRepository.findByUserName(user.getUserName());
        if(checkUser != null) {
            return new Response(0001,"username already exists.");
        }
        long curTime = System.currentTimeMillis();
        user.setRegTime(curTime);
        userRepository.save(user);
        return new Response(0, "user register success");
    }

    @GetMapping("/login")
    public String login() {
        return "/login.html";
    }

    @GetMapping("/check-login/{username}")
    public Response checkLogin(@PathVariable String username) {
        Subject subject = SecurityUtils.getSubject();
        boolean isAuthenticated = subject.isAuthenticated();
        return new Response(111,String.format("isAuthenticated is %s", isAuthenticated));
    }
    /*
    出于简单前台使用的是form表单提交形式，所以User没有使用@RequestBody注解
     */
    @RequestMapping(value = "/sign-in", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Response signIn(User user) {
        Subject subject = SecurityUtils.getSubject();
        String username = user.getUserName();
        String pwd = user.getPassWord();
        logger.info("xxxxx username {}, passwd {}",username, pwd);
        UsernamePasswordToken token  = new UsernamePasswordToken(username,pwd);
        subject.login(token);
        subject.getSession().setAttribute("user", username);
        return new Response(000, "登陆成功！！！");
    }
    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    public RspData listUserInfo(@PathVariable long userId) {
        String username = (String)SecurityUtils.getSubject().getSession().getAttribute("user");
        logger.info("username is {}",username);
        User user = userRepository.findByUserName(username);
        if(user == null) {
            return new RspData(0001, "user not exist", null);
        }
        user.setPassWord("****");
        return new RspData(0,"info",user);
    }
}
