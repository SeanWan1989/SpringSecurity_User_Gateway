package com.xunhui.gateway.service.user;

import com.xunhui.gateway.MobileGatewayApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Sean.W on 2016/8/28.
 */
@Service
public class LoginUserDetailsServiceImpl implements LoginUserDetailsService
{

    /**
     * 功能描述：查找登录的用户
     */
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException
    {
        LoginUserDetailsImpl user = MobileGatewayApplication.userAccountService.validate(username, password);
        return user;
    }

}
