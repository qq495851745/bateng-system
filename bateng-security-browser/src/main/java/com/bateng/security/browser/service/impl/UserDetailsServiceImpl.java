package com.bateng.security.browser.service.impl;

import com.bateng.security.browser.dao.UserDao;
import com.bateng.security.browser.service.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug(username+"         登录验证");
        boolean accountNonLocked = true;
        boolean credentialsNonExpired = true;
        boolean accountNonExpired = true;
        boolean enabled =true;
        logger.info(userDao.toString());

        com.bateng.security.browser.entity.User user
                =userDao.findByUsername(username);


        return new User(username,user.getPassword()
                ,enabled,accountNonExpired
                ,credentialsNonExpired
                ,accountNonLocked
                ,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
