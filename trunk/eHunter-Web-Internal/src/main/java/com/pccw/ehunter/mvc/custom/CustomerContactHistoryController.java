package com.pccw.ehunter.mvc.custom;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.CustomerContactHistoryDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerCommonService;
import com.pccw.ehunter.service.CustomerContactHistoryService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.CUSTOMER_DTO,
	SessionAttributeConstant.CUSTOMER_CONTACT_HISTORY_DTO
})
public class CustomerContactHistoryController extends BaseController{
	
	@Autowired
	private CustomerContactHistoryService customerContactHistoryService;
	
	@Autowired
	private CustomerCommonService custCommonService;
	
	@RequestMapping("/customer/initCustomerContactHistorySearch.do")
	public ModelAndView initCustomerContactHistorySearch(){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/customer/initCustomersSearch.do", true));
		mv.addObject(ModuleIndicator.MODULE, ModuleIndicator.CUSTOMER_CONTACT_HISTORY);
		return mv;
	}
	
	@RequestMapping("/customer/viewCustomerContactHistory.do")
	public ModelAndView viewCustomerContactHistory(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/customerContactHistoryView");
		
		String id = request.getParameter("_id");
		
		mv.addObject(SessionAttributeConstant.CUSTOMER_DTO, custCommonService.getCustomerByID(id));
		mv.addObject(SessionAttributeConstant.CUSTOMER_CONTACT_HISTORY_DTO, new CustomerContactHistoryDTO());
		mv.addObject(SessionAttributeConstant.LIST_OF_RESPONSE_PERSON, custCommonService.getResponsePersonsByCustomerID(id));
		
		return mv;
	}
	
	@RequestMapping("/customer/loadContactHistories.do")
	public void loadContactHistories(HttpServletRequest request ,HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String id = request.getParameter("_id");
			
			List<CustomerContactHistoryDTO> histories = customerContactHistoryService.getContactHistories(id);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(histories)){
				for(CustomerContactHistoryDTO dto : histories){
					Element hst = root.addElement("history");
					hst.addElement("systemContactRefNum").setText(dto.getSystemContactRefNum());
					hst.addElement("responsePersonName").setText(dto.getResponsePersonDto().getName());
					hst.addElement("adviser").setText(dto.getAdviserDto().getCnName());
					hst.addElement("contactDate").setText(DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime()));
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
			logger.error(">>>>> Exception Catched(loadContactHistories) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
	}
	
	@RequestMapping("/customer/submitContactHistory.do")
	public ModelAndView submitContactHistory(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_CONTACT_HISTORY_DTO)CustomerContactHistoryDTO customerContactHistoryDto){
		ModelAndView mv = new ModelAndView("customer/customerContactHistoryView");
		
		customerContactHistoryDto.setAdviserDto((InternalUserDTO)SecurityUtils.getUser());
		
		customerContactHistoryService.saveContactHistory(customerContactHistoryDto);
		
		return mv;
	}
	
	@RequestMapping("/customer/viewContactHistoryDetail.do")
	public void viewContactHistoryDetail(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String id = request.getParameter("_id");
			
			CustomerContactHistoryDTO dto = customerContactHistoryService.getContactHistoryByID(id);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(dto != null){
				Element hst = root.addElement("history");
				hst.addElement("systemContactRefNum").setText(dto.getSystemContactRefNum());
				hst.addElement("systemCustomerRefNum").setText(dto.getCustomerDto().getSystemCustRefNum());
				hst.addElement("customerName").setText(dto.getCustomerDto().getFullName());
				hst.addElement("responsePersonName").setText(dto.getResponsePersonDto().getName());
				hst.addElement("content").setText(dto.getContent());
				hst.addElement("remark").setText(dto.getRemark());
				hst.addElement("adviser").setText(dto.getAdviserDto().getCnName());
				hst.addElement("contactDate").setText(DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime()));
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			out = response.getWriter();
			writer = new XMLWriter(out, format);
			
			writer.write(doc);
			
			if(writer != null){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(viewContactHistoryDetail) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
	}
}
