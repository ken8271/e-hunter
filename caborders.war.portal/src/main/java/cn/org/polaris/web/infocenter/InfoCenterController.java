package cn.org.polaris.web.infocenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoCenterController {

	@RequestMapping("/infocenter/index.do")
	public ModelAndView index(){
		return new ModelAndView("infocenter/index");
	}
}
