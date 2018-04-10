package com.cai.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cai.pojo.User;
import com.cai.service.SongService;
import com.cai.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private SongService songService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "login", method = {RequestMethod.GET})
	public ModelAndView toLogin()throws Exception{
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "login", method = {RequestMethod.POST}) 
	public ModelAndView login(User user)throws Exception{
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		Subject subject = SecurityUtils.getSubject();
		
		subject.login(token);
		
		if (subject.hasRole("admin")){
			return new ModelAndView("redirect:/admin/queryAll");
		}else if (subject.hasRole("visitor")){
			return new ModelAndView("redirect:/visitor/visitorQueryAll");
		}
//		User u = userService.findByName(user.getUsername());
//		
//		if (u != null){
//			return new ModelAndView("redirect:/admin/queryAll");
//		
//		}
//System.out.println("出现错误");	
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value = "register", method = {RequestMethod.GET})
	public ModelAndView toRegister()throws Exception{
		return new ModelAndView("register");
	}
	
	@RequestMapping(value = "register", method = {RequestMethod.POST})
	public ModelAndView register(User user)throws Exception{
		
		
		userService.add(user);
		
		return new ModelAndView("redirect:/login");
	}
	

}
