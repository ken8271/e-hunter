package com.pccw.ehunter.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends BaseController{
	
	@RequestMapping(value="/login.do")
	public ModelAndView login(HttpServletRequest request){
		return new ModelAndView("login");
	}
}
