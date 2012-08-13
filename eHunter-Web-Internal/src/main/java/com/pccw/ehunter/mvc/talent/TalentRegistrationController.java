package com.pccw.ehunter.mvc.talent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.StaticCodeConstant;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.StaticCodeOperator;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.validator.TalentBaseInfoValidator;

@Controller
@SessionAttributes({SessionAttributeConstant.TALENT_DTO,
	                SessionAttributeConstant.LIST_OF_TALENT_SRC})
public class TalentRegistrationController extends BaseController{
	
	@Autowired
	private TalentCommonService talentCommonService;
	
	@Autowired
	private TalentBaseInfoValidator talentBaseInfoValidator;
	
	@Autowired
	private StaticCodeOperator staticCodeOperator;
	
	@RequestMapping("/talent/initAddTalent.do")
	public ModelAndView initAddTalent(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/talent/fillTalentBaseInfo.do", true));
	}
	
	@RequestMapping("/talent/fillTalentBaseInfo.do")
	public ModelAndView fillTalentBaseInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/fillTalentBaseInfo");
		
		initTalent(request);
		mv.addObject(SessionAttributeConstant.TALENT_DTO, request.getSession(false).getAttribute(SessionAttributeConstant.TALENT_DTO));
		
		return mv;
	}

	private void initTalent(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.setAttribute(SessionAttributeConstant.TALENT_DTO, new TalentDTO());
		session.setAttribute(SessionAttributeConstant.LIST_OF_TALENT_SRC, talentCommonService.loadTalentSource());
		session.setAttribute(SessionAttributeConstant.LIST_OF_DEGREE, staticCodeOperator.getStaticCodes(StaticCodeConstant.TBL_KEY_DEGREE, StaticCodeConstant.CD_KEY_DEGREE));
	}
	
	@RequestMapping("/talent/saveTalentBaseInfo.do")
	public ModelAndView saveTalentBaseInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto , BindingResult errors){
		ModelAndView mv = new ModelAndView("");
		
		talentBaseInfoValidator.validate(talentDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/fillTalentBaseInfo");
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		return mv;
	}
}
