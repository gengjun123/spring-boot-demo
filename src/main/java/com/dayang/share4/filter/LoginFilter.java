package com.dayang.share4.filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getParameter("token");
        if (!StringUtils.isEmpty(token) && token.equals("132")) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
