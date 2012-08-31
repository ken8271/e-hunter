package com.pccw.ehunter.mvc.project;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.convertor.CustomerConvertor;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerEnquireDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.validator.ProjectValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.PROJECT_DTO
})
public class ProjectRegistrationController extends BaseController{
	
	@Autowired
	private CustomerCommonService custCommonService;
	
	@Autowired
	private ProjectValidator projectValidator;
	
	@RequestMapping("/project/initNewProject.do")
	public ModelAndView initNewProject(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/project/fillProjectInfo.do", true));
	}
	
	@RequestMapping("/project/fillProjectInfo.do")
	public ModelAndView fillProjectInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/projectCreate");
		
		initializeProject(mv);
		
		return mv;
	}

	private void initializeProject(ModelAndView mv) {
		InternalUserDTO internalUserDto = (InternalUserDTO)SecurityUtils.getUser();
		
		if(internalUserDto == null){
			internalUserDto = new InternalUserDTO();
		}
		
		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setAdviserDto(internalUserDto);
		projectDto.setCustomerDto(new CustomerDTO());
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
	}
	
	@RequestMapping("/project/loadCustomers.do")
	public ModelAndView loadCustomers(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		try {
			response.setContentType("text/xml;charset=UTF-8");
			out = response.getWriter();
			
			String id = request.getParameter("_id");
			String name = request.getParameter("_name");
			
			CustomerEnquireDTO enquireDto = new CustomerEnquireDTO();
			enquireDto.setSystemCustRefNum(id);
			enquireDto.setName(name);
			
			List<CustomerDTO> dtos = custCommonService.getCustomersByCriteria(CustomerConvertor.toPagedCriteria(enquireDto));

			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(dtos)){
				for(CustomerDTO dto : dtos){
					Element cust = root.addElement("customer");
					cust.addElement("id").setText(dto.getSystemCustRefNum());
					cust.addElement("name").setText(dto.getFullName() + "(" + dto.getShortName() + ")");
					cust.addElement("grade").setText(dto.getGrade());
					cust.addElement("status").setText(dto.getStatus());
				}
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(out, format);
			writer.write(doc);
			
			if(null != writer){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched (loadCustomers) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
		return null;
	}
}
