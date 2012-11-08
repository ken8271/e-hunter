package com.pccw.ehunter.mvc.custom;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.CustomerGroupConvertor;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerRegistrationService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.CustomerValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.CUSTOMER_DTO,
	SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON,
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
		ModelAndView mv = new ModelAndView(new RedirectViewExt("customer/preEditMultiResponsePerson.do", true));
		
		customerValidator.validate(customerDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("customer/customerAmend");
			mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
			return mv;
		}
		
		customerDto.setTypeDto(codeTableHelper.getCompanyCategoryByCode(request, customerDto.getType()));
		customerDto.setSizeDto(codeTableHelper.getCompanySizeByCode(request, customerDto.getSize()));
		customerDto.setGradeDto(codeTableHelper.getCustomerGradeByCode(request, customerDto.getGrade()));
		customerDto.setCustomerStatusDto(codeTableHelper.getCustomerStatusByCode(request, customerDto.getCustomerStatus()));
		
		return mv;
	}
	
	@RequestMapping("/customer/preEditMultiResponsePerson.do")
	public ModelAndView preEditMultiResponsePerson(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		ModelAndView mv = new ModelAndView("customer/multiResponsePersonAmend");
		
		List<CustomerResponsePersonDTO> rps = customerDto.getMultiResponsePerson();
		if(CollectionUtils.isEmpty(rps)){
			rps = new ArrayList<CustomerResponsePersonDTO>();
		}
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		mv.addObject(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON, new CustomerResponsePersonDTO());
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, codeTableHelper.getPositionCategories(request));
		
		return mv;
	}
	
	@RequestMapping("/customer/updateCustomer.do")
	public ModelAndView updateCustomer(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto, BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("customer/viewCustomerDetail.do", true));

		custRegtService.updateCustomerInfo(customerDto);
		
		transactionLogService.logTransaction(ModuleIndicator.CUSTOMER, getMessage("tx.log.customer.update" , new String[]{customerDto.getSystemCustRefNum()}));
		mv.addObject("_id", customerDto.getSystemCustRefNum());
		return mv;
	}
	
	@RequestMapping("/customer/preEditResponsePerson.do")
	public ModelAndView preEditResponsePerson(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		ModelAndView mv = new ModelAndView("customer/singleResponsePersonAmend");
		
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("_response_person_id", id);
		
		List<CustomerResponsePersonDTO> rps = customerDto.getMultiResponsePerson();
		
		CustomerResponsePersonDTO responsePersonDto = null;
		if(!CollectionUtils.isEmpty(rps)){
			responsePersonDto = rps.get(Integer.valueOf(id));
		}
		
		String module = request.getParameter(ModuleIndicator.MODULE);
		if(StringUtils.isEmpty(module)){
			module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		}else {
			request.getSession(false).setAttribute(ModuleIndicator.MODULE, module);
		}
		mv.addObject(ModuleIndicator.MODULE, module);
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON,responsePersonDto);
		return mv;
	}
	
	@RequestMapping("/customer/updateResponsePerson.do")
	public ModelAndView updateResponsePerson(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto,
			@ModelAttribute(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON)CustomerResponsePersonDTO responsePersonDto,
			BindingResult errors){
		ModelAndView mv = null;
		
		String module = request.getParameter(ModuleIndicator.MODULE);
		if(StringUtils.isEmpty(module)){
			module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		}else {
			request.getSession(false).setAttribute(ModuleIndicator.MODULE, module);
		}
		
		if(ModuleIndicator.CUSTOMER_REGISTRATION.equals(module)){
			mv = new ModelAndView("customer/multiResponsePersonCreate");
		}else {
			mv = new ModelAndView("customer/multiResponsePersonAmend");
		}
		
		customerValidator.validateCustomerResponsePerson(responsePersonDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("customer/singleResponsePersonAmend");
			mv.addObject(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON, responsePersonDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("_response_person_id");
		
		List<CustomerResponsePersonDTO> rps = customerDto.getMultiResponsePerson();
		
		if(!CollectionUtils.isEmpty(rps)){
			rps.set(Integer.valueOf(id), responsePersonDto);
		}
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON, new CustomerResponsePersonDTO());
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		mv.addObject("clearField", CommonConstant.YES);
		
		return mv;
	}
	
	@RequestMapping("/customer/deleteResponsePerson.do")
	public ModelAndView deleteResponsePerson(HttpServletRequest request,@ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/customer/fillMultiResponsePerson.do", true));
		
		String id = request.getParameter("_id");
		
		List<CustomerResponsePersonDTO> rpDtos = customerDto.getMultiResponsePerson();
		List<CustomerResponsePersonDTO> removedDtos = new ArrayList<CustomerResponsePersonDTO>(0);
		
		if(!CollectionUtils.isEmpty(rpDtos)){
			removedDtos.add(rpDtos.get(Integer.parseInt(id)));
			rpDtos.removeAll(removedDtos);
		}
		
		customerDto.setMultiResponsePerson(rpDtos);
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON, new CustomerResponsePersonDTO());
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		return mv;
	}	
}
