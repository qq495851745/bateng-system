package com.bateng.security.browser.controller;

import com.bateng.core.properties.BatengSecurityProperties;
import com.bateng.core.properties.BrowserProperties;
import com.bateng.core.properties.BatengSecurityProperties;
import com.bateng.security.browser.support.SimpleRespone;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static com.bateng.utils.ToJsonString.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private BatengSecurityProperties batengSecurityProperties;



    @RequestMapping(value = "/authentication/require")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info(batengSecurityProperties.getBrowserProperties().getLoginPage());
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是:" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {//验证请求是否是页面请求
                redirectStrategy.sendRedirect(request, response,batengSecurityProperties.getBrowserProperties().getLoginPage());//跳转指定登录页面
            }
        }
        return toDisableCircularReferenceDetectString(new SimpleRespone("访问的服务需要身份认证，请先登录"));
    }
}
