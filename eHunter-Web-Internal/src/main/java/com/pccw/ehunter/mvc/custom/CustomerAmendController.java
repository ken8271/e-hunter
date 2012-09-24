package com.pccw.ehunter.mvc.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.CustomerGroupConvertor;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerRegistrationService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.validator.CustomerValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.CUSTOMER_DTO,
    SessionAttributeConstant.LIST_OF_GROUPS
})
public class CustomerAmendController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private CustomerRegistrationService custRegtService;
	
	@Autowired
	private CustomerValidator customerValidator;

	@RequestMapping("/customer/preEditCustomerInfo.do")
	public ModelAndView preEditCustomerInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		ModelAndView mv = new ModelAndView("customer/customerAmend");
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		mv.addObject(SessionAttributeConstant.LIST_OF_GROUPS, CustomerGroupConvertor.toSelectOptions(custRegtService.loadCustGroups()));
		
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, codeTableHelper.getPositionCategories(request));
		
		return mv;
	}
	
	@RequestMapping("/customer/updateCustomerInfo.do")
	public ModelAndView updateCustomerInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto, BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("customer/viewCustomerDetail.do", true));
		
		customerValidator.validate(customerDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("customer/customerAmend");
			mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
			return mv;
		}
		
		custRegtService.updateCustomerInfo(customerDto);
		
		mv.addObject("_id", customerDto.getSystemCustRefNum());
		return mv;
	}
}
