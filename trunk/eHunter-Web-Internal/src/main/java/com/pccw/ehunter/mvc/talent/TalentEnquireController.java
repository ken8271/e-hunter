package com.pccw.ehunter.mvc.talent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.mvc.BaseController;

@Controller
public class TalentEnquireController extends BaseController{
	
	@RequestMapping("/customer/viewTalentDetail.do")
	public ModelAndView viewTalentDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/viewTalentDetail");
		
		String talentId = request.getParameter("_id");
		return mv;
	}
}
