package com.bateng.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            long time1 = new Date().getTime();
        System.out.println("过滤器执行");
            filterChain.doFilter(servletRequest,servletResponse);
            long time2 = new Date().getTime();
        System.out.println("请求耗时:   "+(time2-time1));
        System.out.println("过滤器完成");
    }

    @Override
    public void destroy() {

    }
}
