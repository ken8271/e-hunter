package com.pccw.ehunter.mvc.talent;

import java.util.List;

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
import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.JobIntentionDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.JobIntentionValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_RESUME_DTO,
	SessionAttributeConstant.TALENT_JOB_INTENTION_DTO
})
public class JobIntentionController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private JobIntentionValidator intentionValidator;
	
	@RequestMapping("/talent/fillJobIntention.do")
	public ModelAndView fillJobIntention(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/jobIntentionCreate");
		
		initJobIntention(request , mv);
		
		String operation = (String)request.getSession(false).getAttribute("operation");
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
		JobIntentionDTO intentionDto = null;
		if("E".equals(operation)){
			intentionDto = resumeDto.getIntentionDto();
		}else {
			intentionDto = (JobIntentionDTO)request.getSession(false).getAttribute(SessionAttributeConstant.TALENT_JOB_INTENTION_DTO);
		}
		
		if(intentionDto == null){
			intentionDto = new JobIntentionDTO();
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_JOB_INTENTION_DTO,intentionDto);
		return mv;
	}

	private void initJobIntention(HttpServletRequest request, ModelAndView mv) {
		List<PositionCategoryDTO> positionCategories = codeTableHelper.getPositionCategories(request);
		List<IndustryCategoryDTO> industryCategories = codeTableHelper.getIndustryCategories(request);

		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, positionCategories);
		mv.addObject(WebConstant.LIST_OF_INDUSTRY_CATEGORY, industryCategories);
	}
	
	@RequestMapping("/talent/completeAddJobIntention.do")
	public ModelAndView completeAddJobIntention(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_JOB_INTENTION_DTO)JobIntentionDTO intentionDto,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		if(isNothingInput(intentionDto)){
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		intentionValidator.validate(intentionDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/jobIntentionCreate");
			mv.addObject(SessionAttributeConstant.TALENT_JOB_INTENTION_DTO, intentionDto);
			return mv;
		}
		
		fillPropertyByCode(request, intentionDto);
		
		resumeDto.setIntentionDto(intentionDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	private void fillPropertyByCode(HttpServletRequest request , JobIntentionDTO intentionDto){
		PositionCategoryDTO pc = codeTableHelper.getPositionCategoryByCode(request, intentionDto.getExpectPosition());
		IndustryCategoryDTO ic = codeTableHelper.getIndustryCategoryByCode(request, intentionDto.getExpectIndustry());
		
		intentionDto.setExpectPositionDto(pc);
		intentionDto.setExpectIndustryDto(ic);
	}
	
	private boolean isNothingInput(JobIntentionDTO dto){
		boolean isNothingInput = true;
		
		if(!StringUtils.isEmpty(dto.getEmploymentCategory())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getExpectAddress())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getExpectPosition())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getExpectIndustry())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getExpectSalary())){
			isNothingInput = false;
		}
		
		return isNothingInput;
	}
	
	@RequestMapping("/talent/backWithNothingFilled.do")
	public ModelAndView backWithNothingFilled(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		String operation = (String)request.getSession(false).getAttribute("operation");
		if(!"E".equals(operation)){
			talentDto.setResumeDto(new ResumeDTO());			
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
}
