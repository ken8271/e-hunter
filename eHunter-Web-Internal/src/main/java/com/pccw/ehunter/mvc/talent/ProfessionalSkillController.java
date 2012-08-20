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
import com.pccw.ehunter.dto.ProfessionalSkillDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.SkillCategoryDTO;
import com.pccw.ehunter.dto.SkillLevelDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.ProfessionalSkillValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO ,
	SessionAttributeConstant.TALENT_RESUME_DTO ,
	SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO
})
public class ProfessionalSkillController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private ProfessionalSkillValidator skillValidator;

	@RequestMapping("/talent/fillProfessionalSkill.do")
	public ModelAndView fillProfessionalSkill(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/professionalSkillCreate");
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
		
		initProfessionalSkill(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO , new ProfessionalSkillDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		return mv;
	}
	
	private void initProfessionalSkill(HttpServletRequest request,ModelAndView mv) {
		List<SkillCategoryDTO> skillCategories = codeTableHelper.getSkillCategories(request);
		List<SkillLevelDTO> skillLevels = codeTableHelper.getSkillLevels(request);
		
		mv.addObject(WebConstant.LIST_OF_SKILL_CATEGORY, skillCategories);
		mv.addObject(WebConstant.LIST_OF_SKILL_LEVEL, skillLevels);
	}

	@RequestMapping("/talent/addProfessionalSkillActions.do")
	public ModelAndView addProfessionalSkillActions(HttpServletRequest request,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO)ProfessionalSkillDTO skillDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addProfessionalSkill.do", true));
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		
		return mv;
	}
	
	@RequestMapping("/talent/addProfessionalSkill.do")
	public ModelAndView addProfessionalSkill(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO)ProfessionalSkillDTO skillDto,
			BindingResult errors){
		ModelAndView mv = null;
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		} else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag) && isNothingInput(skillDto)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddProfessionalSkill.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		skillValidator.validate(skillDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/professionalSkillCreate");
			mv.addObject(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO , skillDto);
			mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddProfessionalSkill.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/professionalSkillCreate");
		
		SkillCategoryDTO sc = codeTableHelper.getSkillCategoryByCode(request, skillDto.getCategoryCode());
		SkillLevelDTO sl = codeTableHelper.getSkillLevelByCode(request, skillDto.getLevelCode());
		
		skillDto.setCategoryDto(sc);
		skillDto.setLevelDto(sl);
		
		List<ProfessionalSkillDTO> dtos = resumeDto.getSkillDtos();
		dtos.add(skillDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO , new ProfessionalSkillDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	private boolean isNothingInput(ProfessionalSkillDTO skillDto) {
		boolean isNothingInput = true;
		
		if(!StringUtils.isEmpty(skillDto.getCategoryCode())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(skillDto.getSkillName())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(skillDto.getDuration())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(skillDto.getLevelCode())){
			isNothingInput = false;
		}
		return isNothingInput;
	}
	
	@RequestMapping("/talent/completeAddProfessionalSkill.do")
	public ModelAndView completeAddProfessionalSkill(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/preEditProfessionalSkill.do")
	public ModelAndView preEditProfessionalSkill(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/professionalSkillAmend");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("professionalSkillId", id);

		List<ProfessionalSkillDTO> dtos = resumeDto.getSkillDtos();
		ProfessionalSkillDTO exp = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			exp = dtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO, exp);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditProfessionalSkill.do")
	public ModelAndView completeEditProfessionalSkill(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO)ProfessionalSkillDTO skillDto,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/professionalSkillCreate");
		
		skillValidator.validate(skillDto, errors);

		if(errors.hasErrors()){
			mv = new ModelAndView("talent/professionalSkillAmend");
			mv.addObject(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO , skillDto);
			return mv;
		}
		
		SkillCategoryDTO sc = codeTableHelper.getSkillCategoryByCode(request, skillDto.getCategoryCode());
		SkillLevelDTO sl = codeTableHelper.getSkillLevelByCode(request, skillDto.getLevelCode());
		
		skillDto.setCategoryDto(sc);
		skillDto.setLevelDto(sl);
		
		String id = (String)request.getSession(false).getAttribute("professionalSkillId");
		
		List<ProfessionalSkillDTO> dtos = resumeDto.getSkillDtos();
		
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), skillDto );
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO , new ProfessionalSkillDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillProfessionalSkill.do")
	public ModelAndView backToFillProfessionalSkill(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/professionalSkillCreate");
		
		initProfessionalSkill(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO , new ProfessionalSkillDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		return mv;
	}
	
	@RequestMapping("/talent/deleteProfessionalSkill.do")
	public ModelAndView deleteProfessionalSkill(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/professionalSkillCreate");
		String[] expsList = request.getParameterValues("expsList");
		
		List<ProfessionalSkillDTO> dtos = resumeDto.getSkillDtos();
		
		List<ProfessionalSkillDTO> removedDtos = new ArrayList<ProfessionalSkillDTO>(0);
		
		if(expsList != null && expsList.length != 0 && !CollectionUtils.isEmpty(dtos)){
			for(String id : expsList){
				removedDtos.add(dtos.get(Integer.parseInt(id)));
			}
			
			dtos.removeAll(removedDtos);
		}
		
		resumeDto.setSkillDtos(dtos);
		
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO , new ProfessionalSkillDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
}
