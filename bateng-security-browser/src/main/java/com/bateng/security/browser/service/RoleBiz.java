package com.bateng.security.browser.service;


import com.bateng.security.browser.entity.Role;

import java.util.List;

public interface RoleBiz {

    public void addRole(Role role);

    /**
     * 返回所有角色 json
     * @return
     */
    public  String findRoleAjax();

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> findRole();
}
