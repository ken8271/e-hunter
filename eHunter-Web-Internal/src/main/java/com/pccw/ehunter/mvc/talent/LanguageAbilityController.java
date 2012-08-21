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
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.LanguageAbilityDTO;
import com.pccw.ehunter.dto.LanguageCategoryDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.SkillLevelDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.LanguageAbilityValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_RESUME_DTO,
	SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO
})
public class LanguageAbilityController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private LanguageAbilityValidator languageValidator;
	
	@RequestMapping("/talent/fillLanguageAbility.do")
	public ModelAndView fillLanguageAbility(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/languageAbilityCreate");
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
		
		List<LanguageAbilityDTO> dtos = resumeDto.getLanguageDtos();
		
		if(CollectionUtils.isEmpty(dtos)){
			dtos = new ArrayList<LanguageAbilityDTO>(0);
			resumeDto.setLanguageDtos(dtos);
		}

		initLanguageAbility(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO , new LanguageAbilityDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		
		return mv;
	}
	
	private void initLanguageAbility(HttpServletRequest request , ModelAndView mv) {		
		List<LanguageCategoryDTO> languageCategories = codeTableHelper.getLanguageCategories(request);
		List<SkillLevelDTO> levels = codeTableHelper.getSkillLevels(request);
		
		mv.addObject(WebConstant.LIST_OF_LANGUAGE_CATEGORY, languageCategories);
		mv.addObject(WebConstant.LIST_OF_SKILL_LEVEL, levels);
	}
	
	@RequestMapping("/talent/addLanguageAbilityActions.do")
	public ModelAndView addEduExpActions(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO)LanguageAbilityDTO languageDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addLanguageAbility.do", true));
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		return mv;
	}
	
	private boolean isNothingInput(LanguageAbilityDTO dto){
		boolean isNothingInput = true;
		
		if(!StringUtils.isEmpty(dto.getLanguageCategory())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getAblitityOfRW())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getAblitityOfLS())){
			isNothingInput = false;
		}
		return isNothingInput;
	}
	
	@RequestMapping("/talent/addLanguageAbility.do")
	public ModelAndView addEducationExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO)LanguageAbilityDTO languageDto,
			BindingResult errors){
		ModelAndView mv = null;
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(!StringUtils.isEmpty(actionFlag)){
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}else {
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag) && isNothingInput(languageDto)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddLanguageAbility.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		languageValidator.validate(languageDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/languageAbilityCreate");
			mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
			mv.addObject(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO , languageDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddLanguageAbility.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/languageAbilityCreate");
		
		LanguageCategoryDTO languageCategoryDto = codeTableHelper.getLanguageCategoryByCode(request, languageDto.getLanguageCategory());
		languageDto.setLanguageCategoryDto(languageCategoryDto);
		
		SkillLevelDTO levelDto = codeTableHelper.getSkillLevelByCode(request, languageDto.getAblitityOfRW());
		languageDto.setAblitityOfRWDto(levelDto);
		
		levelDto = codeTableHelper.getSkillLevelByCode(request, languageDto.getAblitityOfLS());
		languageDto.setAblitityOfLSDto(levelDto);
		
		List<LanguageAbilityDTO> dtos = resumeDto.getLanguageDtos();
		dtos.add(languageDto);
			
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO , new LanguageAbilityDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/completeAddLanguageAbility.do")
	public ModelAndView completeAddEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/preEditLanguageAbility.do")
	public ModelAndView preEditEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/languageAbilityAmend");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("languageAbilityId", id);

		List<LanguageAbilityDTO> dtos = resumeDto.getLanguageDtos();
		LanguageAbilityDTO exp = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			exp = dtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO , exp);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditLanguageAbility.do")
	public ModelAndView completeEditEduExp(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO)LanguageAbilityDTO languageDto,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/languageAbilityCreate");
		
		languageValidator.validate(languageDto, errors);

		if(errors.hasErrors()){
			mv = new ModelAndView("talent/languageAbilityAmend");
			mv.addObject(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO , languageDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("languageAbilityId");
		
		LanguageCategoryDTO languageCategoryDto = codeTableHelper.getLanguageCategoryByCode(request, languageDto.getLanguageCategory());
		languageDto.setLanguageCategoryDto(languageCategoryDto);
		
		SkillLevelDTO levelDto = codeTableHelper.getSkillLevelByCode(request, languageDto.getAblitityOfRW());
		languageDto.setAblitityOfRWDto(levelDto);
		
		levelDto = codeTableHelper.getSkillLevelByCode(request, languageDto.getAblitityOfLS());
		languageDto.setAblitityOfLSDto(levelDto);
		
		List<LanguageAbilityDTO> dtos = resumeDto.getLanguageDtos();
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), languageDto);
		}
		
		languageDto = new LanguageAbilityDTO();
		mv.addObject(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO, languageDto);
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/deleteLanguageAbility.do")
	public ModelAndView deleteEducationExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/languageAbilityCreate");
		String[] expsList = request.getParameterValues("expsList");

		List<LanguageAbilityDTO> dtos = resumeDto.getLanguageDtos();
		List<LanguageAbilityDTO> removedDtos = new ArrayList<LanguageAbilityDTO>(0);
		
		if(expsList != null && expsList.length != 0 && !CollectionUtils.isEmpty(dtos)){
			for(String id : expsList){
				removedDtos.add(dtos.get(Integer.parseInt(id)));
			}
			
			dtos.removeAll(removedDtos);
		}
		
		resumeDto.setLanguageDtos(dtos);
		
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO , new LanguageAbilityDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillLanguageAbility.do")
	public ModelAndView backToFillEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/languageAbilityCreate");

		initLanguageAbility(request, mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_LANGUAGE_ABLITITY_DTO , new LanguageAbilityDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		return mv;
	}
}
