package com.dsp.bootdemo.repository;

import com.dsp.bootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
    User findByEmail(String email);
    User findById(long  id);

}
