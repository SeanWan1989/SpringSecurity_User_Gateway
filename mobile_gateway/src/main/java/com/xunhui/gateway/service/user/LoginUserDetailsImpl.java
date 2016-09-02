package com.xunhui.gateway.service.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sean.W on 2016/8/28.
 */
public class LoginUserDetailsImpl extends User implements UserDetails
{
    /**
     *
     */
    private static final long serialVersionUID = -5424897749887458053L;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */
    public LoginUserDetailsImpl(String username, String password, boolean enabled, boolean accountNonExpired,
                                boolean credentialsNonExpired, boolean accountNonLocked,
                                Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    /**
     * @param username
     * @param password
     * @param authorities
     */
    public LoginUserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, authorities);
    }

    /**
     * @param username
     * @param password
     * @param authorities
     */
    public LoginUserDetailsImpl(String username, String password)
    {
        super(username, password, new ArrayList<GrantedAuthority>());
    }

    /**
     * getter method
     * @see LoginUserDetailsImpl#mail
     * @return the mail
     */
    public String getMail()
    {
        return mail;
    }

    /**
     * setter method
     * @see LoginUserDetailsImpl#mail
     * @param mail the mail to set
     */
    public void setMail(String mail)
    {
        this.mail = mail;
    }

    @Override
    public String toString()
    {
        return super.toString() + "; Mail: " + mail;
    }

}
