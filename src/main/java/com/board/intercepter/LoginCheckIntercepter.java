package com.board.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckIntercepter implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			//1. 세션에서 회원정보를 검색
			HttpSession session = request.getSession();
			Object userVo = session.getAttribute("login");
			
			//요청한 주소정보 확인
			String requestUrl = request.getRequestURL().toString();
			System.out.println(requestUrl);
			//login 페이지는 체크에서 제외한다.(제외설정)
			//Interface 설정하는 곳에서 해단경로를 제외할때 사용하는데 스프링레거시는 xml에서 하고 spring은 다르곳 if() 필요없다.
			if(requestUrl.contains("/Login")) {
				return true; //로그인 체크를 중단
			}
			
			//----------------------------------------------
			if(userVo == null) {
				//로그인되어 있찌 않다면 / LoginForm으로 이동;
				response.sendRedirect("/LoginForm");
				return false;
			}
			
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
