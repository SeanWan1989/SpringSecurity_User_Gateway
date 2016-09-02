package com.xunhui.gateway.service.user;


import com.xunhui.gateway.dao.UserDao;
import com.xunhui.gateway.domain.Users;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sean.W on 2016/8/28.
 */
@Service
public class UserAccountService {
    @Autowired
    private UserDao dao;

    public LoginUserDetailsImpl validate(String current_username,String password){
        String username = current_username;
        //先通过用户名去查找
        Users user = dao.findByUserName(username);
        if(user==null){
            //通过email去查找
            user = dao.findByEmail(current_username);
            if(user!=null){
                username = user.getUserName();
            }else{
                //通过其他.....
            }
        }
        System.out.println("user = " + user);
        //比较密码是否匹配
        String raw_password = DigestUtils.sha256Hex(user.getNumber()+password);
        System.out.println("raw_password = " + raw_password);
        if(raw_password.equals(user.getPassword())){
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            LoginUserDetailsImpl loginUserDetails = new LoginUserDetailsImpl(username,password,authorities);
            return loginUserDetails;
        }
        return null;
    }
}
