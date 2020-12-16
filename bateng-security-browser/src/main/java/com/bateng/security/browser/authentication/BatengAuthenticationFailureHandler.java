package com.bateng.security.browser.authentication;

import com.bateng.core.properties.BatengSecurityProperties;
import com.bateng.core.properties.LoginType;
import com.bateng.security.browser.support.SimpleRespone;
import com.bateng.utils.ToJsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.bateng.utils.ToJsonString.*;

@Component
public class BatengAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BatengSecurityProperties batengSecurityProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");
        if(LoginType.JSON.equals(batengSecurityProperties.getBrowserProperties().getLoginType())){//json
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(toDisableCircularReferenceDetectString(new SimpleRespone(exception.getMessage())));
        }else{
            super.onAuthenticationFailure(request,response,exception);
        }

    }
}
