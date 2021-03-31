package com.bateng.security.app.authentication;

import com.bateng.core.properties.BatengSecurityProperties;
import com.bateng.core.properties.LoginType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.bateng.utils.ToJsonString.*;

@Component
public class BatengAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BatengSecurityProperties batengSecurityProperties;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
      logger.info("登录成功"+batengSecurityProperties.getBrowserProperties().getLoginType().name());

      if(LoginType.JSON.equals(batengSecurityProperties.getBrowserProperties().getLoginType())){//json
          response.setContentType("application/json;charset=UTF-8");
//          response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
          response.getWriter().write(toDisableCircularReferenceDetectString(authentication));
      }else{
          super.onAuthenticationSuccess(request,response,authentication);
      }

    }
}
