package cn.org.polaris.web.solutions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HumanResourcesSolutionsController {
	
	@RequestMapping("/solutions/index.do")
	public ModelAndView index(){
		return new ModelAndView("solutions/index");
	}
	
	@RequestMapping("/solutions/service.do")
	public ModelAndView service(){
		return new ModelAndView("solutions/service");
	}
	
	@RequestMapping("/solutions/outsouring.do")
	public ModelAndView outsouring(){
		return new ModelAndView("solutions/outsouring");
	}
	
	@RequestMapping("/solutions/background_research.do")
	public ModelAndView backgroundResearch(){
		return new ModelAndView("solutions/backgroundResearch");
	}
	
	@RequestMapping("/solutions/hr_it_platform.do")
	public ModelAndView itPlatform(){
		return new ModelAndView("solutions/itPlatform");
	}
	
	@RequestMapping("/solutions/marketing.do")
	public ModelAndView marketing(){
		return new ModelAndView("solutions/marketing");
	}
}
