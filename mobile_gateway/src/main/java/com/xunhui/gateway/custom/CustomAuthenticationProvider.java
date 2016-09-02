package com.xunhui.gateway.custom;


import com.xunhui.gateway.service.user.LoginUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private LoginUserDetailsServiceImpl userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        //如果想做点额外的检查,可以在这个方法里处理,校验不通时,直接抛异常即可
        System.out
                .println("CustomAuthenticationProvider.additionalAuthenticationChecks() is called!");
    }

    @Override
    protected UserDetails retrieveUser(String username,
                                       UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails loadedUser;

        try
        {
            String password = (String) authentication.getCredentials();
            loadedUser = userDetailsService.loadUserByUsername(username, password);
        }
        catch (UsernameNotFoundException notFound)
        {
            throw notFound;
        }
        catch (Exception repositoryProblem)
        {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null)
        {
            throw new AuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;

    }

}