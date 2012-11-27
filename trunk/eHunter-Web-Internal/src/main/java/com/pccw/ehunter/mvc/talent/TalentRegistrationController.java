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
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.CityDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.EducationExperienceDTO;
import com.pccw.ehunter.dto.JobExperienceDTO;
import com.pccw.ehunter.dto.JobIntentionDTO;
import com.pccw.ehunter.dto.LanguageAbilityDTO;
import com.pccw.ehunter.dto.ProfessionalSkillDTO;
import com.pccw.ehunter.dto.ProjectEnquireDTO;
import com.pccw.ehunter.dto.SelfEvaluationDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentRegistrationService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.ResumeValidator;
import com.pccw.ehunter.validator.TalentBaseInfoValidator;

@Controller
@SessionAttributes({SessionAttributeConstant.TALENT_DTO,
	                SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO,
	                SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO,
	                SessionAttributeConstant.TALENT_RESUME_DTO,
	                SessionAttributeConstant.PROJECT_ENQUIRE_DTO})
public class TalentRegistrationController extends BaseController{
	
	@Autowired
	private TalentBaseInfoValidator talentBaseInfoValidator;

	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private ResumeValidator resumeValidator;
	
	@Autowired
	private TalentRegistrationService talentRegtService;
	
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
		List<TalentSourceDTO> talentSources = codeTableHelper.getTalentSources(request);
		List<DegreeDTO> degrees = codeTableHelper.getAllDegrees(request);
		
		mv.addObject(WebConstant.LIST_OF_TALENT_SOURCE, talentSources);
		mv.addObject(WebConstant.LIST_OF_DEGREE, degrees);
	}
	
	@RequestMapping("/talent/saveTalentInfo.do")
	public ModelAndView saveTalentInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto , BindingResult errors){
//		String type = request.getParameter("type");
		
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/fillEmploymentHistory.do", true));
//		if(ActionFlag.BATCH_IMPORT_RESUME.equals(type)){			
//			mv= new ModelAndView(new RedirectViewExt("/talent/batchImportResume.do", true));
//		}else {
//			mv= new ModelAndView(new RedirectViewExt("/talent/fillTalentResume.do", true));
//		}
		
		talentBaseInfoValidator.validate(talentDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/talentCreate");
			initAddTalent(request);
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		DegreeDTO degreeDto = codeTableHelper.getDegreeByCode(request, talentDto.getHighestDegree());
		talentDto.setDegreeDto(degreeDto);
		
		TalentSourceDTO src = codeTableHelper.getTalentSource(request, talentDto.getTalentSrc());
		talentDto.setTalentSrcDto(src);
		
		mv.addObject(ModuleIndicator.MODULE, ModuleIndicator.TALENT_REGISTRATION);
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/fillTalentResume.do")
	public ModelAndView fillTalentResume(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		
		String module = request.getParameter(ModuleIndicator.MODULE);
		if(StringUtils.isEmpty(module)){
			module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		}else {
			request.getSession(false).setAttribute(ModuleIndicator.MODULE, module);
		}
		
		ResumeDTO resumeDto = new ResumeDTO();
		talentDto.setResumeDto(resumeDto);
		
		mv.addObject(ModuleIndicator.MODULE, module);
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
		
		String module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		
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
			if(ModuleIndicator.TALENT_ENQUIRY.equals(module)){
				return new ModelAndView(new RedirectViewExt("/talent/submitResumes.do", true));
			}
			return new ModelAndView(new RedirectViewExt("/talent/saveResumes.do", true));
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
		if(CollectionUtils.isEmpty(resumes)){
			resumes = new ArrayList<ResumeDTO>();
			talentDto.setResumeDtos(resumes);
		}
		
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
		
		initializeResume(request , resume);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resume);
		return mv;
	}
	
	private void initializeResume(HttpServletRequest request , ResumeDTO resume) {
		if(resume.getIntentionDto() != null){
			JobIntentionDTO intentionDto = resume.getIntentionDto();
			
			List<CityDTO> cityDtos = new ArrayList<CityDTO>();
			if(!StringUtils.isEmpty(intentionDto.getExpectAddress())){
				String[] cities = StringUtils.tokenize(intentionDto.getExpectAddress(), CommonConstant.COMMA);
				if(cities != null && cities.length != 0){
					for(String cityCode : cities){
						cityDtos.add(codeTableHelper.getCityByCode(cityCode));
					}
				}
			}
			intentionDto.setExpectCityDtos(cityDtos);
			
			if(intentionDto.getExpectIndustryDto() == null){
				intentionDto.setExpectIndustryDto(codeTableHelper.getIndustryCategoryByCode(request, intentionDto.getExpectIndustry()));
			}
			if(intentionDto.getExpectPositionDto() == null){
				intentionDto.setExpectPositionDto(codeTableHelper.getPositionCategoryByCode(request, intentionDto.getExpectPosition()));
			}
		}
		
		if(!CollectionUtils.isEmpty(resume.getEduExpDtos())){
			for(EducationExperienceDTO eduExpDto : resume.getEduExpDtos()){
				if(eduExpDto.getDegreeDto() == null) eduExpDto.setDegreeDto(codeTableHelper.getDegreeByCode(request, eduExpDto.getDegree()));
				if(eduExpDto.getMajorDto() == null) eduExpDto.setMajorDto(codeTableHelper.getSubjectByCode(eduExpDto.getMajor()));
			}
		}
		
		if(!CollectionUtils.isEmpty(resume.getJobExpDtos())){
			for(JobExperienceDTO jobExpDto : resume.getJobExpDtos()){
				jobExpDto.setCompanyCategoryDto(codeTableHelper.getCompanyCategoryByCode(request, jobExpDto.getCompanyCategoryDto().getCategoryCode()));
				jobExpDto.setCompanySizeDto(codeTableHelper.getCompanySizeByCode(request, jobExpDto.getCompanySizeDto().getSizeCode()));
				//jobExpDto.setIndustryCategoryDto(codeTableHelper.getIndustryCategoryByCode(request, jobExpDto.getIndustryCategoryDto().getCategoryCode()));
				jobExpDto.setIndustryDto(codeTableHelper.getIndustryByCode(jobExpDto.getIndustryDto().getIndustryCode()));
				//jobExpDto.setPositionCategoryDto(codeTableHelper.getPositionCategoryByCode(request, jobExpDto.getPositionCategoryDto().getTypeCode()));
				jobExpDto.setPositionDto(codeTableHelper.getPositionByCode(jobExpDto.getPositionDto().getTypeCode()));
			}
		}
		
		if(!CollectionUtils.isEmpty(resume.getLanguageDtos())){
			for(LanguageAbilityDTO language : resume.getLanguageDtos()){
				language.setLanguageCategoryDto(codeTableHelper.getLanguageCategoryByCode(request, language.getLanguageCategory()));
				language.setAblitityOfRWDto(codeTableHelper.getSkillLevelByCode(request, language.getAblitityOfRW()));
				language.setAblitityOfLSDto(codeTableHelper.getSkillLevelByCode(request, language.getAblitityOfLS()));
			}
		}
		
		if(!CollectionUtils.isEmpty(resume.getSkillDtos())){
			for(ProfessionalSkillDTO skillDto : resume.getSkillDtos()){
				skillDto.setCategoryDto(codeTableHelper.getSkillCategoryByCode(request, skillDto.getCategoryCode()));
				skillDto.setLevelDto(codeTableHelper.getSkillLevelByCode(request, skillDto.getLevelCode()));
			}
		}
	}

	@RequestMapping("/talent/saveResumes.do")
	public ModelAndView saveResumes(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto , BindingResult errors){
		ModelAndView mv = null;
		
		if(talentDto.getResumeDto() == null || isNothingInput(talentDto.getResumeDto())){
			mv = new ModelAndView(new RedirectViewExt("/talent/verify.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		resumeValidator.validate(talentDto.getResumeDto(), errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/resumeCreate");
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		mv = new ModelAndView(new RedirectViewExt("/talent/verify.do", true));
				
		talentDto.getResumeDtos().add(talentDto.getResumeDto());
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		
		return mv;
	}
	
	private boolean isNothingInput(ResumeDTO dto){
		boolean isNothingInput = true;
		
		if(!StringUtils.isEmpty(dto.getName())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getLanguage())){
			isNothingInput = false;
		}
		
		if(dto.getIntentionDto() != null && !StringUtils.isEmpty(dto.getIntentionDto().getEmploymentCategory())){
			isNothingInput = false;
		}
		
		if(dto.getSelfEvaluationDto() != null && !StringUtils.isEmpty(dto.getSelfEvaluationDto().getContent())){
			isNothingInput = false;
		}
		
		if(!CollectionUtils.isEmpty(dto.getEduExpDtos())){
			isNothingInput = false;
		}
		
		if(!CollectionUtils.isEmpty(dto.getJobExpDtos())){
			isNothingInput = false;
		}
		
		if(!CollectionUtils.isEmpty(dto.getPrjExpDtos())){
			isNothingInput = false;
		}
		
		if(!CollectionUtils.isEmpty(dto.getTrnExpDtos())){
			isNothingInput = false;
		}
		
		if(!CollectionUtils.isEmpty(dto.getSkillDtos())){
			isNothingInput = false;
		}
		
		if(!CollectionUtils.isEmpty(dto.getLanguageDtos())){
			isNothingInput = false;
		}
		
		if(!CollectionUtils.isEmpty(dto.getCertDtos())){
			isNothingInput = false;
		}
		return isNothingInput;
	}
	
	@RequestMapping("/talent/verify.do")
	public ModelAndView verify(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/verifyInfo");
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		
		return mv;
	}
	
	@RequestMapping("/talent/submitTalent.do")
	public ModelAndView submitTalent(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		talentRegtService.completeTalentRegistration(talentDto);
		transactionLogService.logTransaction(ModuleIndicator.TALENT, getMessage("tx.log.talent.create" , new String[]{talentDto.getTalentID()}));
		return new ModelAndView(new RedirectViewExt("/talent/completeTalentRegistration.do", true));
	}
	
	@RequestMapping("/talent/completeTalentRegistration.do")
	public ModelAndView completeTalentRegistration(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/talentConfirmation");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject(SessionAttributeConstant.PROJECT_ENQUIRE_DTO, new ProjectEnquireDTO());
		return mv;
	}
}
