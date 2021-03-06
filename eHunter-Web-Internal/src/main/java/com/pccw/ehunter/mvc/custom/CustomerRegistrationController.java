package com.pccw.ehunter.mvc.custom;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.ActionFlag;
import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.CustomerIndicator;
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.CustomerGroupConvertor;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerGroupDTO;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerRegistrationService;
import com.pccw.ehunter.service.PositionCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.CustomerValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.CUSTOMER_DTO,
	SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON,
	SessionAttributeConstant.PROJECT_DTO,
	SessionAttributeConstant.LIST_OF_GROUPS
})
public class CustomerRegistrationController extends BaseController{
		
	@Autowired
	private CustomerRegistrationService custRegtService;
	
	@Autowired
	private PositionCommonService positionCommonService;
	
	@Autowired
	private CustomerValidator customerValidator;
	
	@Autowired
	private CodeTableHelper codeTableHelper;

	@RequestMapping(value="/customer/initAddCustomer.do")
	public ModelAndView initAddCustom(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/customer/fillCustomerInfo.do", true));
	}
	
	private void initCustomer(HttpServletRequest request , ModelAndView mv) {
		HttpSession session = request.getSession(false);
		session.setAttribute(SessionAttributeConstant.CUSTOMER_DTO, new CustomerDTO());
		session.setAttribute(SessionAttributeConstant.LIST_OF_GROUPS, CustomerGroupConvertor.toSelectOptions(custRegtService.loadCustGroups()));
		
		List<PositionCategoryDTO> positionCategories = codeTableHelper.getPositionCategories(request);
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, positionCategories);
	}

	@RequestMapping(value="/customer/fillCustomerInfo.do")
	public ModelAndView fillCustomerInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/customerCreate");
		initCustomer(request , mv );
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, request.getSession(false).getAttribute(SessionAttributeConstant.CUSTOMER_DTO));
		return mv;
	}
	
	@RequestMapping(value="/customer/loadCustomerGroup.do")
	public ModelAndView loadCustomerGroup(HttpServletRequest request , HttpServletResponse response){
		CustomerGroupDTO cg = null;
		PrintWriter out = null;
		XMLWriter writer = null;
		try {
			response.setContentType("text/xml;charset=UTF-8");
			out = response.getWriter();
			
			String _id = request.getParameter("_id");
			cg = custRegtService.loadCustGroupByID(_id);

			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			root.addElement("id").setText(cg.getSystemGroupRefNum());
			root.addElement("shortName").setText(cg.getShortName());
			root.addElement("fullName").setText(cg.getFullName());
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(out, format);
			writer.write(doc);
			
			if(null != writer){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched (loadCustomerGroup) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}

		return null;
	}
	
	@RequestMapping(value="/customer/loadPositions.do")
	public ModelAndView loadPositions(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String code = request.getParameter("code");
			List<PositionDTO> positions = positionCommonService.getPositionsByCategory(code);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(positions)){
				for(PositionDTO dto : positions){
					Element post = root.addElement("position");
					post.addElement("label").setText(dto.getDisplayName());
					post.addElement("value").setText(dto.getTypeCode());
				}
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			out = response.getWriter();
			writer = new XMLWriter(out, format);
			
			writer.write(doc);
			
			if(writer != null){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(loadPositions) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
		
		return null;
	}
	
	@RequestMapping(value="/customer/saveCustCoInfo.do")
	public ModelAndView saveCustCoInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto, BindingResult errors){
		ModelAndView mv = null;
		customerValidator.validate(customerDto, errors);
		if(errors.hasErrors()){
			mv = new ModelAndView("customer/customerCreate");
			mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
			mv.addObject(SessionAttributeConstant.LIST_OF_GROUPS, CustomerGroupConvertor.toSelectOptions(custRegtService.loadCustGroups()));
			return mv;
		}
		
		customerDto.setTypeDto(codeTableHelper.getCompanyCategoryByCode(request, customerDto.getType()));
		customerDto.setSizeDto(codeTableHelper.getCompanySizeByCode(request, customerDto.getSize()));
		customerDto.setGradeDto(codeTableHelper.getCustomerGradeByCode(request, customerDto.getGrade()));
		customerDto.setCustomerStatusDto(codeTableHelper.getCustomerStatusByCode(request, customerDto.getCustomerStatus()));
		
		mv = new ModelAndView(new RedirectViewExt("/customer/fillMultiResponsePerson.do", true));
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		return mv;
	}
	
	@RequestMapping("/customer/fillMultiResponsePerson.do")
	public ModelAndView fillMultiResponsePerson(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		ModelAndView mv = new ModelAndView("customer/multiResponsePersonCreate");
		
		List<CustomerResponsePersonDTO> rps = customerDto.getMultiResponsePerson();
		if(CollectionUtils.isEmpty(rps)){
			rps = new ArrayList<CustomerResponsePersonDTO>();
		}
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		mv.addObject(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON, new CustomerResponsePersonDTO());
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, codeTableHelper.getPositionCategories(request));
		return mv;
	}
	
	@RequestMapping("/customer/addResponsePersonActions.do")
	public ModelAndView addResponsePersonActions(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON)CustomerResponsePersonDTO responsePersonDto,
			@ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/customer/addResponsePerson.do", true));
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		String module = request.getParameter(ModuleIndicator.MODULE);
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		mv.addObject(ModuleIndicator.MODULE, module);
		
		return mv;
	}	
	
	@RequestMapping("/customer/addResponsePerson.do")
	public ModelAndView addResponsePerson(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto,
			@ModelAttribute(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON)CustomerResponsePersonDTO responsePersonDto,
			BindingResult errors){
		ModelAndView mv = null;
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		String module = request.getParameter(ModuleIndicator.MODULE);
		if(StringUtils.isEmpty(module)){
			module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		}else {
			request.getSession(false).setAttribute(ModuleIndicator.MODULE, module);
		}
		if(ActionFlag.COMPLETE.equals(actionFlag) && isNothingInput(responsePersonDto)){
			if(ModuleIndicator.CUSTOMER_REGISTRATION.equals(module)){				
				mv = new ModelAndView(new RedirectViewExt("/customer/verify.do", true));
			}else {
				mv = new ModelAndView(new RedirectViewExt("/customer/updateCustomer.do", true));
			}
			mv.addObject(SessionAttributeConstant.CUSTOMER_DTO , customerDto);
			return mv;
		}
		
		customerValidator.validateCustomerResponsePerson(responsePersonDto, errors);
		if(errors.hasErrors()){
			if(ModuleIndicator.CUSTOMER_REGISTRATION.equals(module)){				
				mv = new ModelAndView("customer/multiResponsePersonCreate");
			}else {
				mv = new ModelAndView("customer/multiResponsePersonAmend");
			}
			mv.addObject(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON, responsePersonDto);
			mv.addObject(SessionAttributeConstant.CUSTOMER_DTO	, customerDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			if(ModuleIndicator.CUSTOMER_REGISTRATION.equals(module)){				
				mv = new ModelAndView(new RedirectViewExt("/customer/verify.do", true));
			}else {
				mv = new ModelAndView(new RedirectViewExt("/customer/updateCustomer.do", true));
			}
			mv.addObject(SessionAttributeConstant.CUSTOMER_DTO , customerDto);
			return mv;
		}
		
		if(ModuleIndicator.CUSTOMER_REGISTRATION.equals(module)){				
			mv = new ModelAndView("customer/multiResponsePersonCreate");
		}else {
			mv = new ModelAndView("customer/multiResponsePersonAmend");
		}
		responsePersonDto.setPositionCategoryDto(codeTableHelper.getPositionCategoryByCode(request, responsePersonDto.getPositionCategory()));
		responsePersonDto.setPositionTypeDto(codeTableHelper.getPositionByCode(responsePersonDto.getPositionType()));
		
		List<CustomerResponsePersonDTO> rps = customerDto.getMultiResponsePerson();
		rps.add(responsePersonDto);
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		mv.addObject(SessionAttributeConstant.CUSTOMER_RESPONSE_PERSON, new CustomerResponsePersonDTO());
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, codeTableHelper.getPositionCategories(request));
		mv.addObject("clearField", CommonConstant.YES);
		return mv ;
	}
	
	private boolean isNothingInput(CustomerResponsePersonDTO rpDto){
		boolean isNothingInput = true;
		
		if(!StringUtils.isEmpty(rpDto.getName())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(rpDto.getPositionCategory())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(rpDto.getPositionType())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(rpDto.getPositionName())){
			isNothingInput = false;
		}
		
		if(rpDto.getTelephoneDto() != null && !StringUtils.isEmpty(rpDto.getTelephoneDto().getPhoneNumber())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(rpDto.getEmail())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(rpDto.getStatus())){
			isNothingInput = false;
		}
		
		return isNothingInput;
	}
	
	@RequestMapping(value="/customer/verify.do")
	public ModelAndView verify(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/verifyInfo");
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, request.getSession(false).getAttribute(SessionAttributeConstant.CUSTOMER_DTO));
		return mv;
	}
	
	@RequestMapping(value="/customer/backToCustomerInfo.do")
	public ModelAndView backToCustomerInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		ModelAndView mv = new ModelAndView("customer/customerCreate");
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		mv.addObject(SessionAttributeConstant.LIST_OF_GROUPS, CustomerGroupConvertor.toSelectOptions(custRegtService.loadCustGroups()));
		
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, codeTableHelper.getPositionCategories(request));
		return mv;
	}
	
	@RequestMapping(value="/customer/submitCustomer.do")
	public ModelAndView submitCustomer(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		String systemGroupRefNum = null;
		if(customerDto.getCustGroup() != null && CustomerIndicator.CUSTOMER_SUBSIDIARY.equals(customerDto.getGroupIndicator()) ){
			systemGroupRefNum = customerDto.getCustGroup().getSystemGroupRefNum();
		}
		custRegtService.completeCustRegistration(customerDto);
		if(CustomerIndicator.CUSTOMER_SUBSIDIARY.equals(customerDto.getGroupIndicator()) ){
			custRegtService.updateSubsidiaryInfo(systemGroupRefNum, customerDto.getSystemCustRefNum());
		}
		
		transactionLogService.logTransaction(ModuleIndicator.CUSTOMER, getMessage("tx.log.customer.create" , new String[]{customerDto.getSystemCustRefNum()}));
		
		return new ModelAndView(new RedirectViewExt("/customer/completeCustRegistration.do", true));	
	}
	
	@RequestMapping(value="/customer/completeCustRegistration.do")
	public ModelAndView completeCustRegistration(HttpServletRequest request ,@ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto){
		ModelAndView mv = new ModelAndView("customer/customerConfirmation");
		InternalUserDTO internalUserDto = (InternalUserDTO)SecurityUtils.getUser();
		
		if(internalUserDto == null){
			internalUserDto = new InternalUserDTO();
		}
		
		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setAdviserDto(internalUserDto);
		projectDto.setCustomerDto(customerDto);
		projectDto.setSystemCustRefNum(customerDto.getSystemCustRefNum());
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, customerDto);
		return mv;
	}
}

