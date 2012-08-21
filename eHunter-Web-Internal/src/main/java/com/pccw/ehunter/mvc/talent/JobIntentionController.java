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
		
		JobIntentionDTO intentionDto = (JobIntentionDTO)request.getSession(false).getAttribute(SessionAttributeConstant.TALENT_JOB_INTENTION_DTO);
		if(intentionDto == null){
			intentionDto = new JobIntentionDTO();
		}
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
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
		
		resumeDto.setIntentionDto(intentionDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
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
	
	@RequestMapping("/talent/backToFillResume.do")
	public ModelAndView backToFillResume(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
}
