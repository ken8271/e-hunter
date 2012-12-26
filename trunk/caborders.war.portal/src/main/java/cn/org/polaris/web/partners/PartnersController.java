package cn.org.polaris.web.partners;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartnersController {

	@RequestMapping("/partners/index.do")
	public ModelAndView index(){
		return new ModelAndView("partners/index");
	}
}
