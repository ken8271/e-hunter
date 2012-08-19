package com.pccw.ehunter.mvc.talent;

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

import com.pccw.ehunter.constant.ActionFlag;
import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.ProjectExperienceDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.ProjectExperienceValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_RESUME_DTO,
	SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO
})
public class ProjectExperienceController extends BaseController {
	
	@Autowired
	private ProjectExperienceValidator prjExpValidator;
	
	@RequestMapping("/talent/fillProjectExperience.do")
	public ModelAndView fillProjectExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/projectExperienceCreate");
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
		
		mv.addObject(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO, new ProjectExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		return mv;
	}
	
	@RequestMapping("/talent/addProjectExperienceActions.do")
	public ModelAndView addProjectExperienceActions(HttpServletRequest request,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO)ProjectExperienceDTO prjExpDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addProjectExperience.do", true));
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		
		return mv;
	}
	
	@RequestMapping("/talent/addProjectExperience.do")
	public ModelAndView addProjectExperience(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO)ProjectExperienceDTO prjExpDto,
			BindingResult errors){
		ModelAndView mv = null;
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		} else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag) && isNothingInput(prjExpDto)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddProjectExperience.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		prjExpValidator.validate(prjExpDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/projectExperienceCreate");
			mv.addObject(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO, prjExpDto);
			mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddProjectExperience.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/projectExperienceCreate");
		
		List<ProjectExperienceDTO> dtos = resumeDto.getPrjExpDtos();
		dtos.add(prjExpDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO, new ProjectExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	private boolean isNothingInput(ProjectExperienceDTO dto){
		boolean isNothingInput = true;
		
		if(dto.getFromDateDto() != null){
			if(!StringUtils.isEmpty(dto.getFromDateDto().getYear())
					|| !StringUtils.isEmpty(dto.getFromDateDto().getMonth())
					|| !StringUtils.isEmpty(dto.getFromDateDto().getDay())){
				isNothingInput = false;
			}
		}
		
		if(dto.getToDateDto() != null){
			if(!StringUtils.isEmpty(dto.getToDateDto().getYear())
					|| !StringUtils.isEmpty(dto.getToDateDto().getMonth())
					|| !StringUtils.isEmpty(dto.getToDateDto().getDay())){
				isNothingInput = false;
			}
		}
		
		if(!StringUtils.isEmpty(dto.getName())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getDuty())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getDescription())){
			isNothingInput = false;
		}
		return isNothingInput;
	}
	
	@RequestMapping("/talent/completeAddProjectExperience.do")
	public ModelAndView completeAddProjectExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/preEditProjectExperience.do")
	public ModelAndView preEditProjectExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/projectExperienceAmend");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("projectExperienceId", id);

		List<ProjectExperienceDTO> dtos = resumeDto.getPrjExpDtos();
		ProjectExperienceDTO exp = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			exp = dtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO, exp);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditProjectExperience.do")
	public ModelAndView completeEditProjectExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO)ProjectExperienceDTO prjExpDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/projectExperienceCreate");
		
		prjExpValidator.validate(prjExpDto, errors);

		if(errors.hasErrors()){
			mv = new ModelAndView("talent/projectExperienceAmend");
			mv.addObject(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO, prjExpDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("projectExperienceId");
		
		List<ProjectExperienceDTO> dtos = resumeDto.getPrjExpDtos();
		
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), prjExpDto);
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO , new ProjectExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillProjectExperience.do")
	public ModelAndView backToFillProjectExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/projectExperienceCreate");
		
		mv.addObject(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO , new ProjectExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		return mv;
	}
	
	@RequestMapping("/talent/deleteProjectExperience.do")
	public ModelAndView deleteProjectExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/projectExperienceCreate");
		String[] expsList = request.getParameterValues("expsList");
		List<ProjectExperienceDTO> dtos = resumeDto.getPrjExpDtos();
		
		List<ProjectExperienceDTO> removedDtos = new ArrayList<ProjectExperienceDTO>(0);
		
		if(expsList != null && expsList.length != 0 && !CollectionUtils.isEmpty(dtos)){
			for(String id : expsList){
				removedDtos.add(dtos.get(Integer.parseInt(id)));
			}
			
			dtos.removeAll(removedDtos);
		}
		
		resumeDto.setPrjExpDtos(dtos);
		
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO , new ProjectExperienceDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
}
