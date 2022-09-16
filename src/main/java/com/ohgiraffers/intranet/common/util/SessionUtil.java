package com.ohgiraffers.intranet.common.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    /* 로그아웃 프로세스 진행 */
    public static void invalidateSession(HttpServletRequest request, HttpServletResponse response){

        /* false 일 경우 http. true일 경우 https. */
        boolean isSecure = false;
        String contextPath = null;

        /* request -> Session invalidate */
        if (request != null) {
            HttpSession session = request.getSession(false);

            if(session != null){
                session.invalidate();
            }

            /* http 요청으로 request가 들어왔는지 확인한다. */
            isSecure = request.isSecure();
            /* 프로젝트 path만 얻어온다. */
            contextPath = request.getContextPath();
        }

        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
        context.setAuthentication(null);

        /* response -> cookie invalidate */
        if(response != null){

            Cookie cookie = new Cookie("JSESSIONID", null);
            String cookiePath = StringUtils.hasText(contextPath) ? contextPath : "/";
            cookie.setPath(cookiePath);
            cookie.setMaxAge(0);
            cookie.setSecure(isSecure);
            response.addCookie(cookie);
        }

    }
}
