package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserMapper userMapper;
	
	// http://localhost:9090
	@RequestMapping("/")
	public  String   home() {
		return "home";
	}
	@RequestMapping("/LoginForm")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/users/login");
		return mv;
		
	}
	@RequestMapping("/Login")
	public ModelAndView login(HttpServletRequest request) {
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		UserVo userVo = userMapper.login(userid, passwd);
		
		HttpSession session = request.getSession();
		session.setAttribute("login", userVo);
		session.setMaxInactiveInterval(30*60);
		
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
				
		
	}
}
