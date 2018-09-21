package com.dsp.bootdemo.realm;

import com.dsp.bootdemo.repository.MockUserRepo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomRealm  extends AuthorizingRealm {

    @Autowired
    MockUserRepo mockUserRepo;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Set<String> roleSet  = mockUserRepo.getRolesByUsername(username);
        authorizationInfo.setRoles(roleSet);
        roleSet.forEach(item -> {
            System.out.println("xxxx" + item);
            authorizationInfo.addStringPermission(item);
        });
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String passwd = new String(usernamePasswordToken.getPassword());
        if(mockUserRepo.getPasswdByName(username) == null
                || !mockUserRepo.getPasswdByName(username).equals(passwd)) {
            throw new AccountException("用户名密码不正确");
        }
        return new SimpleAuthenticationInfo(username, passwd,getName());
    }
}
