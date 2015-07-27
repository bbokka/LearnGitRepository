package com.balakrishna.spring.security2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.balakrishna.spring.security2.service.SecurityService;

@Controller
public class SpringSecurityController {
	
	@Autowired
	private SecurityService securityservice;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView getHomePage()
	{
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public ModelAndView getDashboardPage(Principal principal)
	{
		return new ModelAndView("dashboard");
	}
	
	@Secured(value = { "ROLE_ADMIN" })
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public ModelAndView getAdminPage(Principal principal)
	{
		return new ModelAndView("admin");
	}
	
	@RequestMapping(value="/reports",method=RequestMethod.GET)
	public ModelAndView getReportsPage(Principal principal)
	{
		return new ModelAndView("reports");
	}
	
	
	@RequestMapping(value="/denied",method=RequestMethod.GET)
	public ModelAndView getDeniedPage(Principal principal)
	{
		return new ModelAndView("denied");
	}
	
		
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView getRegisterPage(){
		//System.out.println("LoggedinUser for /reports     "+principal.getName());
		return new ModelAndView("register");
	}
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView getPOSTRegisterPage(@RequestParam("email") String email,@RequestParam("password") String password ){
		//System.out.println("LoggedinUser for /reports     "+principal.getName());
		//System.out.println("email >>> " + email+"   password  >>>  "+password);
		securityservice.createUserAccount(email, password);
		return new ModelAndView("home");
	}
	
}
