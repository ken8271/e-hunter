package com.pccw.ehunter.mvc.custom;

import java.io.PrintWriter;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.convertor.CustomerGroupConvertor;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerGroupDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerRegistrationService;
import com.pccw.ehunter.service.PositionCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.validator.CustomerValidator;

@Controller
@SessionAttributes(value = {SessionAttributeConstant.CUSTOMER_DTO,
	                SessionAttributeConstant.LIST_OF_GROUPS,
	                SessionAttributeConstant.LIST_OF_POSITION_TYPE})
public class CustomerRegistrationController extends BaseController{
		
	@Autowired
	private CustomerRegistrationService custRegtService;
	
	@Autowired
	private PositionCommonService positionCommonService;
	
	@Autowired
	private CustomerValidator customerValidator;

	@RequestMapping(value="/customer/initAddCustomer.do")
	public ModelAndView initAddCustom(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/customer/fillCustomerInfo.do", true));
	}
	
	private void initCustomer(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.setAttribute(SessionAttributeConstant.CUSTOMER_DTO, new CustomerDTO());
		session.setAttribute(SessionAttributeConstant.LIST_OF_GROUPS, CustomerGroupConvertor.toSelectOptions(custRegtService.loadCustGroups()));
		session.setAttribute(SessionAttributeConstant.LIST_OF_POSITION_TYPE, positionCommonService.loadPositionTypes());
	}

	@RequestMapping(value="/customer/fillCustomerInfo.do")
	public ModelAndView fillCustomerInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/customerCreate");
		initCustomer(request);
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
			List<BaseLabelValueDTO> lvs = positionCommonService.loadPositionTypeByParentCode(code);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(lvs)){
				for(BaseLabelValueDTO lv : lvs){
					Element post = root.addElement("position");
					post.addElement("label").setText(lv.getLabel());
					post.addElement("value").setText(lv.getValue());
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
			mv.addObject(SessionAttributeConstant.LIST_OF_POSITION_TYPE, positionCommonService.loadPositionTypes());
			return mv;
		}
		
		mv = new ModelAndView(new RedirectViewExt("/customer/verify.do", true));
		return mv;
	}
	
	@RequestMapping(value="/customer/verify.do")
	public ModelAndView verify(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/verifyInfo");
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, request.getSession(false).getAttribute(SessionAttributeConstant.CUSTOMER_DTO));
		return mv;
	}
	
	@RequestMapping(value="/customer/submitCustomer.do")
	public ModelAndView submitCustomer(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_DTO)CustomerDTO customerDto , SessionStatus status){
		logger.info("---->>>> " + customerDto.getCustGroup().getFullName());
		custRegtService.completeCustRegistration(customerDto);
		return new ModelAndView(new RedirectViewExt("/customer/completeCustRegistration.do", true));	
	}
	
	@RequestMapping(value="/customer/completeCustRegistration.do")
	public ModelAndView completeCustRegistration(HttpServletRequest request){
		return new ModelAndView("customer/customerConfirmation");
	}
}

