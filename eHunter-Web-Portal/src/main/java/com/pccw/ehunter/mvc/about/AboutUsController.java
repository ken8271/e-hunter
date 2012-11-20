package com.pccw.ehunter.mvc.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutUsController {

	@RequestMapping("/about/index.do")
	public ModelAndView index(){
		return new ModelAndView("about/index");
	}
}
