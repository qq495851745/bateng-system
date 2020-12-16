package com.bateng.security.browser.controller;


import com.bateng.security.browser.entity.User;
import com.bateng.security.browser.service.MenuBiz;
import com.bateng.security.browser.service.UserBiz;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/authencication")
public class MenuAjaxController {
     @Autowired
     private MenuBiz menuBiz;

     @Autowired
     private UserBiz userBiz;


     @RequestMapping(value = "/findMenus" ,method = {RequestMethod.POST, RequestMethod.GET},produces = "application/json;charset=utf-8")
     @ApiOperation(value = "根据当前用户获取所有菜单")
     public String findMenus(HttpSession session){
         SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
         String username = ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
//         User user= (User) session.getAttribute("user");
         User user = userBiz.findUserByUsername(username);
        return menuBiz.findMenusByUserIdAjax(user.getId());
         //return menuBiz.findMenusAjax();
     }

    public MenuBiz getMenuBiz() {
        return menuBiz;
    }

    public void setMenuBiz(MenuBiz menuBiz) {
        this.menuBiz = menuBiz;
    }

    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
}
