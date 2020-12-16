package com.bateng.security.browser.dao.repository;


import com.bateng.security.browser.entity.PageVo;
import com.bateng.security.browser.entity.User;

public interface UserRepository {

    public PageVo<User> findUserByPage(PageVo<User> pageVo, User user);
}
