package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;
import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	// http://localhost:9090
	@RequestMapping("/")
	public  String   home() {
		return "home";
	}
	
	@RequestMapping("/LoginForm")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users/login");
		return mv;
		
	}
	
	@RequestMapping("/Login")
	public ModelAndView login(HttpServletRequest request) {
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		UserVo userVo = userMapper.login(userid, passwd);
		MenuVo menuVo = menuMapper.getMenu("MENU01");
		
		String loc = "";
		if(userVo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("login", userVo);
			session.setAttribute("menuVo", menuVo);
			session.setMaxInactiveInterval(30*60);
			loc = "redirect:/";
		} else { //아이디 비번 틀림
			loc = "redirect:/LoginForm";
			
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName(loc);
		return mv;
	
	}
	@RequestMapping("/Logout")
	public String logout(HttpSession session) {
		session.invalidate();		
		return "redirect:/LoginForm";
	}
}
