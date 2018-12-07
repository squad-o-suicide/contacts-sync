package org.shoper.contacts.filter;

import org.apache.commons.lang3.StringUtils;
import org.shoper.contacts.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
public class AuthorityFilter implements Filter {
    @Autowired
    UserSession userSession;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpServletRequest.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("token".equals(c.getName())) {
                    token = c.getValue();
                }
            }
        }
        if (StringUtils.isEmpty(token))
            token = httpServletRequest.getHeader("token");
        User userInfo = null;
        HttpSession httpSession = httpServletRequest.getSession();
        Object userInfo1 = httpSession.getAttribute("userInfo");
        if (userInfo1 != null)
            userInfo = (User) userInfo1;
        if (userInfo == null) {
            User userSission = userSession.getUserSession(token);
            if (Objects.nonNull(userSission))
                httpSession.setAttribute("userInfo", userSission);
        } else {
            if (!userInfo.getToken().equals(token)) {
                userSession.removeSession(userInfo.getToken());
                httpSession.invalidate();
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
