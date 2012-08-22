package com.pccw.ehunter.mvc.talent;

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
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.JobIntentionDTO;
import com.pccw.ehunter.dto.SelfEvaluationDTO;
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
		
		DegreeDTO degreeDto = codeTableHelper.getDegreeByCode(request, talentDto.getHighestDegree());
		talentDto.setDegreeDto(degreeDto);
		
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
		}else if(ActionFlag.UPDATE.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/completeEditResume.do", true));
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
		
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_JOB_INTENTION_DTO);
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO);
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO);
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_PROJECT_EXPERIENCE_DTO);
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO);
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO);
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO);
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_CERT_DTO);
		talentDto.setResumeDto(initialResumeDto());
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	private ResumeDTO initialResumeDto(){
		ResumeDTO resumeDto = new ResumeDTO();
		SelfEvaluationDTO se = new SelfEvaluationDTO();
		JobIntentionDTO ji = new JobIntentionDTO();
		
		resumeDto.setSelfEvaluationDto(se);
		resumeDto.setIntentionDto(ji);
		
		return resumeDto;
	}
	
	@RequestMapping("/talent/preEditResume.do")
	public ModelAndView preEditResume(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("resumeId", id);
		List<ResumeDTO> resumeDtos = talentDto.getResumeDtos();
		ResumeDTO resumeDto = null;
		
		if(!CollectionUtils.isEmpty(resumeDtos)){
			resumeDto = resumeDtos.get(Integer.parseInt(id));
		}
		
		if(resumeDto != null){
			talentDto.setResumeDto(resumeDto);
		}else {
			talentDto.setResumeDto(new ResumeDTO());
		}
		
		request.getSession(false).setAttribute("operation", "E");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditResume.do")
	public ModelAndView completeEditResume(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		resumeValidator.validate(talentDto.getResumeDto(), errors);
		
		if(errors.hasErrors()){
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("resumeId");
		ResumeDTO resumeDto = talentDto.getResumeDto();
		List<ResumeDTO> dtos = talentDto.getResumeDtos();
		
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), resumeDto);
		}
		
		talentDto.setResumeDto(new ResumeDTO());
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		
		request.getSession(false).removeAttribute("operation");
		
		return mv;
	}
	
	@RequestMapping("/talent/backToPreviousStep.do")
	public ModelAndView backToPreviousStep(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/talentCreate");
		
		initTalent(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillResume.do")
	public ModelAndView backToFillResume(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		talentDto.setResumeDto(new ResumeDTO());
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		
		request.getSession(false).removeAttribute("operation");
		
		return mv;
	}
	
	@RequestMapping("/talent/clearCurrResume.do")
	public ModelAndView clearCurrResume(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		talentDto.setResumeDto(new ResumeDTO());
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		
		return mv;
	}
	
	@RequestMapping("/talent/deleteResume.do")
	public ModelAndView delelteResume(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		String id = request.getParameter("_id");
		
		List<ResumeDTO> resumes = talentDto.getResumeDtos();
		
		if(!CollectionUtils.isEmpty(resumes)){
			resumes.remove(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		
		return mv;
	}
	
	@RequestMapping("/talent/pop/viewResume.do")
	public ModelAndView viewResumeDetail(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeView");
		
		String id = request.getParameter("_id");
		List<ResumeDTO> dtos = talentDto.getResumeDtos();
		ResumeDTO resume = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			resume = dtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resume);
		return mv;
	}
}
