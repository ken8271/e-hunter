package com.pccw.ehunter.mvc.talent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;

@Controller
@SessionAttributes({SessionAttributeConstant.TALENT_DTO,
	                SessionAttributeConstant.LIST_OF_TALENT_SRC})
public class TalentRegistrationController extends BaseController{
	
	@Autowired
	private TalentCommonService talentCommonService;
	
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
	}
}
