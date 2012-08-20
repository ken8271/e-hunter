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
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TrainingExperienceDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.TrainingExperienceValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_RESUME_DTO,
	SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO
})
public class TrainingExperienceController extends BaseController{
	
	@Autowired
	private TrainingExperienceValidator trnExpValidator;
	
	@RequestMapping("/talent/fillTrainingExperience.do")
	public ModelAndView fillTrainingExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/trainingExperienceCreate");
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
		
		mv.addObject(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO , new TrainingExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		return mv;
	}
	
	@RequestMapping("/talent/addTrainingExperienceActions.do")
	public ModelAndView addTrainingExperienceActions(HttpServletRequest request,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO)TrainingExperienceDTO trnExpDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addTrainingExperience.do", true));
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		
		return mv;
	}
	
	@RequestMapping("/talent/addTrainingExperience.do")
	public ModelAndView addTrainingExperience(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO)TrainingExperienceDTO trnExpDto,
			BindingResult errors){
		ModelAndView mv = null;
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		} else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag) && isNothingInput(trnExpDto)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddTrainingExperience.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		trnExpValidator.validate(trnExpDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/trainingExperienceCreate");
			mv.addObject(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO , trnExpDto);
			mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddTrainingExperience.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/trainingExperienceCreate");
		
		List<TrainingExperienceDTO> dtos = resumeDto.getTrnExpDtos();
		dtos.add(trnExpDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO, new TrainingExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	private boolean isNothingInput(TrainingExperienceDTO dto){
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

		if(!StringUtils.isEmpty(dto.getOrganization())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getAddress())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getCourse())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getCert())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getDescription())){
			isNothingInput = false;
		}
		
		return isNothingInput;
	}
	
	@RequestMapping("/talent/completeAddTrainingExperience.do")
	public ModelAndView completeAddTrainingExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/preEditTrainingExperience.do")
	public ModelAndView preEditTrainingExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/trainingExperienceAmend");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("trainingExperienceId", id);

		List<TrainingExperienceDTO> dtos = resumeDto.getTrnExpDtos();
		TrainingExperienceDTO exp = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			exp = dtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO , exp);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditTrainingExperience.do")
	public ModelAndView completeEditTrainingExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO)TrainingExperienceDTO trnExpDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/trainingExperienceCreate");
		
		trnExpValidator.validate(trnExpDto, errors);

		if(errors.hasErrors()){
			mv = new ModelAndView("talent/trainingExperienceAmend");
			mv.addObject(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO , trnExpDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("trainingExperienceId");
		
		List<TrainingExperienceDTO> dtos = resumeDto.getTrnExpDtos();
		
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), trnExpDto);
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO , new TrainingExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillTrainingExperience.do")
	public ModelAndView backToFillTrainingExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/trainingExperienceCreate");
		
		mv.addObject(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO , new TrainingExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		return mv;
	}
	
	@RequestMapping("/talent/deleteTrainingExperience.do")
	public ModelAndView deleteTrainingExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/trainingExperienceCreate");
		String[] expsList = request.getParameterValues("expsList");
		
		List<TrainingExperienceDTO> dtos = resumeDto.getTrnExpDtos();
		
		List<TrainingExperienceDTO> removedDtos = new ArrayList<TrainingExperienceDTO>(0);
		
		if(expsList != null && expsList.length != 0 && !CollectionUtils.isEmpty(dtos)){
			for(String id : expsList){
				removedDtos.add(dtos.get(Integer.parseInt(id)));
			}
			
			dtos.removeAll(removedDtos);
		}
		
		resumeDto.setTrnExpDtos(dtos);
		
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_TRAINING_EXPERIENCE_DTO , new TrainingExperienceDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
}
