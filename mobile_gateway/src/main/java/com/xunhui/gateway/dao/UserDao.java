package com.xunhui.gateway.dao;

import com.xunhui.gateway.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<Users, String>{
    public Users findByUserName(String userName);
    public Users findByEmail(String email);
}
