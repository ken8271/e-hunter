package com.pccw.ehunter.mvc.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.CustomerEnquireDTO;
import com.pccw.ehunter.mvc.BaseController;

@Controller
@SessionAttributes({SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO})
public class CustomerEnquireController extends BaseController{
	
	@RequestMapping(value="/customer/initCustomersSearch.do")
	public ModelAndView initCustomersSearch(HttpServletRequest reuqest){
		ModelAndView mv = new ModelAndView("customer/customerEnquiry");
		
		CustomerEnquireDTO enquireDto = new CustomerEnquireDTO();
		reuqest.getSession(false).setAttribute(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO, enquireDto);
		mv.addObject(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO, enquireDto);
		
		return mv;
	}
	
	public ModelAndView customersSearch(HttpServletRequest requet , @ModelAttribute(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO)CustomerEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("customer/customerEnquiry");
		handlePagedSearch(requet , enquireDto);
		return null;
	}

	private void handlePagedSearch(HttpServletRequest requet, CustomerEnquireDTO enquireDto) {
		
	}
}
