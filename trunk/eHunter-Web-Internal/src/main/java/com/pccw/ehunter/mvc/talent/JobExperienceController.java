package com.pccw.ehunter.mvc.talent;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.pccw.ehunter.constant.ActionFlag;
import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.CompanyCategoryDTO;
import com.pccw.ehunter.dto.CompanySizeDTO;
import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.IndustryDTO;
import com.pccw.ehunter.dto.JobExperienceDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.JobExperienceValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_RESUME_DTO,
	SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO
})
public class JobExperienceController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private JobExperienceValidator jobExpValidator;
	
	
	@RequestMapping("/talent/fillJobExperience.do")
	public ModelAndView fillJobExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/jobExperienceCreate");
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
		List<JobExperienceDTO> jobExpDtos = resumeDto.getJobExpDtos();
		
		if(CollectionUtils.isEmpty(jobExpDtos)){
			jobExpDtos = new ArrayList<JobExperienceDTO>(0);
			resumeDto.setJobExpDtos(jobExpDtos);
		}

		initJobExperience(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO, new JobExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		
		return mv;
	}

	private void initJobExperience(HttpServletRequest request, ModelAndView mv) {
		List<IndustryCategoryDTO> industryCategories = codeTableHelper.getIndustryCategories(request);
		List<PositionCategoryDTO> positionCategories = codeTableHelper.getPositionCategories(request);
		List<CompanyCategoryDTO> companyCategories = codeTableHelper.getCompanyCategories(request);
		List<CompanySizeDTO> companySizes = codeTableHelper.getCompanySizes(request);
		
		mv.addObject(WebConstant.LIST_OF_INDUSTRY_CATEGORY, industryCategories);
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, positionCategories);
		mv.addObject(WebConstant.LIST_OF_COMPANY_CATEGORY, companyCategories);
		mv.addObject(WebConstant.LIST_OF_COMPANY_SIZE, companySizes);
	}
	
	@RequestMapping("/talent/addJobExperienceActions.do")
	public ModelAndView addJobExperienceActions(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO)JobExperienceDTO jobExpDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addJobExperience.do", true));
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		
		return mv;
	}
	
	private boolean isNothingInput(JobExperienceDTO jobExpDto){
		boolean isNothingInput = true;
		if(jobExpDto.getFromDateDto() != null){
			if(!StringUtils.isEmpty(jobExpDto.getFromDateDto().getYear())
					|| !StringUtils.isEmpty(jobExpDto.getFromDateDto().getMonth())
					|| !StringUtils.isEmpty(jobExpDto.getFromDateDto().getDay())){
				isNothingInput = false;
			}
		}
		
		if(jobExpDto.getToDateDto() != null){
			if(!StringUtils.isEmpty(jobExpDto.getToDateDto().getYear())
					|| !StringUtils.isEmpty(jobExpDto.getToDateDto().getMonth())
					|| !StringUtils.isEmpty(jobExpDto.getToDateDto().getDay())){
				isNothingInput = false;
			}
		}
		
		if(!StringUtils.isEmpty(jobExpDto.getCompanyName())){
			isNothingInput = false;
		}
		
		if(jobExpDto.getCompanyCategoryDto() != null && !StringUtils.isEmpty(jobExpDto.getCompanyCategoryDto().getCategoryCode())){
			isNothingInput = false;
		}
		
		if(jobExpDto.getCompanySizeDto() != null && !StringUtils.isEmpty(jobExpDto.getCompanySizeDto().getSizeCode())){
			isNothingInput = false;
		}
		
		if(jobExpDto.getIndustryCategoryDto() != null && !StringUtils.isEmpty(jobExpDto.getIndustryCategoryDto().getCategoryCode())){
			isNothingInput = false;
		}
		
		if(jobExpDto.getIndustryDto() != null && !StringUtils.isEmpty(jobExpDto.getIndustryDto().getIndustryCode())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(jobExpDto.getDepartment())){
			isNothingInput = false;
		}
		
		if(jobExpDto.getPositionCategoryDto() != null && !StringUtils.isEmpty(jobExpDto.getPositionCategoryDto().getTypeCode())){
			isNothingInput = false;
		}
		
		if(jobExpDto.getPositionDto() != null && !StringUtils.isEmpty(jobExpDto.getPositionDto().getTypeCode())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(jobExpDto.getPositionName())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(jobExpDto.getJobDescription())){
			isNothingInput = false;
		}
		
		return isNothingInput;
	}
	
	@RequestMapping("/talent/addJobExperience.do")
	public ModelAndView addJobExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO)JobExperienceDTO jobExpDto,
			BindingResult errors){
		ModelAndView mv = null;
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag) && isNothingInput(jobExpDto)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddJobExperience.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		jobExpValidator.validate(jobExpDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/jobExperienceCreate");
			mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO, jobExpDto);
			mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddJobExperience.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/jobExperienceCreate");
		
		IndustryDTO industry = codeTableHelper.getIndustryByCode(jobExpDto.getIndustryDto().getIndustryCode());
		PositionDTO position = codeTableHelper.getPositionByCode(jobExpDto.getPositionDto().getTypeCode());
		
		jobExpDto.setIndustryDto(industry);
		jobExpDto.setPositionDto(position);
		
		List<JobExperienceDTO> jobExpDtos = resumeDto.getJobExpDtos();
		jobExpDtos.add(jobExpDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO, new JobExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv ;
	}
	
	@RequestMapping("/talent/completeAddJobExperience.do")
	public ModelAndView completeAddJobExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/preEditJobExperience.do")
	public ModelAndView preEditJobExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/jobExperienceAmend");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("jobExpId", id);

		List<JobExperienceDTO> jobExpDtos = resumeDto.getJobExpDtos();
		JobExperienceDTO exp = null;
		
		if(!CollectionUtils.isEmpty(jobExpDtos)){
			exp = jobExpDtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO, exp);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditJobExperience.do")
	public ModelAndView completeEditJobExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO)JobExperienceDTO jobExpDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/jobExperienceCreate");
		
		jobExpValidator.validate(jobExpDto, errors);

		if(errors.hasErrors()){
			mv = new ModelAndView("talent/jobExperienceAmend");
			mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO, jobExpDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("jobExpId");
		
		IndustryDTO industry = codeTableHelper.getIndustryByCode(jobExpDto.getIndustryDto().getIndustryCode());
		PositionDTO position = codeTableHelper.getPositionByCode(jobExpDto.getPositionDto().getTypeCode());
		
		jobExpDto.setIndustryDto(industry);
		jobExpDto.setPositionDto(position);
		
		List<JobExperienceDTO> jobExpDtos = resumeDto.getJobExpDtos();
		
		if(!CollectionUtils.isEmpty(jobExpDtos)){
			jobExpDtos.set(Integer.parseInt(id), jobExpDto);
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO , new JobExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillJobExperience.do")
	public ModelAndView backToFillJobExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/jobExperienceCreate");

		initJobExperience(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO , new JobExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		return mv;
	}
	
	@RequestMapping("/talent/deleteJobExperience.do")
	public ModelAndView deleteJobExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/jobExperienceCreate");
		String[] expsList = request.getParameterValues("expsList");
		List<JobExperienceDTO> jobExpDtos = resumeDto.getJobExpDtos();
		
		List<JobExperienceDTO> removedDtos = new ArrayList<JobExperienceDTO>(0);
		
		if(expsList != null && expsList.length != 0 && !CollectionUtils.isEmpty(jobExpDtos)){
			for(String id : expsList){
				removedDtos.add(jobExpDtos.get(Integer.parseInt(id)));
			}
			
			jobExpDtos.removeAll(removedDtos);
		}
		
		resumeDto.setJobExpDtos(jobExpDtos);
		
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO , new JobExperienceDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/loadIndustries.do")
	public ModelAndView loadIndustries(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String categoryCode = request.getParameter("_id");
			List<IndustryDTO> industries = codeTableHelper.getIndustriesByCategory(categoryCode);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(industries)){
				for(IndustryDTO dto : industries){
					Element post = root.addElement("industry");
					post.addElement("label").setText(dto.getDisplayName());
					post.addElement("value").setText(dto.getIndustryCode());
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
			logger.error(">>>>> Exception Catched(loadIndustries) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
		
		return null;
	}
	
	@RequestMapping("/talent/loadPositions.do")
	public ModelAndView loadPositions(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String categoryCode = request.getParameter("_id");
			List<PositionDTO> positions = codeTableHelper.getPositionsByCategory(categoryCode);
			
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
}
