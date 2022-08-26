package com.uvic.auth;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Order(1)
public class SecurityFilter implements Filter {

    @Resource
    private Map<String, String> userTokenMap;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("access_token");
        String username = httpRequest.getHeader("user");
        if(!isEmptyString(token) && !isEmptyString(username)){
            if(checkIfUserAvailable(username, token) && !checkIfUserAccessingAdmin(username, httpRequest.getServletPath())){
                chain.doFilter(request, response);
                return;
            }
        }
        httpResponse.setHeader("content-type", "application/json");
        httpResponse.sendError(404);
    }

    boolean checkIfUserAvailable(String username, String token){
        return userTokenMap.get(username) != null && token.equals(userTokenMap.get(username));
    }

    boolean checkIfUserAccessingAdmin(String username, String servletPath){
        return servletPath.contains("admin")  && !"admin".equals(username) ;
    }

    boolean isEmptyString(String val){
        return val == null || val.isEmpty();
    }
}
