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
	
	@RequestMapping(value="/index.do")
	public ModelAndView index(HttpServletRequest request){
		request.getSession(false).setAttribute("ehunter_in_session", "ehunter_in_session");
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/logout.do")
	public ModelAndView logout(HttpServletRequest request){
		logger.debug(">>>>> logout");
		ModelAndView mv = new ModelAndView("login");
		String key = request.getParameter("key");
		if("session_t_o".equals(key)){
			mv.addObject("error_code_not_from_security", "EHT-E-0001");
		}
		return mv;
	}
}
