package com.pccw.ehunter.mvc.career;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CareerManagerController {

	@RequestMapping("/career/index.do")
	public ModelAndView index(){
		return new ModelAndView("career/index");
	}
}
