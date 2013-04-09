package cn.org.polaris.web.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutUsController {

	@RequestMapping("/about/index.do")
	public ModelAndView index(){
		return new ModelAndView("about/index");
	}
	
	@RequestMapping("/about/introduce.do")
	public ModelAndView introduce(){
		return new ModelAndView("about/introduce");
	}
	
	@RequestMapping("/about/culture.do")
	public ModelAndView culture(){
		return new ModelAndView("about/culture");
	}
	
	@RequestMapping("/about/joinUs.do")
	public ModelAndView joinUs(){
		return new ModelAndView("about/joinUs");
	}
	
	@RequestMapping("/about/contactUs.do")
	public ModelAndView contactUs(){
		return new ModelAndView("about/contactUs");
	}
}
