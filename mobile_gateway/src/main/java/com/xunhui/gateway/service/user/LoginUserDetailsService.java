package com.xunhui.gateway.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Sean.W on 2016/8/28.
 */
public interface LoginUserDetailsService {
    UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException;
}
