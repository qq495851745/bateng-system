package com.bateng.security.browser.service;


import com.bateng.security.browser.entity.PageVo;
import com.bateng.security.browser.entity.User;

import java.util.List;

public interface UserBiz {
    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);



    public User checkUser(User user);

    public User getUserById(int id);

    public List<User> findUserByName(User user);

    public User findUserByUsername(String username);

    public PageVo<User> findUserByPage(PageVo<User> pageVo, User user);

    public List<User> findAll();

    /**
     * 做删除操作
     * @param id
     */
    public void deleteUserById(int id);

    /**
     * 更新用户
     * @param user
     */
    public void updateUser(User user);

//    /**
//     * 根据UserLevel查看是否被引用
//     * @param id
//     * @return
//     */
//    public boolean checkUserByUserLevel(int id);


}
