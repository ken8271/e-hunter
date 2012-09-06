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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.CustomerConvertor;
import com.pccw.ehunter.dto.AnnualLeaveWelfareDTO;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerEnquireDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDescriptionDTO;
import com.pccw.ehunter.dto.PositionRequirementDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ResidentialWelfareDTO;
import com.pccw.ehunter.dto.SalaryCategoryDTO;
import com.pccw.ehunter.dto.SocietyWelfareDTO;
import com.pccw.ehunter.dto.SubjectCategoryDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.validator.PositionDescriptionValidator;
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
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private PositionDescriptionValidator postDescValidator;
	
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
	
	@RequestMapping("/project/saveProjectInfo.do")
	public ModelAndView saveProjectInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,BindingResult errors){
		ModelAndView mv = null;
		
		projectValidator.validate(projectDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("project/projectCreate");
			mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
			return mv;
		}
		
		CustomerDTO custDto = custCommonService.getCustomerByID(projectDto.getSystemCustRefNum());
		
		if(custDto != null){
			projectDto.setCustomerDto(custDto);
		}
		
		return new ModelAndView(new RedirectViewExt("/project/fillPositionDescription.do", true));
	}
	
	@RequestMapping("/project/fillPositionDescription.do")
	public ModelAndView fillPositionDescription(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/positionDescription");
		
		PositionDescriptionDTO postDescDto = new PositionDescriptionDTO();
		
		initializePositionDescription(request , mv );
		
		mv.addObject(SessionAttributeConstant.POSITION_DESCRIPTION_DTO, postDescDto);
		mv.addObject("customerName", projectDto.getCustomerDto().getFullName());
		
		return mv;
	}
	
	private void initializePositionDescription(HttpServletRequest request,ModelAndView mv) {
		List<PositionCategoryDTO> categories = codeTableHelper.getPositionCategories(request);
		List<SalaryCategoryDTO> salaryCategories = codeTableHelper.getSalaryCategories(request);
		List<AnnualLeaveWelfareDTO> annualWelfares = codeTableHelper.getAnnualLeaveWelfares(request);
		List<ResidentialWelfareDTO> residentialWelfares = codeTableHelper.getResidentialWelfares(request);
		List<SocietyWelfareDTO> societyWelfares = codeTableHelper.getSocietyWelfares(request);
		
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, categories);
		mv.addObject(WebConstant.LIST_OF_SALARY_CATEGORY, salaryCategories);
		mv.addObject(WebConstant.LIST_OF_ANNUAL_LEAVE_WELFARE, annualWelfares);
		mv.addObject(WebConstant.LIST_OF_RESIDENTIAL_WELFARE, residentialWelfares);
		mv.addObject(WebConstant.LIST_OF_SOCIETY_WELFARE, societyWelfares);
	}
	
	@RequestMapping("/project/savePositionDescription.do")
	public ModelAndView savePositionDescription(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,
			@ModelAttribute(SessionAttributeConstant.POSITION_DESCRIPTION_DTO)PositionDescriptionDTO postDescDto , BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/project/fillPositionRequirement.do", true));
		
		postDescValidator.validate(postDescDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("project/positionDescription");
			mv.addObject("customerName", projectDto.getCustomerDto().getFullName());
			mv.addObject(SessionAttributeConstant.POSITION_DESCRIPTION_DTO, postDescDto);
		}
		
		projectDto.setPostDescDto(postDescDto);
		
		return mv;
	}
	
	@RequestMapping("/project/fillPositionRequirement.do")
	public ModelAndView fillPositionRequirement(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/positionRequirement");
		
		initializePositionRequirement(request , mv);
		mv.addObject(SessionAttributeConstant.POSITION_REQUIREMENT_DTO, new PositionRequirementDTO());
		return mv;
	}

	private void initializePositionRequirement(HttpServletRequest request,ModelAndView mv) {
		List<SubjectCategoryDTO> majorCategories = codeTableHelper.getAllSubjectTypes(request);
		List<DegreeDTO> degreeCategories = codeTableHelper.getAllDegrees(request);
		
		mv.addObject(WebConstant.LIST_OF_SUBJECT_TYPE, majorCategories);
		mv.addObject(WebConstant.LIST_OF_DEGREE, degreeCategories);
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
