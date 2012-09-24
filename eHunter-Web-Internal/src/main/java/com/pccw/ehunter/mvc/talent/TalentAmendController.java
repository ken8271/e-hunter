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
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentRegistrationService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.validator.TalentBaseInfoValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO
})
public class TalentAmendController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private TalentBaseInfoValidator talentBaseInfoValidator;
	
	@Autowired
	private TalentRegistrationService talentRegtService;
	
	@RequestMapping("/talent/preEditTalentInfo.do")
	public ModelAndView preEditTalentInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/talentAmend");
		
		initTalent(request , mv);
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	private void initTalent(HttpServletRequest request , ModelAndView mv) {		
		List<TalentSourceDTO> talentSources = codeTableHelper.getTalentSources(request);
		List<DegreeDTO> degrees = codeTableHelper.getAllDegrees(request);
		
		mv.addObject(WebConstant.LIST_OF_TALENT_SOURCE, talentSources);
		mv.addObject(WebConstant.LIST_OF_DEGREE, degrees);
	}
	
	@RequestMapping("/talent/updateTalentInfo.do")
	public ModelAndView updateTalentInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto, BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/viewTalentDetail.do", true));
		
		talentBaseInfoValidator.validate(talentDto, errors);
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/talentAmend");
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		talentRegtService.udpateTalent(talentDto);
		
		mv.addObject("_id", talentDto.getTalentID());
		return mv;
	}

}
