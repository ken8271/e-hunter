package com.pccw.ehunter.mvc.portal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.mvc.BaseController;

@Controller
public class WebPortalManagementController extends BaseController{

	@RequestMapping("/portal/initInformationMgmt.do")
	public ModelAndView initInformationMgmt(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("portal/informationCreate");
		return mv;
	}
}
