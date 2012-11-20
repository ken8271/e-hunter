package com.pccw.ehunter.mvc.solutions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HumanResourcesSolutionsController {
	
	@RequestMapping("/solutions/index.do")
	public ModelAndView index(){
		return new ModelAndView("solutions/index");
	}
}
