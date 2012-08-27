package com.pccw.ehunter.mvc.talent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentRegistrationService;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO
})
public class TalentEnquireController extends BaseController{
	
	@Autowired
	private TalentRegistrationService talentRegtService;
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@RequestMapping("/talent/viewTalentDetail.do")
	public ModelAndView viewTalentDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/viewTalentDetail");
		
		String id = request.getParameter("_id");
		
		TalentDTO talentDto = talentRegtService.getTalentByID(id);
		
		TalentSourceDTO src = codeTableHelper.getTalentSource(request, talentDto.getTalentSrc());
		talentDto.setTalentSrcDto(src);
		
		DegreeDTO degreeDto = codeTableHelper.getDegreeByCode(request, talentDto.getHighestDegree());
		talentDto.setDegreeDto(degreeDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
}
