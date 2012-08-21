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

import com.pccw.ehunter.constant.ActionFlag;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.ResumeValidator;
import com.pccw.ehunter.validator.TalentBaseInfoValidator;

@Controller
@SessionAttributes({SessionAttributeConstant.TALENT_DTO,
	                SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO,
	                SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO,
	                SessionAttributeConstant.TALENT_RESUME_DTO})
public class TalentRegistrationController extends BaseController{
	
	@Autowired
	private TalentBaseInfoValidator talentBaseInfoValidator;

	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private ResumeValidator resumeValidator;
	
	@RequestMapping("/talent/initAddTalent.do")
	public ModelAndView initAddTalent(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/talent/fillTalentInfo.do", true));
	}
	
	@RequestMapping("/talent/fillTalentInfo.do")
	public ModelAndView fillTalentInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/talentCreate");
		
		initTalent(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, new TalentDTO());
		return mv;
	}

	private void initTalent(HttpServletRequest request , ModelAndView mv) {		
		List<TalentSourceDTO> talentSources = codeTableHelper.getAllTalentSources(request);
		List<DegreeDTO> degrees = codeTableHelper.getAllDegrees(request);
		
		mv.addObject(WebConstant.LIST_OF_TALENT_SOURCE, talentSources);
		mv.addObject(WebConstant.LIST_OF_DEGREE, degrees);
	}
	
	@RequestMapping("/talent/saveTalentInfo.do")
	public ModelAndView saveTalentInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto , BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/fillTalentResume.do", true));
		
		talentBaseInfoValidator.validate(talentDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/talentCreate");
			initAddTalent(request);
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/fillTalentResume.do")
	public ModelAndView fillTalentResume(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		ResumeDTO resumeDto = new ResumeDTO();
		talentDto.setResumeDto(resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/addResumeActions.do")
	public ModelAndView addResumeActions(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,BindingResult errors){
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.SUBMIT.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/addResume.do", true));
		}else if(ActionFlag.EDU_EXP.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillEducationExperience.do", true));
		}else if(ActionFlag.JOB_EXP.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillJobExperience.do", true));
		}else if(ActionFlag.PROJECT_EXP.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillProjectExperience.do", true));
		}else if(ActionFlag.TRAINING_EXP.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillTrainingExperience.do", true));
		}else if(ActionFlag.PROFESSIONAL_SKILL.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillProfessionalSkill.do", true));
		}else if(ActionFlag.JOB_INTENTION.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillJobIntention.do", true));
		}else if(ActionFlag.LANGUAGE_ABILITY.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillLanguageAbility.do", true));
		}else if(ActionFlag.CERT.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillCert.do", true));
		}else if(ActionFlag.COMPLETE.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/completeAddResumes.do", true));
		}else {
			return new ModelAndView("talent/resumeCreate");
		}
	}
	
	@RequestMapping("/talent/addResume.do")
	public ModelAndView addResume(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto , BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		resumeValidator.validate(talentDto.getResumeDto(), errors);
		
		if(errors.hasErrors()){
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		List<ResumeDTO> resumes = talentDto.getResumeDtos();		
		resumes.add(talentDto.getResumeDto());
		
		talentDto.setResumeDto(new ResumeDTO());
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
}
