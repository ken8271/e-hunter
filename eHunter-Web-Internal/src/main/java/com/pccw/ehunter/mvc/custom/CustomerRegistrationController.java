package com.pccw.ehunter.mvc.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.convertor.CustomerGroupConvertor;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerRegistrationService;

@Controller
public class CustomerRegistrationController extends BaseController{
	
	@Autowired
	private CustomerRegistrationService custRegtService;
	
	@RequestMapping(value="/customer/initAddCustomer.do")
	public ModelAndView initAddCustom(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/customerCreate");
		mv.addObject("customerDto", new CustomerDTO());
		mv.addObject("listOfGroups", CustomerGroupConvertor.toSelectOptions(custRegtService.loadCustGroups()));
		return mv;
	}
}
