package com.pccw.ehunter.mvc.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.mvc.BaseController;

@Controller
public class CustomerRegistrationController extends BaseController{
	
	@RequestMapping(value="/customer/initAddCustomer.do")
	public ModelAndView initAddCustom(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/customerCreate");
		mv.addObject("customerDto",new CustomerDTO());
		return mv;
	}
}
